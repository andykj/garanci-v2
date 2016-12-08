<%-- 
    Document   : signup
    Created on : 14 Sep, 2016, 3:59:16 PM
    Author     : zishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Connect and collaborate for safety and awareness - Garanci</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="CSS/bootstrap.min.css" type="text/css" rel="stylesheet"/>
        <link href="CSS/homepage.css" type="text/css" rel="stylesheet"/>
        <script src="js/bootstrap.min.js" type="text/javascript" ></script>
        <link href='css/login.css' rel='stylesheet' type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/intlTelInput.css" media="all">
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="images/Garanci_Logo.png" />
        <meta http-equiv="cache-control" content="max-age=0" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="X-UA-Compatible" content="IE=8" />
        <meta name="MobileEnabled" content="yes"/>
        <meta name="ROBOTS" content="FOLLOW,INDEX"/>
        <meta name="keywords" content="manage warranties, track warranties, renew warranties">
        <meta name="copyright" content="Garanci Inc">
        <script type='text/javascript' src='js/jquery.html5-placeholder-shim.js'></script>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>

        <link href='css/jquery-ui.css' rel='stylesheet' type="text/css"/>
        <link href='css/login.css' rel='stylesheet' type="text/css"/>
        <script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script type='text/javascript' src='js/detectmobilebrowser.js'></script>
        <script src="js/garanci.js" type="text/javascript"></script>

        <link rel="stylesheet" href="css/intlTelInput.css" media="all">
        <meta content="Garanci lets you keep all your warranties at one place securely as well as track your expirations and provide a seamless integration with partners for highest quality service." name="description">
        <title> Garanci Official Website - Warranties. Nicely Managed.</title>


        <style>
            body{
                padding: 0px;
                margin: 0px;

            }
            .padding0
            {
                padding: 0px;
                padding: 5px;
                padding-right: 3px;
            }
            .padding1
            {
                height: 40px;
            }
            #rowMiddleImg1,#rowMiddleImg
            {

                height:100px;
                width: 100px;
                margin: 0 auto;
                border-radius: 100px;
                border: 1px solid black;
                padding: 23px;
            }
            #middleIcons
            {
                margin: 0 auto;
            }
            .PADDINF50
            {
                padding: 50px;
            }


        </style>

        <script>
            $(document).ready(function () {
                $("#toTop").click(function () {
                    $('html, body').animate({
                        scrollTop: $("#top-header1").offset().top
                    }, 1000);
                });
            });
        </script>

    </head>
    <body>


        <div class="container-fluid" id="top-header1" >

            <div class="row-fluid">

                <div class="container">
                    <nav role="navigation" class="navbar navbar-default" style="padding-top: 20px;">
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

                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="default?action=login"><strong>Sign In</strong></a></li>
                                <li><a href="https://garanci.wordpress.com/ "><strong>Blog</strong></a></li> 
                                <li><a href="#dotowhy" name='dotowhy'><strong>Features</strong></a></li> 
                            </ul>
                        </div>
                    </nav>
                </div>

            </div>

            <div class="row" style="background-color: black">

                <div class="container">

                    <div class="row">
                        <div class="col-lg-8 col-sm-8 col-md-8" style="padding: 20px;">
                            <div class="row">
                                <div class="col-lg-3 col-md-3 " style="padding: 20px;">
                                    <img class="img-responsive center-block" src="images/Garanci_Logo.png"  />
                                </div>
                                <div class="col-lg-9 col-md-9 visible-lg visible-md  " style="color: white;padding: 20px;margin: 0;" >
                                    <h1>  <span style="font-weight: bold;">  Warranties.<br>Nicely Managed.&trade;</span></h1>
                                </div>
                                <div class="visible-xs visible-sm text-center" style="color: white;padding: 20px;margin: 0;" >
                                    <h1>  <span style="font-weight: bold;">  Warranties.<br>Nicely Managed.&trade;</span></h1>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12 visible-lg visible-md" style="padding: 20px;color: white">
                                    <h3>Garanci helps to manage your household warranties.<br>
                                        Capture on the go and be in the know of expirations.</h3>
                                </div>
                                <div class="visible-sm visible-xs text-center" style="padding: 20px;color: white">
                                    <h3>Garanci helps to manage your household warranties.<br>
                                        Capture on the go and be in the know of expirations.</h3>
                                </div>
                            </div>

                        </div>

                        <div class="col-lg-4 col-sm-4 col-md-4" style="color: white;padding: 20px;">
                            <form action="#" method="post" id="signUpForm" class="form-group">
                                <div class="text-center" id="errorDiv">${requestScope.errorMsg}</div>

                                <div class="row">
                                    <div class="col-lg-6 padding0">
                                        <input type="text" class="form-control padding1" id="firstName" name="firstName" placeholder="First Name" value="${sessionScope.firstName}" onblur="checkFirstName();" required>
                                    </div>
                                    <div class="col-lg-6 padding0" >
                                        <input type="text" class="form-control padding1" id="lastName" name="lastName" placeholder="Last Name" value="${sessionScope.lastName}" onblur="checkLastName();" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-3 col-sm-3 col-xs-3 padding0" style="color: black;">
                                        <input type="tel" class="form-control padding1" id="cntryCd" style="min-width: max-content"   required name="countryCode" onblur="checkCountryCode();" readonly>
                                    </div> <div class="col-lg-9 col-sm-9 col-xs-9 padding0">
                                        <input type="text" class="form-control padding1" id="phone" name="phone" placeholder="Mobile" value="${sessionScope.phone}" onblur="checkPhone();" onkeyup="loadingAnimation('phone');" required>
                                    </div>
                                </div>
                                <div class="row padding0">
                                    <input type="text" class="form-control padding1" id="email" name="email" placeholder="Email" value="${sessionScope.email}" onblur="checkEmail();" onkeyup="loadingAnimation('email');" required>
                                </div>
                                <div class="row padding0" >
                                    <input type="password" class="form-control padding1" id="password" name="password" placeholder="Create Your Password" onblur="checkPassword()"required maxlength="20">
                                </div>
                                <div class="row padding0">
                                    <div id="reCaptcha"></div>
                                </div>
                                <div class="row padding0">
                                    <button type="button" id="signUpButton" onclick="signUp();"><strong>Sign Up</strong></button>
                                </div>
                            </form>
                            <script src="js/intlTelInput.js"></script>
                            <script>

                                        $(document).ready(function () {
                                            $(window).scroll(function () {
                                                if ($(this).scrollTop() > 50) {
                                                    $('#back-to-top').fadeIn();
                                                } else {
                                                    $('#back-to-top').fadeOut();
                                                }
                                            });
                                            // scroll body to 0px on click
                                            $('#back-to-top').click(function () {
                                                $('#back-to-top').tooltip('hide');
                                                $('body,html').animate({
                                                    scrollTop: 0
                                                }, 800);
                                                return false;
                                            });

                                            $('#back-to-top').tooltip('show');

                                        });
                                        $("#feature").click(function () {
                                            $('html, body').animate({
                                                scrollTop: $("#containerMiddle").offset().top
                                            }, 1000);
                                        });
                                        $("#firstName").keydown(function (e) {
                                            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                                    (e.keyCode == 65 && e.ctrlKey === true) ||
                                                    (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                return;
                                            }
                                            if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#lastName").keydown(function (e) {
                                            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                                    (e.keyCode == 65 && e.ctrlKey === true) ||
                                                    (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                return;
                                            }
                                            if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#phone").keydown(function (e) {
                                            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                                    (e.keyCode == 65 && e.ctrlKey === true) ||
                                                    (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                return;
                                            }
                                            if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $(document).ready(function () {
                                            function add() {
                                                if ($(this).val() === '') {
                                                    $(this).val($(this).attr('placeholder')).addClass('placeholder');
                                                }
                                            }
                                            function remove() {
                                                if ($(this).val() === $(this).attr('placeholder')) {
                                                    $(this).val('').removeClass('placeholder');
                                                }
                                            }


                                            if (!('placeholder' in $('<input>')[0])) {

                                                // Select the elements that have a placeholder attribute
                                                $('input[placeholder], textarea[placeholder]').blur(add).focus(remove).each(add);

                                                // Remove the placeholder text before the form is submitted
                                                $('form').submit(function () {
                                                    $(this).find('input[placeholder], textarea[placeholder]').each(remove);
                                                });
                                            }
                                        });

                                        $("#cntryCd").intlTelInput({
                                        });
                            </script>
                            <div class="row" style="padding: 5px;">


                                <p >By signing up you agree to the garanci
                                    <a href="default?action=terms">terms</a> and you have read our <a href="default?action=privacy">privacy policy.</a>
                                    <br>We use your email and mobile
                                    number to send you warranty expiration
                                    alerts and renewal options</p>
                            </div>
                        </div>
                    </div>

                </div>

            </div>



            <div class="row" style="background-color: white">

                <div class="container" id='dotowhy'>
                    <div class="row text-center PADDINF50">
                        <h1><strong>Why you’ll love Garanci.</strong></h1>
                        <h3> <p>Powerful features to manage household warranties</p>
                        </h3>
                    </div>
                    <div class="row text-center">
                        <div class="col-lg-1 col-md-1">

                        </div>
                        <div class="col-lg-5 col-md-5 PADDINF50">

                            <div class="clearfix center-block">
                                <div id="rowMiddleImg1"><img id="middleIcons" src="images/list.png" class="img-responsive"></div>

                            </div>
                            <h3>&nbsp;Warranties. Nicely managed.</h3>
                            <p>Manage and view household warranties in one place</p>

                        </div>
                        <div class="col-lg-5 col-md-5 PADDINF50">
                            <div id="rowMiddleImg"><img id="middleIcons" src="images/bookmark.png" class="img-responsive"></div><h3>&nbsp; Capture on the go.</h3>
                            <p>Manage and view household warranties in one place</p>
                        </div>
                        <div class="col-lg-1 col-md-1">

                        </div>
                    </div>


                    <div class="row text-center">
                        <div class="col-lg-1 col-md-1">

                        </div>
                        <div class="col-lg-5 col-md-5 PADDINF50">

                            <div class="clearfix center-block">
                                <div  id="rowMiddleImg"><img id="middleIcons" src="images/tachometer.png" class="img-responsive"></div>

                            </div>
                            <h3>Expiration alerts.</h3>
                            <p>Be in the know of warranty expirations and 
                                get the timely service done.</p>

                        </div>
                        <div class="col-lg-5 col-md-5 PADDINF50">
                            <div id="rowMiddleImg"><img id="middleIcons" src="images/random.png" class="img-responsive"></div><h3>&nbsp;Integration.</h3>
                            <p>Seamless integration with partners for highest
                                quality service. Communicate with ease.</p>
                        </div>
                        <div class="col-lg-1 col-md-1">

                        </div>
                    </div>

                </div>

            </div>
            <div class="row">
                <div class="container" >
                    <div class="row"><hr/></div>
                </div>
            </div>
            <div class="row" style="background-color: white">

                <div class="container">
                    <div class="row text-center PADDINF30">
                        <h1><strong >The easiest way to use Garanci on iOS.</strong></h1>

                    </div>
                    <div class="row text-center">
                        <button type="button" class="btn btn-default" id="iosDownButton" style="max-width: 100vw" onclick="goToAppleStore();"><strong>Download Garanci for iPhone</strong></button>
                    </div>

                </div>

            </div>
            <div class="row" style="background-color: white">

                <div class="container">

                    <div class="row"  style="padding-top: 20px;">
                        <div class="col-sm-3" style="margin-top: 10px;" ><img src="images/IosScreenShot/ScreenShot1.PNG" class="img-responsive"></div>
                        <div class="col-sm-3" style="margin-top: 10px;" ><img src="images/IosScreenShot/ScreenShot2.PNG" class="img-responsive"></div>

                        <div class="col-sm-3" style="margin-top: 10px;" ><img src="images/IosScreenShot/ScreenShot4.PNG" class="img-responsive"></div>
                        <div class="col-sm-3"  style="margin-top: 10px;"><img src="images/IosScreenShot/ScreenShot4.PNG" class="img-responsive"></div>
                    </div>

                </div>


            </div>

            <div class="row" style="background-color: white;margin-top: 100px;">

                <div class="container">

                    <div class="row"  style="padding-top: 20px;">

                        <div class="col-lg-4 col-sm-4 " >
                            <div class="row" >

                                <div class="col-lg-6 col-sm-6 " ><a class="footerInsideRight" href="default?action=contact">&COPY;2014 Garanci.</a></div>
                                <div class="col-lg-3 col-sm-3 " ><a  href="default?action=terms" class="footerInsideRight">Terms</a></div>
                                <div class="col-lg-3 col-sm-3 " ><a href="default?action=privacy" class="footerInsideRight">Privacy</a></div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-sm-4 col-sm-push-4 col-lg-push-4">
                            <div class="row" >
                                <div class="col-lg-6 col-sm-6 " ><a href="default?action=contact" class="footerInsideRight">Contact</a></div>
                                <div class="col-lg-3 col-sm-3 " ><a href="https://garanci.wordpress.com" target="_blank" class="footerInsideRight">Blog</a></div>
                                <div class="col-lg-3 col-sm-3 " style="" ><a href="default?action=about" style="color: #AAA" class="">About</a></div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-4 col-sm-pull-4 col-lg-pull-4" >
                            <div class="row">

                                <div class="col-lg-2 col-sm-2 col-xs-2 col-lg-offset-5 col-md-offset-5 col-sm-offset-5 col-xs-offset-5" > <img src="images/Garanci_Logo.png" id="GaranciFooterLogo" style="margin: 0 auto"/></div>

                            </div>
                            <div class="row" >
                                <div class="text-center">  <a class="version">1.0.1.0, Developed by </a><a href="http://www.aspiringwings.com" target="_blank" class="companyRef">Aspiring Wings Technologies.</a></div>
                            </div>

                        </div>

                    </div>

                </div>

                <div class="text-right"><img id="toTop" src="images/top-arrow.png"/></div>
            </div>

        </div>

    </body>
</html>

