<%-- 
    Document   : index_1
    Created on : 9 May, 2016, 1:55:32 PM
    Author     : zishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false" %>
<!doctype html>
<!--
  Material Design Lite
  Copyright 2015 Google Inc. All rights reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License
-->
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Garancy Admin</title>

        <!-- Add to homescreen for Chrome on Android -->
        <meta name="mobile-web-app-capable" content="yes">
        <link rel="icon" sizes="192x192" href="images/android-desktop.png">

        <!-- Add to homescreen for Safari on iOS -->
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-title" content="Material Design Lite">

        <link href="CSS/bootstrap.min.css" type="text/css"  rel="stylesheet"/>
        <script src="javascript/jquery-1.10.2.min.js" type="text/javascript" ></script>
        <link href="CSS/bootstrap-theme.min.css" type="text/css"  rel="stylesheet"/>

        <link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

        <!-- Tile icon for Win8 (144x144 + tile color) -->
        <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
        <meta name="msapplication-TileColor" content="#3372DF">


        <link rel="stylesheet" type="text/css" media="screen" href="//cdn.muicss.com/mui-0.5.5/css/mui.css" />
        <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
        <script src="//cdn.muicss.com/mui-0.5.5/js/mui.js"></script>


        <link rel="shortcut icon" href="images/favicon.png">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"/>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

        <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
        <!--
        <link rel="canonical" href="http://www.example.com/">
        -->

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.cyan-light_blue.min.css">

        <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="CSS/styles.css">

        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

        <script src="js/admin.js" type="text/javascript"></script>


        <style type="text/css">

            .table3
            {
                width: 100%;
                background-color: transparent;
            }

            .table3 td
            {
                padding: 5px;
                text-align: left;
            }
            .styled-button-6 {
                background:#f78096;
                background: -moz-linear-gradient(top,#f78096 0%,#f56778 100%);
                background: -webkit-gradient(linear,left top,left bottom,color-stop(0%,#f78096),color-stop(100%,#f56778));
                background: -webkit-linear-gradient(top,#f78096 0%,#f56778 100%);
                background: -o-linear-gradient(top,#f78096 0%,#f56778 100%);
                background: -ms-linear-gradient(top,#f78096 0%,#f56778 100%);
                filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#f78096',endColorstr='#f78096',GradientType=0);
                padding:5px 4px;
                color:#fff;
                font-family:'Helvetica Neue',sans-serif;
                font-size:12px;
                border-radius:4px;
                -moz-border-radius:4px;
                -webkit-border-radius:4px;
                border:1px solid #ae4553
            }      
            .mdl-navigation__link:hover
            {
                cursor: pointer;
            }
        </style>
        <script>

            function notificationClick(Id)
            {


                $(".mdl-navigation__link").css("background-color", "transparent");
                $(Id).css("background-color", "#07ACC1");

                $("#recasslSch").show();
                $("#notilSch").hide();
            }

            function recallClick(Id)
            {
                $(Id).css("background-color", "#07ACC1");

                $(".mdl-navigation__link").css("background-color", "transparent");
                $(Id).css("background-color", "#07ACC1");

                $("#recasslSch").hide();
                $("#notilSch").show();
            }

            //document.getElementById("mybutt").style.backgroundColor = '#07ACC1';
            $(function () {
                document.getElementById("mybutt").click();
                $("#time").datepicker();
            });
        </script>

    </head>
    <body id="body" onload="GmtTimeCacculater('${Rtd}')">




        <input type="hidden" id="whatRole" value="${roleId}" />
        <input type="hidden" id="whatId" value="${userId}"/>
        <input type="hidden" id="whatName" value="${Name}"/>
        <input type="hidden" id="whatEmail" value="${email}"/>
        <input type="hidden" id="whatPhoto" value="${personProfilePics}"/>
        <input type="hidden" id="whatMobile" value="${personMobile}"/>
        <input type="hidden" id="jsessionid" value="${jsessionid}" />

        <input type="hidden" id="tier2Path" value="${tier2Path}" />




        <div id="containerholder"  onclick="allCloser();"></div>


        <div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
            <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600" style="height: 50px;padding: 0px;">
               
            </header>
            <div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
                <header class="demo-drawer-header">
                    <!--                    <img  id="homepageprofile" class="demo-avatar">&nbsp;&nbsp;-->
                    <div class="demo-avatar-dropdown">
                        <span id="spanforname" onclick="getDataForProfile();">&nbsp;Home</span>

                    </div>
                </header>
                <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
                    <a class="mdl-navigation__link"  style="text-decoration: none;color: white;background-color: transparent" id='mybutt' onclick="notificationClick(this);" >Recalls Schedule</a>
                    <a class="mdl-navigation__link"  style="text-decoration: none;color: white;background-color: transparent" id='mybutt2' onclick="recallClick(this);" >Warranty Notification Schedule</a>
                
                  <a class="mdl-navigation__link" href='Admin?action=logout'  style="text-decoration: none;color: white;background-color: transparent" id='mybutt2'  ><button class="form-control styled-button-6" style="width: 100%;height: 30px">LogOut&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-log-out"></span></button></a>
                </nav>
            </div>

            <main class="mdl-layout__content mdl-color--grey-200">

                <div style="width: 100%;height: 500px;;background-color: transparent;" id="recasslSch">
                    <ul style="margin-left: 30px;margin-top: 30px;">
                        <li style="list-style: none">  <strong>Currently Running ${Rname}</strong></li>
                        <li style="list-style: none"> Running every day at ${Rhr} : ${Rmn} GMT <span class='gnttimespan' ></span> </li>              
                    </ul>
                    <div style="width: 100%;height: 70px;text-align: center;border: 1px solid transparent;" >

                        <p style="margin-top: 20px;font-size: 20px;">Set Scheduling Time</p>

                    </div>


                    <div style="width: 500px;;height: auto;text-align: center;padding: 10px;border: 1px solid transparent;margin: 0 auto;border-top:  1px solid rgba(0,0,0,.3);;border-bottom:   1px solid rgba(0,0,0,.3)" >
                        <table class="table3">
                            <tr>
                                <td>
                                    Current Time
                                </td><td>
                                    ${Rhr} : ${Rmn} GMT <span class='gnttimespan' ></span> 
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Scheduling Time
                                </td><td>
                                    <input type="text" class="form-control"  id='time' style="margin: 0 auto;background-color: transparent" placeholder="i.e. 12:15" />
                                </td>
                            </tr>
                            <tr>
                                <td >
                                    Time Zone
                                </td><td>
                                    <select class="form-control" id='zone' style="margin: 0 auto;background-color: transparent"/>
                            <option value="0">Local Time Zone</option>
                            <option value="1">GMT Time Zone</option>
                            </select>
                            </td>
                            </tr>
                            <tr>
                                <td >

                                </td><td>
                                    <button class="btn btn-primary" style="float: right;width: 100px;" onclick="changeTimer(this);" >Change</button>
                                </td>
                            </tr>
                        </table>


                    </div>


                </div>

                <div style="width: 100%;height: 500px;;background-color: transparent;display: none" id="notilSch">

                 <ul style="margin-left: 30px;margin-top: 30px;">
                        <li style="list-style: none">  <strong>Currently Running ${Nname}</strong></li>
                        <li style="list-style: none"> Running every day at ${Nhr} : ${Nmn}  <span id='gnttimespan' ></span> GMT </li>         
                    </ul>

                </div>

            </main>




        </div>

        <script src="https://code.getmdl.io/1.1.3/material.min.js"></script>

    </body>

</html>