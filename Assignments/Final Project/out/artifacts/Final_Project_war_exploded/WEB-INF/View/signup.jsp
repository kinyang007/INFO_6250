<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/16
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<style>
    .col-center-block {
        float: none;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }
</style>

<body class="bg-light">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="navbar-header">
        <a class="navbar-brand" href="/main">KSearch</a>
    </div>
</nav>

<div class="container">
    <br/>
    <h1 class="text-center">Sign Up for KSearch</h1>
    <h6 class="text-center">Connect with great local businesses</h6>
    <div class="row">
        <div class="col-xs-6 col-md-4 col-center-block">
            <br/>
            <form>
                <div class="form-group">
                    <input class="form-control" type="text" name="firstName" placeholder="First Name"/><br/>
                    <input class="form-control" type="text" name="lastName" placeholder="Last Name"/><br/>
                    <input class="form-control" type="text" name="email" placeholder="Email"/><br/>
                    <input class="form-control" type="password" name="password" placeholder="Password"/><br/>
                    <input class="btn btn-primary btn-block" type="submit" name="submit" value="Sign Up"/><br/>
                </div>
            </form>
            <p class="text-right">Already On KSearch? <a href="/login">Log in</a></p>
        </div>
    </div>
</div>

</body>
</html>
