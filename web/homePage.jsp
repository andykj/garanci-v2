<%-- 
    Document   : homePage
    Created on : 9 Aug, 2016, 4:09:44 PM
    Author     : zishan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Connect and collaborate for safety and awareness - Garanci</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="CSS/bootstrap.min.css" type="text/css" rel="stylesheet"/>
        <link href="CSS/homepage.css" type="text/css" rel="stylesheet"/>
        <script src="js/bootstrap.min.js" type="text/javascript" ></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        <!--        <link href="http://fortawesome.github.io/Font-Awesome/ https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800" />-->
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery.js"></script>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>
        <style type="text/css">


            .bs-example{
                margin: 20px;
            }
            .navbar-default {
                color: black;
                box-shadow: none;
                border: none;

                background-color: white;

            }
            body
            {
                overflow-x: hidden;
                font-family: 'Open Sans';
            }
            #loginsignup li a
            {
                color: white;
            }
            #loginsignup li a:hover
            {
                color: black;
            }


        </style>
    </head>
    <body>





        <div class="container-fluid" style="background-color: white;overflow: hidden;">
            <div class="row" style="background-color: white">



                <div class="container" style="background-color: white">
                    <div class="row" style="background-color: white;padding-top: 30px;padding-bottom:  10px;">

                        <nav role="navigation" class="navbar navbar-default">
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
                            <div id="navbarCollapse" class="collapse navbar-collapse" >

                                <form role="search" class="navbar-form navbar-left">
                                    <div class="form-group">
                                        <input type="text" placeholder="Search" style="border-radius: 0px;" class="form-control">
                                    </div>
                                </form>

                                <ul class="nav navbar-nav navbar-right" style="margin-right: -3px;">
                                     <li><a href="#"><strong>Explore</strong></a></li> 
                                    <li ><a href="#"><strong>Solutions&nbsp;</strong></a></li>
                                   

                                </ul>
                            </div>
                        </nav>
                    </div> 
                </div>



                <div class="row" style="background-color: black;">
                    <div class="container">
                        <div class="row" style="padding: 20px;" >
                            <div class="col-lg-8 col-sm-8 col-xs-8" style="padding: 20px;padding-bottom: 0px;;color: white">
                                <p>
                                    <strong>Without the heart, we are just another website.</strong><br/>
                                    Garanci has a heart to listen and encourage the way we communicate<br/>
                                    the awareness to protect our beloved ones from the defective products.<br/><br/><br/><br/>
                                    <strong>Products registered. Recalls published.</strong><br/>
                                    We are here to alert your product warranty expirations proactively.<br/>
                                    And also report, publish Unsafe product issues timely to prevent injuries.
                                    <br/><br/>




                                </p>
                            </div>
                            <div class="col-lg-4 col-sm-4 col-xs-4" >
                                <!--                                <div class="row">
                                                                    <div class="col-lg-6 col-sm-6 " style="padding: 20px;" >
                                                                        <a href="default?action=signup" style="font-size: 15px;float: right;color:  white;">Sign Up</a>
                                                                    </div> <div class="col-lg-6 col-sm-6 " style="padding: 20px;" >
                                                                        <a href="default?action=login" style="font-size: 15px;float: right;color:  white;">Log In</a>
                                                                    </div>
                                                                </div>-->

                                <ul class="nav navbar-nav navbar-right" id="loginsignup" style="color:  white;">
                                    <li >   <a href="default?action=signup" style="font-size: 15px;float: right;">Sign Up&nbsp;</a></li>
                                    <li> <a href="default?action=login" style="font-size: 15px;float: right;">Log In&nbsp;</a></li>              
                                </ul>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" style="background-color: #F4C430;border-bottom: 1px solid #daa80c;;border-top: 1px solid #daa80c">
                    <div class="container">
                        <div class="row">
                            <div class='col-sm-4 col-md-2 col-lg-2 textCenter' style="text-align: center" >
                                <div class='t2containermainimagecontainerimage' >
                                    <img src="images/Feature_Icons/Product Warranties digitized.png" class="images"/>
                                </div>
                                Product warranties digitized
                            </div>
                            <div class='col-sm-4 col-md-2 col-lg-2 textCenter' >
                                <div class='t2containermainimagecontainerimage' >
                                    <img src="images/Feature_Icons/Capture all on the go.png" class="images"/>
                                </div>
                                Capture all on the go
                            </div><div class='col-sm-4 col-md-2 col-lg-2 textCenter ' >
                                <div class='t2containermainimagecontainerimage' >
                                    <img src="images/Feature_Icons/Proactive. Alerts.png" class="images"/>
                                </div>
                                Proactive alerts
                            </div><div class=' col-sm-4 col-md-2 col-lg-2 textCenter' >
                                <div class='t2containermainimagecontainerimage' >
                                    <img src="images/Feature_Icons/Recalls. Published.png" class="images"/>
                                </div>
                                Recalls published
                            </div><div class=' col-sm-4 col-md-2 col-lg-2 textCenter'>
                                <div class='t2containermainimagecontainerimage' >
                                    <img src="images/Feature_Icons/Analytics. Everywhere.png" class="images"/>
                                </div>
                                Analytics everywhere
                            </div><div class=' col-sm-4 col-md-2 col-lg-2 textCenter' >
                                <div class='t2containermainimagecontainerimage' >
                                    <img src="images/Feature_Icons/Kids. Safety.png" class="images"/>
                                </div>
                                Kids safety
                            </div>


                        </div>
                    </div>
                </div>
                <div class="row" style="background-color:#d3d3d3;color: white;border-bottom: 1px solid #a1a1a1">
                    <div class="container">
                        <div class="row vdivide" style="padding: 20px;" >
                            <div class="col-lg-6 col-sm-6 " >

                                <label  style="padding: 10px;padding-left: 0px;color: black">Recent Recalls</label>
                                <form action="searchResult?page=1&type=0" method="post"> 
                                    <input type="text" class="form-control" name="text" style="border-radius: 0px;"  placeholder="search"/>
                                </form>
                                <br/>
                                <div class="row"   style="padding: 17px; "   >
                                    <div class="detailchild" style="padding: 0px;">
                                        <c:forEach var="x" items="${allRecall}">
                                            <div class="productList">
                                                <strong>RECALL</strong>&nbsp;&nbsp;|&nbsp;&nbsp;${x.date}<br/>
                                                <p><a   href="ProductDetail?Id=${x.Id}"  >${x.title}</a></p>
                                                <p>
                                                    ${x.discription}
                                                </p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>

                            </div>

                            <div class="visible-xs "  style="height: 1px;padding: 20px;padding-bottom: 40px;"><hr/></div>


                            <div class="col-lg-6 col-sm-6 "  >

                                <label style="padding: 10px;padding-left: 0px;color: black">Child Products Recalls</label>
                                <form action="searchResult?page=1&type=9" method="post"> 
                                    <input type="text" class="form-control" name="text"  style="border-radius: 0px;" placeholder="search"/>
                                </form>
                                <br/>
                                <div class="row"  style="padding: 17px; "   >
                                    <div class="detailchild" >
                                        <c:forEach var="x" items="${childRecall}">
                                            <div class="productList">
                                                <strong>RECALL</strong>&nbsp;&nbsp;|&nbsp;&nbsp;${x.date} <br/>
                                                <p><a href="ProductDetail?Id=${x.Id}" >${x.title}</a></p>
                                                <p>
                                                    ${x.discription}
                                                </p>
                                            </div>
                                        </c:forEach>

                                    </div>
                                </div>
                            </div>
                        </div>
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
