<%-- 
    Document   : login
    Created on : Apr 3, 2020, 7:02:54 PM
    Author     : lts
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="static/css/AdminLTE.css">
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href=""><b> welcome to our website </b></a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">
                <p class="login-box-msg">
                    <b>
                        please login in
                    </b>
                </p>
                <c:if test="${!empty requestScope.errorMessage}">
                    <p style="color: red;">${requestScope.errorMessage}</p>
                </c:if>

                <form class="form-signin" method="post" action="/JSTL/login">
                    <div class="form-group has-feedback">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                        <input autofocus="autofocus"
                               class="form-control text-center"
                               id="username"
                               name="username"
                               placeholder="user name"
                               required="required"
                               type="text">

                    </div>

                    <div class="form-group has-feedback">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        <input class="form-control text-center" id="password"
                               name="password"
                               placeholder="password"
                               required="required"
                               type="password">

                    </div>
                    <div class="row">
                        <!-- /.col -->
                        <div class="col-xs-4">
                            <button class="btn btn-primary btn-block btn-flat" type="submit">login</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>
                <hr>
                <div class="row">
                    <div class="col-xs-6">
                        <a class="text-right" href="#">
                            <b>
                                forget password
                            </b>
                        </a>
                    </div>
                    <div class="col-xs-6">
                        <a class="pull-right" href="register.jsp">
                            <b>
                                sign up
                            </b>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
