<%-- 
    Document   : home
    Created on : 8 Aug, 2014, 4:56:39 PM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
    <head>
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
        <link href='css/bootstrap-theme.min.css' rel='stylesheet' type="text/css"/>
        <link href='css/bootstrap.min.css' rel='stylesheet' type="text/css"/>
        <link href='css/jquery-ui.css' rel='stylesheet' type="text/css"/>
        <link href='css/login.css' rel='stylesheet' type="text/css"/>
        <script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script type='text/javascript' src='js/detectmobilebrowser.js'></script>
        <script src="js/garanci.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/intlTelInput.css" media="all">
        <meta content="Garanci lets you keep all your warranties at one place securely as well as track your expirations and provide a seamless integration with partners for highest quality service." name="description">
        <title>Connect and collaborate for safety and awareness - Garanci</title>
        <script>
            $(document).ready(function () {
                var vpw = (screen.width >= 768) ? '960' : 'device-width';
                if (vpw == 960)
                {
                    $('head').prepend('<meta name="viewport" content="width=' + vpw + ',initial-scale=0.8"/>');
                }
                else
                {
                    $('head').prepend('<meta name="viewport" content="width=960px, initial-scale=0.33"/>');
                }
            });
        </script>
        <style>

            li
            {
                list-style: none;
            }
            a{
                color: black; 
            }

        </style>
    </head>
    <body>
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-56475428-1', 'auto');
            ga('send', 'pageview');

        </script>
        <div id="top-header"> 
            <div id="header">
                <div class="logo">
                    <a href="HomePage">Garanci</a>
                </div>
                <c:if test="${requestScope.current.equals('all')}">
                    <div class="headerInside" id="headerInsideRight">
                        <a href="default?action=login" class="headerInsideRight">Sign In</a>
                        <a href="https://garanci.wordpress.com" target="_blank" class="headerInsideRight">Blog</a>
                        <a href="#" id="feature" class="headerInsideRight">Features</a>
                    </div>
                </c:if>
                <c:if test="${requestScope.current.equals('login')}">
                    <div class="headerInside" id="headerInsideRight">
                        <a href="default?action=login" class="headerInsideRight">Sign In</a>
                        <a href="default" class="headerInsideRight">Sign Up</a>
                    </div>
                </c:if>
                <c:if test="${requestScope.current.equals('contact')}">
                    <div class="headerInside" id="headerInsideRight">
                        <a href="default?action=login" class="headerInsideRight">Sign In</a>
                        <a href="default" class="headerInsideRight">Sign Up</a>
                    </div>
                </c:if>
                <c:if test="${requestScope.current.equals('forgotPassword')}">
                    <div class="headerInside" id="headerInsideRight">
                        <a href="default?action=login" class="headerInsideRight">Sign In</a>
                        <a href="default" class="headerInsideRight">Sign Up</a>
                    </div>
                </c:if>
                <c:if test="${requestScope.current.equals('reset')}">
                    <div class="headerInside" id="headerInsideRight">
                        <a href="default?action=login" class="headerInsideRight">Sign In</a>
                        <a href="default" class="headerInsideRight">Home</a>
                    </div>
                </c:if>
                <!--<c:if test="${requestScope.current.equals('about')}">
                   <div class="headerInside" id="headerInsideRight">
                        <a href="default?action=login" class="headerInsideRight">Sign In</a>
                        <a href="default" class="headerInsideRight">Sign Up</a>
                    </div>
                </c:if>
                <c:if test="${requestScope.current.equals('terms')}">
                   <div class="headerInside" id="headerInsideRight">
                        <a href="default?action=login" class="headerInsideRight">Sign In</a>
                        <a href="default" class="headerInsideRight">Sign Up</a>
                    </div>
                </c:if>
                <c:if test="${requestScope.current.equals('privacy')}">
                   <div class="headerInside" id="headerInsideRight">
                        <a href="default?action=login" class="headerInsideRight">Sign In</a>
                        <a href="default" class="headerInsideRight">Sign Up</a>
                    </div>
                </c:if>-->
            </div>
        </div>
        <c:if test="${requestScope.current.equals('all')}">
            <div id="main-container1">
                <div id="main-container"> 
                    <div class="container" id="container">
                        <div class="row-fluid" id="signUpContainer">
                            <div class="col-xs-8 col-sm-6">
                                <div class="row" id="homeTitle">
                                    <img class="img-responsive" src="images/Garanci_Logo.png" id="Imglogo"/>
                                    <div class="col-sm-10" id="imageSideQuote">
                                        Warranties.<br>Nicely Managed.<div id="tradeMark">TM</div>
                                    </div>
                                </div>
                                <div class="row" id="imageBottomQuote">
                                    <h3>Garanci helps to manage your household warranties.<br>
                                        Capture on the go and be in the know of expirations.</h3>
                                </div>
                            </div>   

                            <div class="col-md-9" id="signUpDiv">
                                <form action="#" method="post" id="signUpForm" class="form-group">
                                    <div class="text-center" id="errorDiv">${requestScope.errorMsg}</div>
                                    <input type="text" class="inputField" id="firstName" name="firstName" placeholder="First Name" value="${sessionScope.firstName}" onblur="checkFirstName();" required>
                                    <input type="text" class="inputField" id="lastName" name="lastName" placeholder="Last Name" value="${sessionScope.lastName}" onblur="checkLastName();" required>
                                    <input type="tel" class="cntryCode" id="cntryCd" required name="countryCode" onblur="checkCountryCode();" readonly>
                                    <input type="text" class="contactNum" id="phone" name="phone" placeholder="Mobile" value="${sessionScope.phone}" onblur="checkPhone();" onkeyup="loadingAnimation('phone');" required>
                                    <input type="text" class="emailId" id="email" name="email" placeholder="Email" value="${sessionScope.email}" onblur="checkEmail();" onkeyup="loadingAnimation('email');" required>
                                    <input type="password" class="userPassword" id="password" name="password" placeholder="Create Your Password" onblur="checkPassword()"required maxlength="20">
                                    <div id="reCaptcha"></div>
                                    <button type="button" id="signUpButton" onclick="signUp();"><strong>Sign Up</strong></button>
                                </form>
                                <script src="js/intlTelInput.js"></script>
                                <script>
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
                                <p id="terms">By signing up you agree to the garanci
                                    <a href="default?action=terms">terms</a> and you have read our <a href="default?action=privacy">privacy policy.</a>
                                    <br>We use your email and mobile
                                    number to send you warranty expiration
                                    alerts and renewal options</p>
                            </div>
                        </div>
                    </div>              
                </div>
                <div class="container" id="container">                
                    <div class="container-fluid" id="containerMiddle">
                        <div class="page-header text-center" id="about">
                            <h3>Why you’ll love Garanci.</h3>
                            <p>Powerful features to manage household warranties</p>
                        </div>
                        <div class="container text-center" id="container2">
                            <div class="row-fluid" id="rowMiddleId">
                                <div class="col-xs-6 col-sm-3 row text-center" id="insideRow">
                                    <div id="rowMiddleImg"><img id="middleIcons" src="images/list.png" class="img-responsive"></div><h3>Warranties. Nicely managed.</h3>
                                    <p>Manage and view household warranties in one place</p>
                                </div>
                                <div class="col-xs-6 col-sm-3 row text-center" id="insideRow">
                                    <div id="rowMiddleImg"><img id="middleIcons" src="images/bookmark.png" class="img-responsive"></div><h3>Capture on the go.</h3>
                                    <p>Capture your warranty receipt on the go.</p>
                                </div>
                                <div class="col-xs-6 col-sm-3  row text-center" id="insideRow">
                                    <div  id="rowMiddleImg"><img id="middleIcons" src="images/tachometer.png" class="img-responsive"></div><h3>Expiration alerts.</h3>
                                    <p>Be in the know of warranty expirations and <br>
                                        get the timely service done.</p>
                                </div>
                                <div class="col-xs-6 col-sm-3 row text-center" id="insideRow">
                                    <div id="rowMiddleImg"><img id="middleIcons" src="images/random.png" class="img-responsive"></div><h3>Integration</h3>
                                    <p>Seamless integration with partners for highest<br>
                                        quality service. Communicate with ease.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid" id="iosContainer">
                        <div class="row-fluid">
                            <div class="page-header text-center" id="about">
                                <h3>The easiest way to use Garanci on iOS.</h3>
                            </div>
                            <div class="text-center">
                                <button type="button" class="btn btn-default" id="iosDownButton" onclick="goToAppleStore();"><strong>Download Garanci for iPhone</strong></button>
                            </div>

                            <div class="row-fluid" id="IosSnapShort">
                                <div class="col-xs-6 col-sm-3" id="IosApp"><img src="images/IosScreenShot/ScreenShot1.PNG" class="img-responsive"></div>
                                <div class="col-xs-6 col-sm-3" id="IosApp"><img src="images/IosScreenShot/ScreenShot2.PNG" class="img-responsive"></div>
                                <div class="col-xs-6 col-sm-3" id="IosApp"><img src="images/IosScreenShot/ScreenShot3.PNG" class="img-responsive"></div>
                                <div class="col-xs-6 col-sm-3" id="IosApp"><img src="images/IosScreenShot/ScreenShot4.PNG" class="img-responsive"></div>
                            </div>
                        </div>
                    </div>	
                </div>
                <div class="text-right" id="toTopDiv"><img id="toTop" src="images/top-arrow.png"/></div>
            </div>      	
        </c:if>

        <c:if test="${requestScope.current.equals('login')}">
            <div id="main-container">
                <div class="container" id="container2">
                    <div class="row-fluid" id="insideContainer">
                        <div class="text-center" id="ErrorDiv">${requestScope.errorMsg}</div>
                        <div class="register span6" id="logInDiv">
                            <form action="" onsubmit="signIn();
                                                        return false" method="">
                                <h2>Sign In</h2>

                                <div class="form-group" id="formText">
                                    <label for="email">Email or Mobile number</label>
                                    <input type="text" id="email" name="email" class="form-control" onblur="CheckEmail('ErrorDiv');"tabindex="1"/>
                                </div>
                                <div class="form-group" id="formText">
                                    <label for="password">Password<a  id="forgotPassword" href="default?action=forgotPassword">(forgot password)</a></label>
                                    <input type="password" id="password" name="password" class="form-control" onblur="CheckPassword('ErrorDiv');
                                                                " tabindex="2"/>
                                </div>
                                <button type="submit" class="btn btn-default" id="signInButton"><strong>Sign In</strong></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>                       
        </c:if>
        <c:if test="${requestScope.current.equals('forgotPassword')}">
            <div id="main-container">
                <div class="container" id="container2">
                    <div class="row-fluid" id="insideContainer1">
                        <div class="register span6" id="logInDiv">
                            <form method="post" onsubmit="forgotPassword();
                                                    return false" name="resetForm">
                                <h2 id="success">Forgot Password</h2>
                                <div id="ErrorDiv1">${requestScope.errorMsg}</div>
                                <div class="form-group" id="formText">
                                    <label for="email">Email</label>
                                    <input type="text" id="email" class="form-control" name="email" placeholder="Enter your email or mobile" onblur="CheckEmail('ErrorDiv');" value="${requestScope.email}"/>
                                </div>
                                <div id="resetHideButton"> 
                                    <button type="submit" class="btn btn-default" id="signInButton" id="resetButton"><strong>Reset</strong></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>                   
        <c:if test="${requestScope.current.equals('reset')}">
            <div id="main-container">
                <div class="container" id="container2">
                    <div class="row-fluid" id="insideContainer">
                        <div class="text-center" id="ErrorDiv">${requestScope.errorMsg}</div>
                        <div class="register span6" id="logInDiv">
                            <form action="" method="">
                                <h2>Reset Password</h2>
                                <div class="form-group" id="formText">
                                    <label for="email">Email</label>
                                    <input type="text" id="email" class="form-control" name="email" placeholder="Email or mobile number" value="${requestScope.email}" readonly/>
                                </div>
                                <div class="form-group" id="formText">
                                    <label for="password">Password</label>
                                    <input type="password" id="password" class="form-control" name="password" placeholder="Password" onblur="CheckResetPassword('ErrorDiv');" required maxlength="20"/>
                                </div>
                                <div class="form-group" id="formText">
                                    <label for="rePassword">Re-Password</label>
                                    <input type="password" id="rePassword" class="form-control" name="rePassword" placeholder="Re-Password" onblur="checkPasswordRePassword('ErrorDiv');" required maxlength="20"/>
                                    <input type="hidden" id="code" name="code" value="${requestScope.code}"/>
                                </div>
                                <button type="button" class="btn btn-default" onclick="resetPassword();" id="resetButton"><strong>Reset</strong></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>              
        </c:if>
        <c:if test="${requestScope.current.equals('contact')}">
            <div id="main-container">                              
                <div class="container" id="container2">
                    <div class="row-fluid" id="insideContainer">
                        <div class="text-center" id="ErrorDiv">${requestScope.errorMsg}</div>
                        <div class="register span6" id="logInDiv">
                            <form action="#" method="post">
                                <h2>Contact Us</h2>
                                <p>We are here to help with any questions or comments.</p>

                                <div class="form-group" id="formText">
                                    <label class="sr-only" for="textbox">Name</label>
                                    <input type="text" class="form-control" id="name" placeholder="Name" onclick="hideWarning('name');" value="" >
                                </div>
                                <div class="form-group" id="formText">
                                    <label class="sr-only" for="textbox">Email</label>
                                    <input type="text" class="form-control" id="email" placeholder="Email" onclick="hideWarning('email');" value="" >
                                </div>
                                <div class="form-group" id="formText">
                                    <label class="sr-only" for="textbox">Mobile></label>
                                    <input type="text" class="form-control" id="mobile" placeholder="Mobile" onclick="hideWarning('mobile');" value="" >
                                </div>
                                <div class="form-group" id="formText">
                                    <label class="sr-only" for="textbox">Subject</label>
                                    <input type="text" class="form-control" id="subject" placeholder="Subject" onclick="hideWarning('subject');" value="" >
                                </div>
                                <div class="form-group" id="formText">
                                    <textarea value="" class="form-control" id="message" placeholder="Message" ></textarea>
                                </div>
                                <button type="button" class="btn btn-default" id="signInButton" onclick="contactUs();"/><strong>Send Message</strong></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>                           
        </c:if>
        <c:if test="${requestScope.current.equals('about')}">
            <div id="main-container">
                <div class="container" id="container2">
                    <div class="row-fluid" id="insideContainer">
                        <div class="about span6" id="logInDiv">

                            <h3>About Garanci website</h3><br>
                            <p>Garanci is the secure place for all your 
                                warranties in one place. The cool features 
                                of the Garanci would help to keep all your 
                                warranties at one place digitally and be in 
                                the know of expirations so you can plan on scheduling 
                                tune up services/ on time service calls before the 
                                product plan or warranty expires. </p><br><br>
                            <p>Originally founded by <a href="http://ajagarapu.com" target="_blank">Anand Jagarapu</a>, 
                                the product is driven by passion and has 
                                a series of value added features in the 
                                roadmap of Garanci. Be sure to <a href="default">sign up</a> and 
                                watch for the cool features as the product 
                                evolve around the residential and business 
                                communities.</p>

                        </div>
                    </div>
                </div>
            </div>                       
        </c:if>
        <c:if test="${requestScope.current.equals('terms')}">
            <div id="main-container">
                <div class="container" id="container2">
                    <div class="row-fluid" id="insideContainer">
                        <div class="term" id="termDiv">
                            <h3>Terms & Conditions</h3><br>
                            <p class="text-left">This Privacy and Terms of Use Statement applies only to the Web site at www.Garanci.com. This site is owned and operated by Garanci, Inc. 
                                and should not be confused with any other Web site whether or not the words Garanci and/or Warranties Nicely Managed appear as part of 
                                the domain name or branding. You are advised to check each page you visit on or from this site to determine whether you have moved onto a 
                                third party site. </p><br>
                            <h4>Privacy</h4>
                            <p class="text-left">Garanci, Inc. ("Company") respects your privacy. In that regard, we have created this Privacy Policy to let you know what information we
                                collect when you visit our site, why we collect it, and how it is used. By using this website, you consent to the data practices prescribed in this 
                                Privacy Policy. Garanci's Privacy Policy governs the privacy guidelines for Garanci's website at [www.Garanci.com] (the "Site"). </p><br><br>
                            <h4>What information do we collect?</h4>
                            <p class="text-left">"Personal Information" means any information that may be used to identify an individual, including, but not limited to, first and last 
                                name, home or other physical address, an email address, phone number, or other contact information, whether at work or at home. In a few areas on our Site 
                                and online customer support tools, we ask you to provide Personal Information that will enable us to enhance your use of the Site or for purposes such as 
                                facilitating correspondence with you, registering your entitlement to access certain special features of the Site, or to complete transactions. 
                                It is always your choice whether or not to provide that Personal Information. If you choose not to provide requested Personal Information, you may not 
                                be able to use certain features of the Site. We also automatically receive and record information on our server logs from your browser, including your 
                                IP address, cookie information, browser information, and the page you request.</p><br><br>
                            <h4>How do we use Personal Information?</h4>
                            <p class="text-left">The Personal Information you provide will be kept confidential and used to support your customer relationship with Garanci. Among other 
                                things, we want to help you quickly find information on our Site and alert you to feature upgrades, special offers, updated information and other new products 
                                and services. Garanci may enhance or merge your information collected at the Site with data from third parties for purposes of marketing products or services to you. 
                                Periodically, we may send you information about our various new features and services, or other products and services we feel may be of interest to you. Only Garanci 
                                (or development team working on our behalf and under confidentiality agreements) will send you these direct mailings. If you do not want to receive such mailings, 
                                simply tell us when you give us your Personal Information. Or, at any time you can easily opt-out of receiving further marketing by contacting us and requesting to 
                                have your name removed from our lists. </p><br><br>
                            <h4>Information Sharing and Disclosure to Third Parties</h4>
                            <p class="text-left">Employees of Garanci may have access to your Personal Information for the purpose of developing product feature enhancements on our behalf. 
                                All such employees who have access to your Personal Information are required to keep the information confidential and not use it for any other purpose than to carry 
                                out the services they are performing for Garanci development and value added enhancements. </p><br><br>
                            <p class="text-left">Garanci does not rent, sell or share Personal Information it collects about you to or with third parties. Personal Information collected from you is only
                                used to provide you with products and services and to comply with any requirements of law. Garanci may disclose Personal Information if required to do so by law or in the 
                                good faith belief that such action is necessary to: (1) conform to the edicts of the law or comply with legal process served on Garanci or this Site; (2) protect and defend 
                                the rights or property of Garanci; or (3) act in urgent circumstances to protect the personal safety of users of Garanci, its web sites or the public. If Garanci should ever 
                                file for bankruptcy or merge with another company, we may share your Personal Information with any company with whom we merge. </p><br><br>
                            <h4>Security of Your Information</h4>
                            <p class="text-left">Garanci protects the security of your information during transmission by using Secure Sockets Layer (SSL) software, which encrypts the information you input. 
                                You are solely responsible for maintaining the secrecy of your passwords or any account information. Please be careful and responsible whenever you're online. If you post Personal 
                                Information online that is accessible to the public, you may receive unsolicited messages from other parties in return. While we strive to protect your Personal Information, Garanci 
                                does not ensure or warrant the security of any information you transmit to us, and you do so at your own risk. </p><br><br>
                            <h4>Third-Party Sites</h4>
                            <p class="text-left">Please be aware that other web sites that may be accessed through our site may collect personally identifiable information about you. This Privacy Policy does not 
                                cover the information practices of those third-party web sites linked to our Site. </p><br><br>
                            <h4>International Transfers</h4>
                            <p class="text-left">Personal Information collected on this Site may be stored and processed in the United States or any other country in which Garanci or its affiliates, subsidiaries 
                                or developers maintain facilities, and by using this Site, you consent to any such transfer of information outside of your country. </p><br><br>
                            <h4>Children's Privacy</h4>
                            <p class="text-left">This Site is not intended for or directed to persons under the age of 13. Any person who provides their information to Garanci through the account login page for 
                                new customers or any other part of the web site represents to Garanci that they are 13 years of age or older. </p><br><br>
                            <h4>Your Consent to this Privacy Policy</h4>
                            <p class="text-left">By using this Site, you agree to this Privacy Policy. This is our entire and exclusive Privacy Policy and it supersedes any earlier version. We may change this 
                                Privacy Policy by posting a new version of this Privacy Policy on this web site, and it is your responsibility to review this page periodically. When we do change the policy, 
                                we will also revise the "last updated" date. Your continued use of this web site constitutes your agreement to this Privacy Policy and any updates.</p><br><br>                                    <h4>Changes to Terms of Use Statement</h4>
                            <p class="text-left">Garanci may revise this Terms of Use Statement at any time without notice. Although we will endeavor to highlight any changes to this Statement, you should revisit 
                                this site periodically to make sure you are aware of the most recent terms, because they will be binding on you. Your use of the site after such changes constitutes your agreement 
                                to such changes. </p><br><br>
                            <p class="text-left">Copyright © 2014 Garanci, Inc. All rights reserved.</p><br>
                        </div>
                    </div>
                </div>
                <div class="text-right"><img id="toTop" src="images/top-arrow.png"/></div>
            </div>                       
        </c:if>
        <c:if test="${requestScope.current.equals('privacy')}">
            <div id="main-container">
                <div class="container" id="container2">
                    <div class="row-fluid" id="insideContainer">
                        <div class="term" id="termDiv">
                            <h3>Privacy Policy</h3><br>
                            <p class="text-left">This Terms of Use Statement applies only to the Web site at www.Garanci.com. This site is owned and operated by Garanci, Inc. and should not be 
                                confused with any other Web site whether or not the words Garanci and/or Warranties Nicely Managed appear as part of the domain name or branding. You are advised to 
                                check each page you visit on or from this site to determine whether you have moved onto a third party site. </p><br>
                            <h4>Terms of Use - Site Security</h4>
                            <p class="text-left">You are prohibited from violating, or attempting to violate, the security of this site. Any such violations may result in criminal and/or civil 
                                penalties against you. We will investigate any alleged or suspected violations and if a criminal violation is suspected, we will cooperate with law enforcement 
                                agencies in their investigations. Violations of the security of the site include without limitation: </p><br><br>

                            <ul>
                                <li><p class="text-left">logging into or attempting to log into a server or account that you are not authorized to access;</p></li>
                                <li><p class="text-left">accessing data or taking any action to obtain data, information or services not intended for you or your use;</p></li>
                                <li><p class="text-left">tampering, hacking, modifying or otherwise corrupting or breaching security or authentication measures;</p></li>
                                <li><p class="text-left">transmitting material that contains viruses, Trojan horses, worms, time bombs, cancelbots, corrupted files or other 
                                        computer programming routines or engines or engage in conduct that could damage, disrupt or otherwise impair or interfere with a computer's 
                                        functionality or the operation of the site. Other Prohibited Activity.</p></li>

                                <h4 class="text-center">In using this site, you must not:</h4>
                                <li><p class="text-left">post, send or otherwise transmit to or through the site any unlawful, infringing, harmful, harassing, defamatory, threatening, vulgar, 
                                        sexually explicit, hateful or otherwise objectionable material of any kind, any material that exploits children or is invasive of or in breach of another 
                                        person's privacy or other rights or any material that Garanci in its sole discretion does not wish posted or transmitted on the site;</p></li>
                                <li><p class="text-left">defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;</p></li>
                                <li><p class="text-left">upload or otherwise make available, files that contain images, software or other material protected by intellectual property laws, 
                                        including without limitation copyright or trademark laws and rights of publicity and privacy unless you own or control the rights thereto or have received 
                                        all necessary authorizations to do the same;</p></li>
                                <li><p class="text-left">misrepresent your identity or affiliation in any way;</p></li>
                                <li><p class="text-left">engage in deceptive online marketing;</p></li>
                                <li><p class="text-left">violate any applicable laws or regulations; or</p></li>
                                <li><p class="text-left">assist or permit any persons in engaging in any of the activities described above.</p></li>
                            </ul><br><br>
                            <h4>User Submissions</h4>
                            <p class="text-left">You must exercise caution, good sense and sound judgment in using the site. You are responsible for any material you place on or transmit to or through 
                                the site. You agree, represent and warrant that any information you post to or transmit through the site is truthful, accurate, not misleading and offered in good faith, 
                                and that you have the right to post or transmit such information. Such information (including without limitation, data, text, software, graphics or any other materials whatsoever), 
                                whether publicly posted or privately transmitted, is your sole responsibility. </p><br><br>
                            <h4>Copyright</h4>
                            <p class="text-left">The material made available at this site is protected by copyright. No material from this site may be copied, reproduced, republished, uploaded, posted, 
                                transmitted, or distributed in any way without written permission of the copyright owner, except that you may download one copy of the materials on any single computer and 
                                produce one printed copy for your personal, noncommercial use only, provided you keep intact all copyright and other proprietary notices. Modification of the materials or 
                                use of the materials for any other purpose is a violation of Garanci's copyright and other proprietary rights. Permission for all other uses of materials contained herein, 
                                including reproducing and distributing multiple copies, or linking to any page at this site except the "home page" (http://www.Garanci.com), must be obtained from Garanci in advance.
                                For purposes of this Agreement, the use of any such material on any other Web site or networked computer environment is prohibited. All design rights, databases and compilation and
                                other intellectual property rights, in each case whether registered or unregistered, and related goodwill are proprietary to Garanci.</p><br><br>
                            <p class="text-left">In the event you download software from the site, the software, including any files, images incorporated in or generated by the software, and data accompanying the software 
                                (collectively, the "Software") are licensed to you by Garanci. Garanci does not transfer title to the Software to you. You may own the medium on which the Software is recorded, but Garanci 
                                retains full and complete title to the Software, and all intellectual property rights therein. You may not redistribute, sell, decompile, reverse engineer, disassemble or otherwise reduce 
                                the Software to a human-perceivable form, except to the extent permitted by applicable law and on giving Garanci prior written notice of such activities. </p><br><br>
                            <h4>Trademarks</h4>
                            <p class="text-left">All trademarks, service marks, logos and trade names, whether registered or unregistered, are proprietary to Garanci or to other companies where so indicated. You may not 
                                reproduce, download or otherwise use any such trademarks, service marks, logos or trade names without the prior written consent of the appropriate owner thereof. </p><br><br>
                            <h4>Governing Law and Jurisdiction</h4>
                            <p class="text-left">This site is controlled and operated by Garanci from its offices within the State of Ohio, United States of America. Garanci makes no representation that materials in the 
                                site are appropriate or available for use in other locations. Those who choose to access this site from other locations do so on their own initiative and are responsible for compliance with 
                                local laws, if and to the extent local laws are applicable. Access to this site from jurisdictions where the contents of this site are illegal or penalized is prohibited. Software from this 
                                site is further subject to United States export controls.</p><br><br>
                            <h4>Severability</h4>
                            <p class="text-left">If any clause or provision set forth in this Terms and Conditions statement is determined to be illegal, invalid or unenforceable under present or future law, the clause or 
                                provision shall be deemed to be deleted without affecting the enforceability of all remaining clauses or provisions. </p><br><br>
                            <h4>Disclaimer</h4>
                            <p class="text-left">THE COMPANY DOES NOT SELL WARRANTY SERVICES, AND THE COMPANY IS NOT A WARRANTY PLAN

                                UNDERWRITER. IT IS UP TO THE THIRD PARTY WARRANTY PROVIDER, TO OFFER WARRANTY SERVICES 

                                WHICH MAY BE PURCHASED THROUGH USE OF THE APPLICATION OR SERVICE. THE COMPANY 

                                PROVIDES A SOFTWARE APPLICATION FOR THE HOUSEHOLDS TO SAVE THE WARRANTY DATA IN A 

                                DIGITAL FORMAT, BUT DOES NOT AND DOES NOT INTEND TO PROVIDE WARRANTY SERVICES OR ACT 

                                IN ANY WAY AS A WARRANTY PROVIDER, AND HAS NO RESPONSIBILITY OR LIABILITY FOR ANY 

                                WARRANTY COVERAGES OR SERVICES PROVIDED TO YOU BY SUCH THIRD PARTIES.</p><br><br>  
                            <h4>Limitation of Liability</h4>
                            <p class="text-left">To the extent permitted under applicable law, under no circumstances, including, but not limited to, negligence, shall Garanci be liable for any compensatory, punitive, special or consequential 
                                damages that result from the use of, or the inability to use, the materials in this site, even if Garanci or a Garanci authorized representative has been advised of the possibility of such damages. Applicable 
                                law may not allow the limitation or exclusion of liability or incidental or consequential damages, and so to that extent the above limitation or exclusion may not apply to you. In no event shall Garanci's total 
                                liability to you for all damages, losses, and causes of action (whether in contract, tort (including, but not limited to, negligence) or otherwise) exceed the amount paid by you to Garanci, if any, for using this 
                                site.</p><br><br>  
                            <h4>Indemnity</h4>
                            <p class="text-left">You agree to indemnify, defend and hold Garanci, its affiliates and any of its and their directors, employees, agents and contractors harmless from and against any and all claims, damages, 
                                losses, costs (including without limitation reasonable attorneys' fees) or other expenses that arise directly or indirectly out of or from (i) your breach of this agreement; and/or (ii) your activities in connection 
                                with this site.</p><br><br>  
                            <h4>Changes to Privacy Statement</h4>
                            <p class="text-left">Garanci may revise this Privacy Statement at any time without notice. Although we will endeavor to highlight any changes to this Statement, you should revisit this site periodically to make sure you 
                                are aware of the most recent terms, because they will be binding on you. Your use of the site after such changes constitutes your agreement to such changes.</p><br><br>  

                            <p class="text-left">Copyright © 2014 Garanci, Inc. All rights reserved.</p><br>
                        </div>
                    </div>
                </div>
                <div class="text-right"><img id="toTop" src="images/top-arrow.png"/></div>
            </div>                       
        </c:if> 

        <script>
            $("#toTop").click(function () {
                $('html, body').animate({
                    scrollTop: $("#top-header").offset().top
                }, 1000);
            });
        </script>
        <div id="bottom-footer">            






            <div class="container-fluid">
                <div class="row" style="background-color: white;border-top: 1px solid rgba(0,0,0,.1)">
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
                </div>
            </div>
        </div>
        <c:if test="${requestScope.current.equals('all')}">
            <div style="display:none;" class="nav_up" id="nav_up"> </div>
        </c:if>
    </body>
</html>
<%
    session.setAttribute("errorMsg", "");
%>