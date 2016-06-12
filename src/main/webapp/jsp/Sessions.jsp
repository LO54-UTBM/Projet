<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Online courses</title>
        <link rel="stylesheet" href="ressources/css/polices.css" />
        <link rel="stylesheet" href="ressources//css/bootstrap.css" />
        <link rel="stylesheet" href="ressources//css/style.css" />
        <link rel="icon" type="image/gif" href="ressources//images/icone.gif" />

    </head>

    <body>	    
        <div class="container">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">

                        <a class="navbar-brand" >Welcome to Online Courses !</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="Courses">Home <span class="sr-only">(current)</span></a></li>

                        </ul>
                        <form class="navbar-form navbar-right" role="search" action="CoursesKeyWord" method="POST">
                            <div class="form-group">
                                <input type="text" name="key" class="form-control" placeholder="Key word">
                            </div>
                            <button type="submit" class="btn btn-default">Search</button>
                        </form>

                        <!--  -->
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Advanced search <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <div class="col-xs-12">                 		

                                            <form class="form form-group" action="CoursesAtLocation" method="POST">
                                                <label class="label label-default">Search by location</label>
                                                <br/>
                                                <div class="input-group">

                                                    <select name="city" id="location" class="form-control">
                                                        <c:forEach items="${requestScope.allLocations}" var="location">
                                                            <option id="city" name="city" value="${location}">${location}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-default" type="submit">Go!</button>
                                                    </span>
                                                </div>     
                                            </form>
                                        </div>
                                    </li>

                                    <li>.......................................................................</li>
                                    <br />
                                    <div class="col-xs-12">                 		

                                        <form class="form-group" role="search" action="CoursesAtDate" method="POST">
                                            <label class="label label-default">Search by date</label>
                                            <br/>
                                            <div class="input-group">
                                                <input type="date" id="date" name="date" class="form-control" placeholder="date" autocomplete="off">
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-default" type="submit">Go!</button>
                                                    </span>
                                            </div>     
                                        </form>
                                    </div>

                                </ul>
                            </li>
                        </ul>

                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>

            <div>
                <div class="panel panel-black" style="">
                    <div class="panel-heading text-center">
                        Our sessions
                    </div>

                    <div class="panel-body" style="min-height: 10; max-height: 10;overflow-y: scroll"> 

                        <div class="col-xs-4">
                            <div class="thumbnail course">
                                <img src="ressources/img/${requestScope.course.picture}" alt="..." class="img-responsive img-rounded" style="width:300px"/>
                                <div class="caption text-center">
                                    <h3 class="text-capitalize">${requestScope.course.code}</h3>
                                    <h4>
                                        <strong>${requestScope.course.title}</strong>
                                    </h4>
                                    <p>${requestScope.course.description}</p>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-8">
                            <c:forEach items="${requestScope.allCourseSessions}" var="courseSession">
                                <div class="col-sm-6 col-md-4">
                                    <div class="thumbnail">
                                        <img src="ressources/img/session.jpg" alt="..." class="img-responsive img-rounded" style="width:50%;">
                                            <div class="caption">
                                                <blockquote class="text-center" style="font-size: 100%">
                                                    Start date :
                                                    <footer>
                                                        <fmt:formatDate type="date" value="${courseSession.startDate}" pattern="MM-dd-yyyy"/> 
                                                        <br/>
                                                        <strong> in </strong> ${courseSession.location.city}
                                                    </footer>
                                                </blockquote> 
                                                <form action="Session" method="POST">
                                                    <input type="hidden"  name="code" value="${requestScope.course.code}"></input>
                                                    <input type="hidden"  name="id" value="${courseSession.id}"></input>

                                                    <jsp:useBean id="now" class="java.util.Date"/>
                                                    <fmt:formatDate type="date" value="${now}" var="date" pattern="yyyy-MM-dd"/>
                                                    <c:set var="date1" value="${date}"/>


                                                    <c:choose>
                                                        <c:when test="${courseSession.startDate >= date1}">
                                                            <button  class="btn btn-default btn-block" type="submit">Sign up</button>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <button  class="btn btn-default btn-block" type="submit" disabled="disabled">Sign up</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </form>

                                            </div>
                                    </div>   
                                </div>
                            </c:forEach>
                        </div>
                    </div> 
                </div>
            </div>
        </div>


        <script type="text/javascript" src="ressources/js/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="ressources/js/bootstrap.js"></script>
        <script type="text/javascript" src="ressources/js/script.js"></script>


    </body>
</html>