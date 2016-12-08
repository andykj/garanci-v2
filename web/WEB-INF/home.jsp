<%-- 
    Document   : Home page
    Created on : 30 Aug, 2014, 4:08:41 PM
    Author     : zishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800' rel='stylesheet' type='text/css'>
        <link href='css/bootstrap-theme.min.css' rel='stylesheet' type="text/css"/>
        <link href='css/bootstrap.min.css' rel='stylesheet' type="text/css"/>
     
        <link href='css/home.css' rel='stylesheet' type='text/css'/>
        <script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
           <link href='css/jquery-ui.css' rel='stylesheet' type="text/css"/>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/home.js" type="text/javascript"></script>
        <script src="js/abbyy.js" type="text/javascript"></script>
        <title>Garanci- Home</title>
        <script>
            $(document).ready(function() {
                var vpw = (screen.width>=768)?'960':'device-width';
                if(vpw==960)
                {
                    $('head').prepend('<meta name="viewport" content="width='+vpw+',initial-scale=0.8"/>');
                }
                else
                {
                    $('head').prepend('<meta name="viewport" content="width=device-width, initial-scale=0.33"/>');
                }
            });
        </script>
    </head>
    <body onclick="hideMessage();">
        <div id="header">
            <div id="headerInside">
                <div id="logoDiv"><img src="images/Garanci_Logo.png" id="GaranciLogo" onclick="home();" title="Home"/></div>
                <div id="settingDiv" class="btn-group">
                    <a id="welcomeMsg" onclick="profile();" title="Profile">${sessionScope.subscriberName}</a>
                    <img src="images/setting.png" class="dropdown settingIcon" alt="Setting" title="Setting" data-toggle="dropdown"/>
                    <ul class="dropdown-menu dropdown-menu-right" role="menu" id="dropDown">
                        <li role="presentation"><a role="menuitem" tabindex="-3" onclick="showHouseHold();">Household</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-3" onclick="showNotification();">Notification</a></li>
                    </ul>
                    <a href="do?action=signOut">
                        <img src="images/signOut.png" class="settingIcon" alt="Sign out" title="Sign out"/>
                    </a>
                </div>
            </div>
        </div>
        <div class="row text-center" id="errorDiv"></div>
        <div class="container" id="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-8" id="dashboardDiv">
                        <div id="dashboardHeader">My Dashboard
                            <div id="createReceiptDiv">
                                <div id="colorDiv">
                                    <span id="yellow" class="label label-warning">< 1 Week</span>
                                    <span id="orange" class="label label-warning">< 3 Day</span>
                                    <span id="red" class="label label-danger">< 1 Day</span>               
                                </div>
                                <a id="createReceipt" onclick="showReceiptDiv();">Create New Receipt</a>
                            </div>
                        </div>
                        <div id="dashboardBody">
                        </div>
                </div>
            </div>
        </div>
        <div id="footer">
            <div class="footerInside" id="footerInsideLeft"><a id="copyRight">&COPY;2014 Garanci.</a><a href="default?action=terms" class="footerInsideLeft">Terms</a><a href="default?action=privacy" class="footerInsideLeft">Privacy</a><a class="footerInsideLeft" title="Contact" id="contact" onclick="fetchContact();">Contact</a></div><img src="images/Garanci_Logo.png" id="GaranciFooterLogo"/><div class="footerInside" id="footerInsideRight"><a href="https://garanci.wordpress.com" target="_blank" class="footerInsideRight">Blog</a><a href="default?action=about" class="footerInsideRight">About</a></div>
        </div>
    </body>
</html>