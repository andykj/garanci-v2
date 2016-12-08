<%-- 
    Document   : productDetailPage
    Created on : 10 Aug, 2016, 4:47:13 PM
    Author     : zishan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> <link href="CSS/homepage.css" type="text/css" rel="stylesheet"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="CSS/bootstrap.min.css" type="text/css" rel="stylesheet"/>

        <link  href="css/productDetailPage.css" type="text/css" rel="stylesheet"/>
        <title>Connect and collaborate for safety and awareness - Garanci</title>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>
        <title>Connect and collaborate for safety and awareness - Garanci</title>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        <!--        <link href="http://fortawesome.github.io/Font-Awesome/ https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800" />-->

        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'/>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <style>
            body{
                font-family: sans-serif;
            }

            .table3
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

    </head>
    <body>



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
            <div class="container" style="padding: 0px;">
                <div class="row">

                    <div >
                        <h2>${res.title}</h2>


                        <table class="table1">
                            <tr>
                                <td style="width: 200px;vertical-align: top"><strong>Recall Id</strong></td>
                                <td>${res.recallId}</td>
                            </tr>
                            <tr>
                                <td style="width: 200px;vertical-align: top"><strong>Recall Number</strong></td>
                                <td>${res.recallNumber}</td>
                            </tr>
                            <tr>
                                <td style="width: 200px;vertical-align: top"><strong>Recall Date</strong></td>
                                <td>${res.recallDate}</td>
                            </tr>
                            <tr>
                                <td style="width: 200px;vertical-align: top" ><strong>Recall Descriptions</strong></td>
                                <td>${res.descriptions}</td>
                            </tr>
                            <tr>
                                <td style="width: 200px;vertical-align: top"><strong>Consumer Contact</strong></td>
                                <td>${res.consumerContact}</td>
                            </tr>
                            <tr>
                                <td style="width: 200px;vertical-align: top"><strong>Last Publish Date</strong></td>
                                <td>${res.lastPublishDate}</td>
                            </tr>

                            <tr>
                                <td style="width: 200px;vertical-align: top"><strong>Sold At Labels</strong></td>
                                <td>${res.soldAtLable}</td>
                            </tr>
                        </table>

                        <hr/>
                    </div>
                    <div   >




                        <p style="text-align: center;font-size: 22px;">

                            <strong>Products</strong>
                        </p>

                        <c:forEach var="procuts" items="${res.products}">
                            <table class="table1">
                                <tr>
                                    <td style="width: 200px;vertical-align: top"><strong>Product Name</strong></td>
                                    <td>${procuts.productName}</td>
                                </tr>
                                <tr>
                                    <td style="width: 200px;vertical-align: top"><strong>Products Descriptions</strong></td>
                                    <td>${procuts.productsDiscriptions}</td>
                                </tr>
                                <tr>
                                    <td style="width: 200px;vertical-align: top"><strong>Products Models</strong></td>
                                    <td>${procuts.productsModels}</td>
                                </tr>
                                <tr>
                                    <td style="width: 200px;vertical-align: top"><strong>Products Type</strong></td>
                                    <td>${procuts.productType}</td>
                                </tr>
                                <tr>
                                    <td style="width: 200px;vertical-align: top"><strong>Products Category Id</strong></td>
                                    <td>${procuts.productsCategoryId}</td>
                                </tr>
                                <tr>
                                    <td style="width: 200px;vertical-align: top"><strong>No Of Units</strong></td>
                                    <td>${procuts.productsNoOfUnits}</td>
                                </tr>
                                <tr>

                                    <td colspan="2"><hr/></td>
                                </tr>

                            </table>
                        </c:forEach>

                    </div>

                    <c:if test="${not empty res.hazard}" >
                        <div  >




                            <p style="text-align: center;font-size: 22px;">

                                <strong>Hazard</strong>
                            </p>

                            <c:forEach var="hazard" items="${res.hazard}">
                                <table class="table1">
                                    <tr>
                                        <td style="width: 200px;vertical-align: top"><strong>Hazard</strong></td>
                                        <td>${hazard.hazard}</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 200px;vertical-align: top"><strong>Hazard Type Id</strong></td>
                                        <td>${hazard.hazardTypeId}</td>
                                    </tr>
                                    <tr>

                                        <td colspan="2"><hr/></td>
                                    </tr>

                                </table>
                            </c:forEach>

                        </div>
                    </c:if>

                    <c:if test="${res.manufacCountry!=''}">


                        <div  >
                            <p style="text-align: center;font-size: 22px;">

                                <strong>Manufacturer Countries </strong>
                            </p>


                            <table class="table1">
                                <tr>
                                    <td style="width: 200px;vertical-align: top"><strong>Countries</strong></td>
                                    <td>${res.manufacCountry}</td>
                                </tr>

                                <tr>

                                    <td colspan="2"><hr/></td>
                                </tr>

                            </table>
                        </div>
                    </c:if>

                    <c:if test="${not empty res.injuries}" >

                        <div  >




                            <p style="text-align: center;font-size: 22px;">

                                <strong>Injuries</strong>
                            </p>

                            <c:forEach var="injuries" items="${res.injuries}">
                                <table class="table1">
                                    <tr>
                                        <td style="width: 200px;vertical-align: top"><strong>injuries</strong></td>
                                        <td>${injuries.injuries}</td>
                                    </tr>

                                    <tr>

                                        <td colspan="2"><hr/></td>
                                    </tr>

                                </table>
                            </c:forEach>

                        </div>
                    </c:if>
                    <c:if test="${not empty res.remedies}" >
                        <div  >




                            <p style="text-align: center;font-size: 22px;">

                                <strong>Remedies</strong>
                            </p>

                            <c:forEach var="remedies" items="${res.remedies}">
                                <table class="table1">
                                    <tr>
                                        <td style="width: 200px;vertical-align: top"><strong>injuries</strong></td>
                                        <td>${remedies.remedies}</td>
                                    </tr>

                                    <tr>

                                        <td colspan="2"><hr/></td>
                                    </tr>

                                </table>
                            </c:forEach>

                        </div>
                    </c:if>

                    <c:if test="${not empty res.retailer}" >
                        <div  >




                            <p style="text-align: center;font-size: 22px;">

                                <strong>Retailers</strong>
                            </p>

                            <c:forEach var="retailer" items="${res.retailer}">
                                <table class="table1">
                                    <tr>
                                        <td style="width: 200px;vertical-align: top"><strong>Retailers</strong></td>
                                        <td>${retailer.retailer}</td>
                                    </tr>

                                    <tr>

                                        <td colspan="2"><hr/></td>
                                    </tr>

                                </table>
                            </c:forEach>

                        </div>
                    </c:if>
                    <c:if test="${not empty res.upc}" >
                        <div  >
                            <p style="text-align: center;font-size: 22px;">

                                <strong>UPCs</strong>
                            </p>

                            <c:forEach var="upc" items="${res.upc}">
                                <table class="table1">
                                    <tr>
                                        <td style="width: 200px;vertical-align: top"><strong>UPC</strong></td>
                                        <td>${upc.upc}</td>
                                    </tr>

                                    <tr>

                                        <td colspan="2"><hr/></td>
                                    </tr>

                                </table>
                            </c:forEach>

                        </div>
                    </c:if>
                    <c:if test="${not empty res.manufacturer}" >

                        <div  >
                            <p style="text-align: center;font-size: 22px;">

                                <strong>Manufacturer</strong>
                            </p>

                            <c:forEach var="manufacturer" items="${res.manufacturer}">
                                <table class="table1">
                                    <tr>
                                        <td style="width: 200px;vertical-align: top"><strong>Name</strong></td>
                                        <td>${manufacturer.manufacturer}</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 200px;vertical-align: top"><strong>Company Id</strong></td>
                                        <td>${manufacturer.companyId}</td>
                                    </tr>

                                    <tr>

                                        <td colspan="2"><hr/></td>
                                    </tr>

                                </table>
                            </c:forEach>

                        </div>

                    </c:if>
                    <c:if test="${not empty res.images}" >
                        <div  >
                            <p style="text-align: center;font-size: 22px;">

                                <strong>Images</strong>
                            </p>
                            <table class="table1" style="align-self: auto">
                                <tr>
                                    <td colspan="2" >
                                        <c:forEach var="images" items="${res.images}">
                                            <div style="width: 200px;vertical-align: topmargin-left: 20px;float: left;">
                                                <img src="${images.url}" style="width: 100%;height: 100%;border: 1px solid rgba(0,0,0,0);margin-top: 5px;">
                                            </div>
                                        </c:forEach>
                                    </td>
                                </tr>

                            </table>

                        </div>
                    </c:if>

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
