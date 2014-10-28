<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.0.3/css/bootstrap.min.css" />
    <style>
        body {
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
</head>

<body>


<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Movie Domain Example</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

<div class="container">

    <div class="starter-template">
        <h1>Choice Movie</h1>

        <table class="table table-striped">
            <tr>
                <td>${reservation.customer.name}</td>
            </tr>
            <tr>
                <td>${reservation.showing.movie.title}</td>
            </tr>
            <tr>
                <td>${reservation.showing.playingTimes}</td>
            </tr>
            <tr>
                <td>${reservation.fee.amount}</td>
            </tr>

        </table>
        <a href="/" class="btn btn-warning">LIST</a>
    </div>


</div><!-- /.container -->




<script type="text/javascript" src="webjars/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript" src="webjars/jquery-tmpl/beta1.0.0/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.0.3/js/bootstrap.min.js"></script>

</body>

</html>