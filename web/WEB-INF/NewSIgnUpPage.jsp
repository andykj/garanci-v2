<%-- 
    Document   : NewSIgnUpPage
    Created on : 26 Oct, 2016, 3:04:29 PM
    Author     : zishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            .lisqu li{
                list-style: square;
            }
            .intl-tel-input
            {
                width: 100%;
            }


        </style>

        <script>

//            function SS()
//            {
//                if ($('#cntryCd').width() >= $('#phone').width())
//                {
//                    $('#cntryCd').width($('#cntryCd').parent().width());
//                }
//            }
            $(document).ready(function () {
                $("#toTop").click(function () {
                    $('html, body').animate({
                        scrollTop: $("#top-header1").offset().top
                    }, 1000);
                });
            });
        </script>
    </head>
    <body style="padding: 0;margin: 0;" onresize="SS();">
        <div class="container-fluid" style="padding: 0;">
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
            <div class="container-fluid" style="background-color: black;padding: 20px;">

                <div class="row" style="background-color: black;color: white" >
                    <div class="col-lg-7" style="color: white">
                        <div class="col-md-8" >
                            <div class="row">  <div class="col-lg-3 col-md-12 col-sm-3  center-block" style="padding: 20px;min-width: 150px;width: available">
                                    <img class="img-responsive center-block" src="images/Garanci_Logo.png" />
                                </div>

                                <div class="col-lg-9 col-md-12 col-sm-9" style="color: white;padding: 20px;margin: 0;" >
                                    <h1>  <span style="font-weight: bold;">  Warranties.<br>Nicely Managed.&trade;</span></h1>
                                </div>
                            </div>
                            <div class="row" style="padding: 20px;color: white">
                                <h3>Garanci helps to manage your household warranties.<br>
                                    Capture on the go and be in the know of expirations.</h3>
                            </div>



                        </div>
                        <div class="col-md-4" >
                            <form action="#" method="post" id="signUpForm" class="form-group" style="margin-top: 20px;">
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
                                        <input type="tel" class="form-control padding1" id="cntryCd" style="width: fit-content"   required name="countryCode" onblur="checkCountryCode();" readonly>
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

                    <div class="col-lg-5" >
                        <div class="col-md-12" >
                            <h3><strong>Challenges</strong></h3>
                            <hr/>
                            <ul class="lisqu" >
                                <li  ><strong>No Single Place</strong> to store multiple product warranties</li>
                                <li>Warranties information <strong> not "track able" and "searchable"</strong> </li>
                                <li>No easy access <strong>to extended warranties and maintenance providers</strong></li>
                                <li>Consumers <strong> paying for what is covered </strong>in warranties</li>
                                <li> <strong>Filling warranties claims </strong>is a pain </li>
                                <li> <strong>Product Recalls</strong> information not reaching the consumers </li>
                            </ul>

                        </div>
                        <div class="col-md-12" >
                            <h3><strong>Benefits</strong></h3>
                            <hr/>
                            <ul class="lisqu" >
                                <li  ><strong>No Single Place</strong> to store multiple product warranties</li>
                                <li>Warranties information <strong> not "track able" and "searchable"</strong> </li>
                                <li>No easy access <strong>to extended warranties and maintenance providers</strong></li>
                                <li>Consumers <strong> paying for what is covered </strong>in warranties</li>
                                <li> <strong>Filling warranties claims </strong>is a pain </li>
                                <li> <strong>Product Recalls</strong> information not reaching the consumers </li>
                            </ul>

                        </div>


                    </div>

                </div>
                <div class="col-md-12" style="color: white" >
                    <h5><strong>"Its awful to find those receipts and warranties docs , know who to call,what's covered,etc "</strong></h5>
                    <h5><strong>"Its intimidation not being aware of the recalls on the things we consume daily for the safety of our friend and family "</strong></h5>
                </div>

            </div>
            <div class="container" style="padding: 20px;">
                <div style="text-align: center"><h4><strong>Value Added Solutions</strong></h4></div>
                <div>
                    <table class="table">
                        <tr style="background-color: graytext;color: white">
                            <td><strong>Feature</strong></td>
                            <td><strong>Silver</strong></td>
                            <td><strong>Gold</strong></td>
                            <td><strong>Platinum</strong></td>
                        </tr>
                        <tr>
                            <td>Purchase receipt capture (self)</td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Purchase receipt capture (digitized)</td>
                            <td></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Rate the return service</td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Warranty docs search</td>
                            <td></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Proactive alerts (warranties , recalls)</td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Notify an unsafe product</td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Child safety education</td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Recalls alerts for protections</td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>One stop warranty extention</td>
                            <td></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Write reviews (return and service)</td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Personal dashboards</td>
                            <td></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr>
                            <td>Online service request</td>
                            <td></td>
                            <td></td>
                            <td><img src="images/Christmas Star-25.png"></td>
                        </tr>
                        <tr style="background-color: graytext;color: white">
                            <td><strong>Yearly plans</strong></td>

                            <td><strong>Free</strong></td>
                            <td><strong>$14.99</strong></td>
                            <td><strong>$49.99</strong></td>
                        </tr>
                    </table>
                </div>
                <div>
                    <br/><br/><br/>
                    <p>How you will use value added solutions?        <span style="float: right"><strong>call us at +1(614)448-2473.</strong></span></p>

                    <p>Garanci is the word-class secure website for household members and new born parents</p>
                    <p>to enroll the products of interest or purchased to receive the recall alerts and to protect the beloved ones from injuries.</p>
                    <p>Awareness of education at your finger tips on your mobile device.</p>
                    <p>Consumer also benefit with notification alert on product warranty expiration so timely service can be scheduled before warranty expire</p>
                    <p><strong>For inquiries call</strong><br/>+1(614)448-2473.</p>
                </div>
            </div>
        </div>
        <hr/>

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
                        <li style="padding: 3px;"><a href="getReportData" class="ancer">Report an Incident/Issue</a><span style="float: right">Business<a href="" class="ancer"><strong> Sign Up</strong></a>&nbsp;|&nbsp;<a href="" class="ancer"><strong>Log In</strong></a></span></li>

                        <li style="padding: 3px;">Are you a retailer, manufacturer, distributor looking to  <a href="" class="ancer"><strong>Sign Up.</strong></a></li>
                        <li style="padding: 3px;">Contact our team +1 (614) 448-2473</li>
                    </ul>
                </div>
            </div>
        </div>


    </body>
</html>
