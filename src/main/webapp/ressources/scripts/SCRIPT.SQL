-- -----------------------------------------------------------------------------
--             Génération d'une base de données pour
--                      Oracle Version 10g
--                        (1/4/2016 17:49:06)
-- -----------------------------------------------------------------------------
--      Nom de la base : MPD1
--      Projet : Projet_LO54
--      Auteur : Etudiant
--      Date de dernière modification : 1/4/2016 17:48:07
-- -----------------------------------------------------------------------------

DROP TABLE COURSE_SESSION CASCADE CONSTRAINTS;

DROP TABLE CLIENT CASCADE CONSTRAINTS;

DROP TABLE COURSE CASCADE CONSTRAINTS;

DROP TABLE LOCATION CASCADE CONSTRAINTS;

DROP TABLE LOG CASCADE CONSTRAINTS;

DROP SEQUENCE course_session_seq;

DROP SEQUENCE client_seq;

DROP SEQUENCE location_seq;

-- -----------------------------------------------------------------------------
--       CREATION DE LA BASE 
-- -----------------------------------------------------------------------------

-- CREATE DATABASE LO54;

-- -----------------------------------------------------------------------------
--       TABLE : COURSE_SESSION
-- -----------------------------------------------------------------------------

CREATE TABLE COURSE_SESSION
   (
    ID NUMBER(4)  NOT NULL ,
    LOCATION_ID NUMBER(4)  NOT NULL,
    COURSE_CODE VARCHAR2(5)  NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL
,   CONSTRAINT PK_COURSE_SESSION PRIMARY KEY (ID)  
   ) ;
   CREATE SEQUENCE course_session_seq;





-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE COURSE_SESSION
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_COURSE_SESSION_LOCATION
     ON COURSE_SESSION (LOCATION_ID ASC)
    ;

CREATE  INDEX I_FK_COURSE_SESSION_COURSE
     ON COURSE_SESSION (COURSE_CODE ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : CLIENT
-- -----------------------------------------------------------------------------

CREATE TABLE CLIENT
   (
    ID NUMBER(4)  NOT NULL ,
    COURSE_SESSION_ID NUMBER(4)  NOT NULL,
    LASTNAME VARCHAR2(20)  NOT NULL,
    FIRSTNAME VARCHAR2(35)  NOT NULL,
    ADDRESS VARCHAR2(35)  NOT NULL,
    PHONE VARCHAR2(13)  NOT NULL,
    EMAIL VARCHAR2(35)  NULL
,   CONSTRAINT PK_CLIENT PRIMARY KEY (ID)  
   ) ;
CREATE SEQUENCE client_seq;



   
-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE CLIENT
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_CLIENT_COURSE_SESSION
     ON CLIENT (COURSE_SESSION_ID ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : COURSE
-- -----------------------------------------------------------------------------

CREATE TABLE COURSE
   (
    CODE VARCHAR2(4)  NOT NULL,
    TITLE VARCHAR2(70)  NOT NULL,
    DESCRIPTION VARCHAR2(255) NOT NULL,
    PICTURE VARCHAR2(8) NOT NULL	
,   CONSTRAINT PK_COURSE PRIMARY KEY (CODE)  
   ) ;


-- -----------------------------------------------------------------------------
--       TABLE : LOG
-- -----------------------------------------------------------------------------

CREATE TABLE LOG
   (
    DATES DATE NOT NULL,
    STUDENT VARCHAR2(56) NOT NULL	
,   CONSTRAINT PK_LOG PRIMARY KEY (DATES)  
   ) ;


-- -----------------------------------------------------------------------------
--       TABLE : LOCATION
-- -----------------------------------------------------------------------------

CREATE TABLE LOCATION
   (
    ID NUMBER(4)  NOT NULL ,
    CITY VARCHAR2(20)  NOT NULL
,   CONSTRAINT PK_LOCATION PRIMARY KEY (ID)  
   ) ;
   CREATE SEQUENCE location_seq;





-- -----------------------------------------------------------------------------
--       CREATION DES REFERENCES DE TABLE
-- -----------------------------------------------------------------------------


ALTER TABLE COURSE_SESSION ADD (
     CONSTRAINT FK_COURSE_SESSION_LOCATION
          FOREIGN KEY (LOCATION_ID)
               REFERENCES LOCATION (ID))   ;

ALTER TABLE COURSE_SESSION ADD (
     CONSTRAINT FK_COURSE_SESSION_COURSE
          FOREIGN KEY (COURSE_CODE)
               REFERENCES COURSE (CODE))   ;

ALTER TABLE CLIENT ADD (
     CONSTRAINT FK_CLIENT_COURSE_SESSION
          FOREIGN KEY (COURSE_SESSION_ID)
               REFERENCES COURSE_SESSION (ID))   ;

-- -----------------------------------------------------------------------------
--       CREATION DES TRIGGERS
-- -----------------------------------------------------------------------------

			   
			   
CREATE OR REPLACE TRIGGER client_bir 
BEFORE INSERT ON client 
FOR EACH ROW

BEGIN
  SELECT client_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/


CREATE OR REPLACE TRIGGER location_bir 
BEFORE INSERT ON location
FOR EACH ROW

BEGIN
  SELECT location_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/

CREATE OR REPLACE TRIGGER course_session_bir 
BEFORE INSERT ON course_session 
FOR EACH ROW

BEGIN
  SELECT course_session_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/
-- -----------------------------------------------------------------------------
--                FIN DE GENERATION
-- -----------------------------------------------------------------------------