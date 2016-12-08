<%-- 
    Document   : admin
    Created on : 4 Aug, 2016, 1:46:33 PM
    Author     : zishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Connect and collaborate for safety and awareness - Garanci</title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <style>

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
               
                float: right;
                font-weight: 400;
                color: black;
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
    <body style="margin: 0px;padding: 0px;">
        <div style="width: 100%;height: 100vh;background-color: white;margin: 0 auto;" >

            <div style="width: 100%;height: 80px;;background-color: white;margin: 0 auto;" >
                <div style="width: 1000px;height: 80px;;background-color: white;margin: 0 auto;" >
                    <div id="header">
                        <div class="logo">
                            <a href="HomePage">Garanci</a>
                        </div>


                    </div>
                </div>

                <div style="width: 100%;height: 500px;;background-color: black;margin: 0 auto;" >

                    <div style="width: 1000px;height: 500px;;background-color: black;margin: 0 auto;" >

                        <div style="width: 400px;height: auto;;background-color: white;float: right;margin-right: 100px;margin-top: 100px;padding: 20px;" >

                            <form action="Admin?action=login" method="POST">

                                <table class="table3" >
                                    <tr>
                                        <td><span>${message}</span></td>
                                    </tr>
                                    <tr>
                                        <td><label>Email*</label><input type="email" name='email' class="form-control"></td>
                                    </tr>
                                    <tr>
                                        <td><label>Password*</label><input type="password" name='password' class="form-control"></td>
                                    </tr>
                                    <tr>
                                        <td><button id='signUpButton' style="font-family: sans-serif" >Log In
<!--                                              &nbsp;   <span class="glyphicon glyphicon-log-in" ></span>-->
                                            </button></td>
                                    </tr>

                                </table>
                            </form>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
