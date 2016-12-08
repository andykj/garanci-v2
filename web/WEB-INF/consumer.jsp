<%-- 
    Document   : consumer
    Created on : 29 Nov, 2016, 10:59:29 AM
    Author     : zishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Connect and collaborate for safety and awareness - Garanci</title>

        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="CSS/bootstrap.min.css" type="text/css" rel="stylesheet"/>

        <script src='https://www.google.com/recaptcha/api.js'></script>

        <script src="js/bootstrap.min.js" type="text/javascript" ></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        <link href="http://fortawesome.github.io/Font-Awesome/ https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800" />

        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'/>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <script type="text/javascript" src="js/reportIncident.js"></script>

        <link href='css/jquery-ui.css' rel='stylesheet' type="text/css"/>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
          <script src="js/home.js" type="text/javascript"></script>

        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

    </head>

    <body>

        <div class="container-fluid" style="border-bottom: 1px solid rgba(0,0,0,0.2)">
            <div class="container" style="padding: 0">
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
                            <li  > <a href="ExploreGraph.jsp"   ><strong>Explore</strong></a></li>    
                            <li class=""> <a href="solutions.jsp" style="color: 3B5323" ><strong>Solutions</strong></a></li>    
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="default?action=login"><strong>Login</strong></a></li>
                            <li><a href="default"><strong>Sign Up</strong></a></li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <div class="container">
            <div  class="col-md-6" style="height: auto;background-color: red;">
                <div  class="col-sm-12" style="height: 500px;background-color: gray;">
                    <div id="dashboardBody">
                    </div>
                </div>
                <div  class="col-sm-12" style="height: 500px;background-color: yellow;"></div>
            </div>
            <div  class="col-md-6" style="height: 500px;background-color: pink;" ></div>
        </div>

    </body>
</html>
