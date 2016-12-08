<%-- 
    Document   : searchResult
    Created on : 11 Aug, 2016, 12:41:54 PM
    Author     : zishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="CSS/homepage.css" type="text/css" rel="stylesheet"/>

        <link href="CSS/bootstrap.min.css" type="text/css" rel="stylesheet"/>
        <script src="js/bootstrap.min.js" type="text/javascript" ></script>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'/>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>
        <title>Connect and collaborate for safety and awareness - Garanci</title>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        <!--        <link href="http://fortawesome.github.io/Font-Awesome/ https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800" />-->

        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'/>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>
        <style>
            body
            {
                padding: 0;
                margin: 0;
                font-family: sans-serif;
            }
            #headercontainer{
                width: 100%;
                height: auto;float: left;
                //background-color: black;
                border-bottom: 1px solid rgba(0,0,0,.1);
            }
            #mainheader
            {
                margin-top: 20px;
                height: auto;float: left;
                background-color: white;
                // border: 1px solid red;
                width: 1000px;
            }
            #main
            {
                width: 1000px;
                height: 800px;
                background-color: white;
                margin: 0 auto;
            }
            .pagenumberconainer
            {
                width: 1000px;

                height: 100px;
                float: left;
                // background-color: gray;
            }  .table3
            {
                width: 100%;
            }

            .table3 td
            {
                padding: 5px;
            }

            #signUpButton{
                cursor: pointer;
                width: 100%;
                height: 42px;
                margin: 0 auto;
                margin-top: 25px;
                margin-bottom: 10px;
                padding: 0;
                background: #60B044;
                -moz-border-radius: 3px;
                -webkit-border-radius: 3px;
                border-radius: 3px;
                behavior: url(PIE-1.0.0/PIE.htc);
                border: 0;
                -moz-box-shadow: 0 15px 30px 0 rgba(255,255,255,.1) inset;
                -webkit-box-shadow: 0 15px 30px 0 rgba(255,255,255,.1) inset;
                box-shadow: 0 15px 30px 0 rgba(255,255,255,.1) inset;
                font-family: 'Open Sans';
                //font-size: 16px;
                float: right;
                font-weight: 400;
                color: black;//#fff;
                text-shadow: 0 1px 2px rgba(0,0,0,.1);
                -o-transition: all .2s;
                -moz-transition: all .2s;
                -webkit-transition: all .2s;
                -ms-transition: all .2s;
            }
            #signUpButton a{
                color: white;
            }

            .logo{
                float:left;
                height: 50px;
                padding: 0 auto;
                margin-left: 20px;
            }
            .logo a{
                font-family: 'Open Sans';
                color: black;
                font-size: 2.8em;
                font-weight: bolder;
                text-decoration: none;
            }
            .logo a:hover{
                text-decoration: none;
            }
            #header{
                width: 960px;
                margin: 0 auto;
                padding-top: 10px;
                height: 80px;
                background-color: white;
                font-family: 'Open Sans';
            }



        </style>    
        <script>
            function loader() {

                document.getElementById("typeselecter").value = ${type};

            }
            function onkeyPress(Id)
            {
                document.getElementById("hiddenText").value = Id.value;
            }

        </script>
    </head>
    <body onload="loader()">


        <div class="container-fluid">
              <div class="container" style="padding: 0;">
                <nav role="navigation" class="navbar navbar-default" style="margin-top: 30px;">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header" >
                        <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle" style="color: black">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="HomePage" class="navbar-brand" style="color: black;font-size: 25px;font-weight: bold"> 

                            Garanci
                        </a>


                    </div>
                    <!-- Collection of nav links, forms, and other content for toggling -->
                    <div id="navbarCollapse" class="collapse navbar-collapse">

                        <form role="search" class="navbar-form navbar-left">
                            <div class="form-group">
                                <input type="text" placeholder="Search" class="form-control">
                            </div>
                        </form>

                        <ul class="nav navbar-nav navbar-left">
                            <li> <a href=""><strong>Feature</strong></a></li>
                            <li  > <a href="#"   ><strong>Explore</strong></a></li>    
                            <li > <a href="#" ><strong>Solutions</strong></a></li>    
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="default?action=login"><strong>Login</strong></a></li>
                            <li><a href="default"><strong>Sign Up</strong></a></li>
                        </ul>
                    </div>
                </nav>
            </div>
            <hr/>
            <div class="container">
                <div class="row" >
                    <div class="col-sm-12"  >
                        <div >
                            <form action="searchResult?page=1" method="post"> 

                                <div class="col-sm-3 col-sm-offset-1" style="padding: 0;padding-top: 10px;">
                                    <select id="typeselecter"  name="type" class="form-control"  >
                                        <option value="0">Recent Recalls</option>
                                        <option value="1">Outdoor Products Recalls</option>
                                        <option value="2">Appliance Products Recalls</option>
                                        <option value="3">Clothing Products Recalls</option>
                                        <option value="4">Furniture Products Recalls</option>
                                        <option value="5">Household Products Recalls</option>
                                        <option value="6">Toddler Products Recalls</option>
                                        <option value="7">Sports Products Recalls</option>
                                        <option value="8">Electronics Products Recalls</option>
                                        <option value="9">Child Products Recalls</option>

                                    </select>
                                </div>
                                <div class="col-sm-6" style="padding: 0;padding-top: 10px;">
                                    <input type="text" class="form-control" value="${searchingText}" onkeyup="onkeyPress(this)"/>
                                    <input type="hidden" name="text" id="hiddenText">
                                </div>
                                <div class="col-sm-1" style="padding: 0;padding-top: 10px;">  <button class="btn btn-default" style="width: 100%;">Search</button></div>

                            </form>
                        </div>
                    </div>
                    <div class="col-sm-12" style="width: 100%;height: auto;;float: left;">


                        <c:forEach var="x" items="${Recall}">
                            <div class="productList" style="background-color: transparent">
                                <strong>RECALL</strong>&nbsp;&nbsp;|&nbsp;&nbsp;${x.date}<br/>
                                <p><a href="ProductDetail?Id=${x.Id}" >${x.title}</a></p>
                                <p>
                                    ${x.discription}
                                </p>
                            </div>
                        </c:forEach>

                    </div>
                    <div class="col-sm-12" style='float: left;' >
                        <ul class="pager">
                            <c:if test="${currentPage!=1}">
                                <li ><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage-1}">Previous</a></li>
                                </c:if>

                            <c:if test="${pageSize<=9}">
                                <c:forEach var="i" begin="1" end="${pageSize}">
                                    <li id="pageLi${i}" ><a  href="searchResult?type=${type}&text=${searchingText}&page=${i}">${i}</a></li>
                                    </c:forEach>


                            </c:if>
                            <c:if test="${pageSize>9}">
                                <c:choose>
                                    <c:when test="${currentPage<5}">
                                        <c:forEach var="i" begin="1" end="5">
                                            <li id="pageLi${i}"><a href="searchResult?type=${type}&text=${searchingText}&page=${i}">${i}</a></li>
                                            </c:forEach>
                                        <li id="pageLi${i}"><a href="searchResult?type=${type}&text=${searchingText}&page=${i}">...</a></li>
                                        <li id="pageLi${pageSize-1}"><a href="searchResult?type=${type}&text=${searchingText}&page=${pageSize-1}">${pageSize-1}</a></li>
                                        <li id="pageLi${pageSize}"><a href="searchResult?type=${type}&text=${searchingText}&page=${pageSize}">${pageSize}</a></li>

                                    </c:when>
                                    <c:when test="${currentPage>pageSize-5}">
                                        <li id="pageLi1"><a href="searchResult?type=${type}&text=${searchingText}&page=1">1</a></li>
                                        <li id="pageLi2"><a href="searchResult?type=${type}&text=${searchingText}&page=2">2</a></li>
                                        <li id="pageLi"><a >...</a></li>
                                            <c:forEach var="i" begin="${pageSize-5}" end="${pageSize}">
                                            <li id="pageLi${i}"><a href="searchResult?type=${type}&text=${searchingText}&page=${i}">${i}</a></li>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${(currentPage>=5)&&(currentPage<=pageSize-5)}">
                                        <li  id="pageLi1"><a href="searchResult?type=${type}&text=${searchingText}&page=1">1</a></li>
                                        <li id="pageLi2"><a href="searchResult?type=${type}&text=${searchingText}&page=2">2</a></li>
                                        <li id="pageLi"><a >...</a></li>
                                            <c:forEach var="i" begin="${currentPage-1}" end="${currentPage+1}">
                                            <li id="pageLi${i}"><a href="searchResult?type=${type}&text=${searchingText}&page=${i}">${i}</a></li>
                                            </c:forEach>
                                        <li><a >...</a></li>
                                        <li id="pageLi${pageSize-1}"><a href="searchResult?type=${type}&text=${searchingText}&page=${pageSize-1}">${pageSize-1}</a></li>
                                        <li id="pageLi${pageSize}"><a href="searchResult?type=${type}&text=${searchingText}&page=${pageSize}">${pageSize}</a></li>
                                        </c:when>
                                    </c:choose>
                                </c:if>

                            <c:if test="${currentPage!=pageSize}">
                                <li ><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage+1}">Next</a></li>
                                </c:if>


                        </ul>
                        <script>

                            document.getElementById('pageLi${currentPage}').childNodes[0].style.backgroundColor = "blue";
                            document.getElementById('pageLi${currentPage}').childNodes[0].style.color = "white";
                        </script>

                        <!--                <ul class="pager">
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">Previous</a></li>
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">1</a></li>
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">2</a></li>
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">...</a></li>
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">3</a></li>
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">4</a></li>
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">...</a></li>
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">5</a></li>
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">6</a></li>
                                            <li><a href="searchResult?type=${type}&text=${searchingText}&page=${currentPage}">Next</a></li>
                                        </ul>-->

                    </div>
                </div>
                <div class="row" style="background-color: white;">
                    <div class="container">
                        <div class="row" style="padding: 20px;" >
                            <div class="col-lg-3 col-sm-3 "  >

                                <ul style="margin: 0;padding: 0px;padding: 3px;">
                                    <li >
                                        <p > 565 Metro Pl South. STE 300<br/>
                                            Dublin, OH 43017<br/>
                                            Tel: 1-614-448-2473
                                        </p>   
                                    </li>



                                </ul>
                                <p>

                            </div>
                            <div class="col-lg-4 col-sm-4 " >
                                <ul style="margin: 0;padding: 0px;padding: 3px;">
                                    <li><a href="default?action=terms" class="ancer">Terms</a></li>
                                    <li><a href="default?action=privacy" class="ancer">Privacy policy</a></li>
                                    <li><a href="default?action=contact" class="ancer">Contact</a></li>
                                </ul>
                            </div>


                            <div class="col-lg-5 col-sm-5 " >
                                <ul style="margin: 0;padding: 0px;padding: 3px;">
                                    <li style="padding: 3px;"><a href="#getReportData" class="ancer">Report an Incident/Issue</a><span style="float: right">Business<a href="" class="ancer"><strong> Sign Up</strong></a>&nbsp;|&nbsp;<a href="" class="ancer"><strong>Log In</strong></a></span></li>

                                    <li style="padding: 3px;">Are you a retailer, manufacturer, distributor looking to  <a href="" class="ancer"><strong>Sign Up.</strong></a></li>
                                    <li style="padding: 3px;">Contact our team +1 (614) 448-2473</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
