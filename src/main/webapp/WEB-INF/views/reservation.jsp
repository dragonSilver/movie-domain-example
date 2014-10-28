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
            <colgroup>
                <col width="30%"/>
                <col width="60%"/>
                <col width="10%"/>
            </colgroup>
            <thead>
                <tr>
                    <th>Movie</th>
                    <th>Showing</th>
                    <th></th>
                </tr>
            </thead>

        <c:forEach items="${movies}" var="each">
            <tr>
                <td>
                    <div>title : ${each.title}</div>
                    <div>price : ${each.fixedAmount} won</div>
                    <button type="button" class="btn btn-info btn-sm movie-click" data-id="${each.id}">choice</button>
                </td>
                <td>
                    <table id="showing-${each.id}" class="table ">
                        <colgroup>
                            <col width="30%"/>
                            <col width="50%"/>
                            <col width="20%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>sequence</th>
                            <th>times</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </td>
                <td id="confirm-${each.id}"></td>
            </tr>

        </c:forEach>
        </table>

    </div>

    <form id="reservation" action="/reservation" method="post">
        <input type="hidden" name="movieId" value="">
        <input type="hidden" name="showingId" value="">
        <input type="hidden" name="customerId" value="1">
        <input type="hidden" name="audienceCount" value="1">
    </form>

</div><!-- /.container -->




<script type="text/javascript" src="webjars/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript" src="webjars/jquery-tmpl/beta1.0.0/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.0.3/js/bootstrap.min.js"></script>

<script id="showing-template" type="text/x-jquery-tmpl">
<tr>
     <td>{{= sequence}}</td>
     <td>{{= playingTimes}}</td>
     <td>
        <button type="button" class="btn btn-info btn-sm showing-click" data-id="{{= id}}">choice</button>
     </td>
</tr>
</script>

<script id="confirm-template" type="text/x-jquery-tmpl">
    <div>
        <button type="button" class="btn btn-success confirm-click" >confirm</button>
    </div>
    <div>
        <select name="audienceCount">
            <option value=1>1</option>
            <option value=2>2</option>
            <option value=3>3</option>
            <option value=4>4</option>
        </select>
    </div>
</script>


<script type="application/javascript">

    $(function(){

        $('.movie-click').on('click', function(){
            var movieId = $(this).data('id');

            $.ajax({
                url : '/showings/movie/'+movieId,
                dataType : 'json',
                success : function(showings) {
                    var table = $("#showing-"+movieId).find('tbody');
                    table.empty();

                    $.each(showings, function(idx, item){
                        $("#showing-template").tmpl(item).appendTo(table);
                    });

                    showingEvent.registerEvent();
                    movieForm.putMovieId(movieId);
                }
            });
        });

    });

    var movieForm = (function() {
        var movieId, showingId, audienceCount;

        return {
            registerEvent : function() {
                var that = this;
                $(".confirm-click").off();
                $(".confirm-click").on('click', function(){
                    audienceCount = $(this).parents("td").find("select[name=audienceCount]").val();
                    that.action();
                })
            },
            putMovieId : function(id) {
                movieId = id;
            },
            getMovieId : function() {
                return movieId;
            },
            putShowingId : function(id) {
                showingId = id;
            },
            getShowingId : function() {
                return showingId;
            },
            action : function() {
                var $form = $("#reservation");
                $form.find('input[name=movieId]').val(movieId);
                $form.find('input[name=showingId]').val(showingId);
                $form.find('input[name=audienceCount]').val(audienceCount);

                $form.submit();
            }
        }
    })();

    var showingEvent = (function(){
        return {
            registerEvent : function() {
                $(".showing-click").off();
                $(".showing-click").on('click', function(){
                    $("#confirm-template").tmpl("").appendTo($("#confirm-" + movieForm.getMovieId()));
                    var showingId = $(this).data('id');
                    movieForm.putShowingId(showingId);
                    movieForm.registerEvent();
                });
            }
        }
    })();

</script>

</body>

</html>