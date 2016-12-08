/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

$(document).ready(function(){
    
});

function hideWarning(id)
{
    document.getElementById(id).style.backgroundImage="none";
}
function checkFirstName()
{
    var nameValue=document.getElementById("firstName").value;
    var reg=new RegExp("^[a-zA-Z]{2,35}$");
    if(!reg.test(nameValue))
    {
       document.getElementById("firstName").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("firstName").style.backgroundPosition="right";
       document.getElementById("firstName").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Invalid first name.</div>');
    }
    else
    {
       document.getElementById("firstName").style.backgroundImage="none";
        $("#errorDiv").html(' ');
    }
}
function goToAppleStore()
{
    window.location="https://itunes.apple.com/us/app/garanci/id934726813?ls=1&mt=8";
}
function checkLastName(){
    var nameValue=document.getElementById("lastName").value;
    var reg=new RegExp("^[a-zA-Z]{2,35}$");
    if(!reg.test(nameValue))
    {
       document.getElementById("lastName").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("lastName").style.backgroundPosition="right";
       document.getElementById("lastName").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Invalid last name.</div>');
    }
    else
    {
         document.getElementById("lastName").style.backgroundImage="none";
          $("#errorDiv").html(' ');
    }
}
function checkPhone()
{
    document.getElementById("phone").style.backgroundImage="none";
    document.getElementById("phone").style.backgroundSize="25px 16px";
    var phoneValue=document.getElementById("phone").value;
    var reg=new RegExp("^[0-9]{7,12}$");
    if(!reg.test(phoneValue))
    {
       document.getElementById("phone").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("phone").style.backgroundPosition="right";
       document.getElementById("phone").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Invalid mobile number.</div>');
    }
    else
    {
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="do?client=browser&action=verifyPhone&phone="+escape(phoneValue);
        req.open("POST", url, true);
        req.send(null);
    //    document.getElementById("email").style.backgroundImage="url('images/loading.gif')";
    //    document.getElementById("email").style.backgroundPosition="right";
    //    document.getElementById("email").style.backgroundSize="37px 37px";
    //    document.getElementById("email").style.backgroundRepeat="no-repeat";
        req.onreadystatechange = function()
        {
            if(req.readyState == 4)
            {
                if(req.status==200)
                {
                    var check=req.responseText;
                    var evaluatedResult=eval('(' + check + ')');
                    if(evaluatedResult.response=="unAvailable"|| evaluatedResult.response=="invalidData")
                    {
                        document.getElementById("phone").style.backgroundImage="none";
                        document.getElementById("phone").style.backgroundImage="url('images/alert-icon.png')";
                        document.getElementById("phone").style.backgroundPosition="right";
                        document.getElementById("phone").style.backgroundSize="26px 17px";
                        document.getElementById("phone").style.backgroundRepeat="no-repeat";
                        $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Phone already registered.<div>');
                    }
                    else if(evaluatedResult.response=="available")
                    {
                        $("#errorDiv").html('');
                        document.getElementById("phone").style.backgroundImage="none";
                    }
                    else
                    {
                        $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Service Unavailable.</div>');
                    }
                }
            }
        };
    }
}
function checkEmail()
{
    document.getElementById("email").style.backgroundImage="none";
    var emailValue=document.getElementById("email").value;
    var reg=new RegExp("^[0-9]*[a-z]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\..[a-z]{2,4})$");
    var flag=0;
    if(emailValue=="" || !reg.test(emailValue))
    {
        document.getElementById("email").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("email").style.backgroundPosition="right";
        document.getElementById("email").style.backgroundSize="26px 17px";
        document.getElementById("email").style.backgroundRepeat="no-repeat";
        $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Invalid email.</div>');
        flag=1;
    }
    else
    {
       document.getElementById("email").style.backgroundImage="none";
       $("#errorDiv").html(' ');
       flag=0;
    }
    if(flag==0)
    {
    var req=null;
    if(window.XMLHttpRequest)
    {
        req=new XMLHttpRequest();
    }
    else
    {
        req=new ActiveXObject("Microsoft.XMLHttp");
    }
    var url="do?client=browser&action=verifyEmail&email="+escape(emailValue);
    req.open("POST", url, true);
    req.send(null);
//    document.getElementById("email").style.backgroundImage="url('images/loading.gif')";
//    document.getElementById("email").style.backgroundPosition="right";
//    document.getElementById("email").style.backgroundSize="37px 37px";
//    document.getElementById("email").style.backgroundRepeat="no-repeat";
    req.onreadystatechange = function()
    {
        if(req.readyState == 4)
        {
            if(req.status==200)
            {
                var check=req.responseText;
                var evaluatedResult=eval('(' + check + ')');
                if(evaluatedResult.response=="unAvailable"|| evaluatedResult.response=="invalidData")
                {
                    document.getElementById("email").style.backgroundImage="url('images/alert-icon.png')";
                    document.getElementById("email").style.backgroundPosition="right";
                    document.getElementById("email").style.backgroundSize="26px 17px";
                    document.getElementById("email").style.backgroundRepeat="no-repeat";
                    $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Email already registered.<div>');
                }
                else if(evaluatedResult.response=="available")
                {
                    $("#errorDiv").html('');
                    document.getElementById("email").style.backgroundImage="none";
                }
                else
                {
                    $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Service Unavailable.</div>');
                }
            }
        }
    };
  }
}


function loadingAnimation(id)
{
    document.getElementById(id).style.backgroundImage="url('images/loading.gif')";
    document.getElementById(id).style.backgroundPosition="right";
    document.getElementById(id).style.backgroundSize="37px 37px";
    document.getElementById(id).style.backgroundRepeat="no-repeat";
}


function checkPassword()
{
    var passwordValue=document.getElementById("password").value;
    if(passwordValue=="" || passwordValue.length<7 || passwordValue.length>20 || !new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{4,18}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{1}").test(passwordValue))
    {
       document.getElementById("password").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("password").style.backgroundPosition="right";
       document.getElementById("password").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Invalid password.</div>');
    }
    else
    {
       document.getElementById("password").style.backgroundImage="none";
        $("#errorDiv").html(' ');
    }
}
function checkCountryCode(){
    var countryCodeValue=document.getElementById("cntryCd").value;
    document.getElementById("cntryCd").value=countryCodeValue;
    if(countryCodeValue=="")
    {
        document.getElementById("cntryCd").innerHTML=countryCodeValue;
    }
    else
    {
        document.getElementById("cntryCd").innerHTML=countryCodeValue;
        
    }
}
function signUp()
{
    var email=document.getElementById("email").value;
    var firstName=document.getElementById("firstName").value;
    var lastName=document.getElementById("lastName").value;
    var phone=document.getElementById("phone").value;
    var password=document.getElementById("password").value;
    var countryCode=document.getElementById("cntryCd").value;
    var mobReg=new RegExp("^[0-9]{7,12}$");
    var nameReg=new RegExp("^[a-zA-Z]{2,35}$");
    var emailReg=new RegExp("^[0-9]*[a-z]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\..[a-z]{2,4})$");
    var status=1;
    if(firstName=="" || !nameReg.test(firstName))
    {
       document.getElementById("firstName").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("firstName").style.backgroundPosition="right";
       document.getElementById("firstName").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Please fill all the fields before continuing.</div>');
       status=0;
    }
    else if(lastName=="" || !nameReg.test(lastName))
    {
       document.getElementById("lastName").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("lastName").style.backgroundPosition="right";
       document.getElementById("lastName").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Please fill all the fields before continuing.</div>');
       status=0;
    }
    else if(phone=="" || !mobReg.test(phone) || phone.length<7 || phone.length>13)
    {
       document.getElementById("phone").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("phone").style.backgroundPosition="right";
       document.getElementById("phone").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Please fill all the fields before continuing.</div>');
       status=0;
    }
    else if(email=="" || !emailReg.test(email))
    {
       document.getElementById("email").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("email").style.backgroundPosition="right";
       document.getElementById("email").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Please fill all the fields before continuing.</div>');
       status=0;
    }
    else if(password=="" || password=="Create Your Password" || !new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{4,18}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{1}").test(password))
    {
       document.getElementById("password").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("password").style.backgroundPosition="right";
       document.getElementById("password").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Please fill all the fields before continuing.</div>');
       status=0;
    }
    else if(password.length<7 ||password.length>20)
    {
       document.getElementById("password").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("password").style.backgroundPosition="right";
       document.getElementById("password").style.backgroundRepeat="no-repeat";
       $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Please fill all the fields before continuing.</div>');
       status=0;
    }
    if(status==1)
    {
        var formData="firstName="+firstName+"&lastName="+lastName+"&phone="+phone+"&email="+email+"&password="+password+"&countryCode="+countryCode+"&client=browser&action=registration";
        document.getElementById("email").style.backgroundImage="none";
        document.getElementById("signUpButton").onclick=function(){};
        document.getElementById("signUpButton").innerHTML="Sending...";
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="do";
        req.open("POST", url,true);
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("Connection", "close");
        req.send(formData);
        req.onreadystatechange = function() 
        {
            if(req.readyState == 4)
            {
                if(req.status==200)
                {
                    var check=eval('('+req.responseText+')');
                    if(check.response=="success")
                    {
                        document.getElementById("email").value="";
                        document.getElementById("firstName").value="";
                        document.getElementById("lastName").value="";
                        document.getElementById("phone").value="";
                        document.getElementById("password").value="";
                        document.getElementById("signUpButton").innerHTML="Sign Up";
                        document.getElementById("signUpButton").onclick=function(){signUp();};
                        window.location="default?action=login&afterSignUp=true";
                    }
                    else if(check.response=="alreadyPresent")
                    {
                        $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Email or phone is already registered.</div>');
                        document.getElementById("signUpButton").innerHTML="Sign Up";
                        document.getElementById("signUpButton").onclick=function(){signUp();};
                    }
                    else if(check.response=="failure"){
                        $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Invalid data is found.</div>');
                        document.getElementById("signUpButton").innerHTML="Sign Up";
                        document.getElementById("signUpButton").onclick=function(){signUp();};
                    }
                    else{
                        $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Service Unavailable.</div>');
                        document.getElementById("signUpButton").innerHTML="Sign Up";
                        document.getElementById("signUpButton").onclick=function(){signUp();};
                    }
                }
                else{
                    $("#errorDiv").html('<div class="bg-danger" id="warningMsg2">Please check your internet connection</div>');
                    document.getElementById("signUpButton").value="Sign Up";
                    document.getElementById("signUpButton").onclick=function(){signUp();};
                }
            }
        };
    }
}
function CheckEmail(errorId)
{
    var emailValue=document.getElementById("email");
    var reg=new RegExp("^[0-9]*[a-z]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\..[a-z]{2,4})$");
    var reg2=new RegExp("^[0-9]{6,15}$");
    var flag=1;
    if(!reg.test(emailValue.value))
    {
        emailValue.style.backgroundImage="url('images/alert-icon.png')";
        emailValue.style.backgroundPosition="right";
        emailValue.style.backgroundRepeat="no-repeat";
        document.getElementById(errorId).innerHTML='<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Invalid email.</div>';
        flag=0;
    }
    else
    {
        emailValue.style.backgroundImage="none";
        document.getElementById(errorId).innerHTML=' ';
        flag=1;
    }
    if(flag==0)
    {
        if(!reg2.test(emailValue.value))
        {
            emailValue.style.backgroundImage="url('images/alert-icon.png')";
            emailValue.style.backgroundPosition="right";
            emailValue.style.backgroundRepeat="no-repeat";
            document.getElementById(errorId).innerHTML='<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Invalid email.</div>';
        }
        else
        {
            emailValue.style.backgroundImage="none";
            document.getElementById(errorId).innerHTML=' ';
        }
    }
}
function CheckPassword()
{
    var passwordValue=document.getElementById("password").value;
    if(passwordValue=="" || passwordValue.length<6 || passwordValue.length>20 || !new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{4,18}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{1}").test(passwordValue))
    {
       document.getElementById("password").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("password").style.backgroundPosition="right";
       document.getElementById("password").style.backgroundRepeat="no-repeat";
       $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Invalid password.</div>');
    }
    else
    {
       document.getElementById("password").style.backgroundImage="none";
        $("#ErrorDiv").html(' ');
    }
}
function signIn()
{
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;
    var status=1;
    
    if(password=="" || password.length<6 || password.length>20)
    {
        document.getElementById("ErrorDiv").innerHTML='<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Invalid Password</div>';
        status=0;
    }
    if(!new RegExp("^[0-9]*[a-z]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\..[a-z]{2,4})$").test(email))
    {
       status=0;
    }
    if(status==0)
    {
        if(!new RegExp("^[0-9]{6,15}$").test(email))
        {
            document.getElementById("ErrorDiv").innerHTML='<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Invalid email or password</div>';
            status=0;
        }
        else
        {
            status=1;
        }
    }
    if(status==1)
    {
        var formData="email="+email+"&password="+password+"&client=browser&action=login";
        document.getElementById("signInButton").onclick=function(){};
        document.getElementById("signInButton").innerHTML="logging";
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="do";
        req.open("POST", url,true);
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("Connection", "close");
        req.send(formData);
        req.onreadystatechange = function() 
        {
            if(req.readyState == 4)
            {
                if(req.status==200)
                {
                    var check=eval('('+req.responseText+')');
                    if(check.response=="success")
                    {
                        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Authenticated Successfully. Redirecting...</div>');
                        document.getElementById("email").value="";
                        document.getElementById("password").value="";
                        document.getElementById("signInButton").innerHTML="Log In";
                        window.location="default?action=home";
                    }
                    else if(check.response=="deActive")
                    {
                        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Please activate your account</div>');
                        document.getElementById("signInButton").innerHTML="Log In";
                        document.getElementById("signInButton").onclick=function(){signIn();};
                    }
                    else if(check.response=="resetInProgress")
                    {
                        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Check your email to reset password</div>');
                        document.getElementById("signInButton").innerHTML="Log In";
                        document.getElementById("signInButton").onclick=function(){signIn();};
                    }
                    else if(check.response=="failure")
                    {
                        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Invalid email or password</div>');
                        document.getElementById("signInButton").innerHTML="Log In";
                        document.getElementById("signInButton").onclick=function(){signIn();};
                    }
                    else if(check.response=="sessionExpired")
                    {
                        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Your session has expired</div>');
                        document.getElementById("signInButton").innerHTML="Log In";
                        document.getElementById("signInButton").onclick=function(){signIn();};
                    }
                    else
                    {
                        $("#ErrorDiv").html('Service Unavailable.');
                        document.getElementById("signInButton").innerHTML="Log In";
                        document.getElementById("signInButton").onclick=function(){signIn();};
                    }
                }
                else
                {
                    $("#ErrorDiv").html('Please check your internet connection');
                    document.getElementById("signInButton").value="Log In";
                    document.getElementById("signInButton").onclick=function(){signIn();};
                }
            }
        };
    }
}
function CheckResetPassword(){
    var passwordValue=document.getElementById("password").value;
    if(passwordValue=="" || passwordValue.length<6 || passwordValue.length>20 || !new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{4,18}[a-zA-Z0-9._-!@#$%^&*()+<>,?~`-]{1}").test(passwordValue))
    {
       document.getElementById("password").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("password").style.backgroundPosition="right";
       document.getElementById("password").style.backgroundRepeat="no-repeat";
       $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Enter the new password to reset.</div>');
    }
    else
    {
       document.getElementById("password").style.backgroundImage="none";
        $("#ErrorDiv").html(' ');
    }
}
function checkPasswordRePassword()
{
    var password=document.getElementById("password");
    var rePassword=document.getElementById("rePassword");
    if(password.value!=rePassword.value)
    {
        password.style.backgroundImage="url('images/alert-icon.png')";
        password.style.backgroundPosition="right";
        password.style.backgroundRepeat="no-repeat";
        rePassword.style.backgroundImage="url('images/alert-icon.png')";
        rePassword.style.backgroundPosition="right";
        rePassword.style.backgroundRepeat="no-repeat";
        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Passwords do not match. Please re-enter passwords to continue with the reset</div>');
    }
    else
    {
        password.style.backgroundImage="none";
        rePassword.style.backgroundImage="none";
    }
}
function resetPassword()
{
    var flag=1;
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;
    var rePassword=document.getElementById("rePassword").value;
    var code=document.getElementById("code").value;
    if(password=="" || !new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{4,18}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{1}").test(password))
    {
        password.style.backgroundImage="url('images/alert-icon.png')";
        password.style.backgroundPosition="right";
        password.style.backgroundRepeat="no-repeat";
        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Enter the new password to reset</div>');
        flag=0;
    }
    else if(rePassword=="" || !new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{4,18}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{1}").test(rePassword))
    {
        document.getElementById("rePassword").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("rePassword").style.backgroundPosition="right";
        document.getElementById("rePassword").style.backgroundRepeat="no-repeat";
        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Enter the re-password to reset</div>');
        flag=0;
    }
    else if(password!=rePassword)
    {
       
        password.style.backgroundImage="url('images/alert-icon.png')";
        password.style.backgroundPosition="right";
        password.style.backgroundRepeat="no-repeat";
        rePassword.style.backgroundImage="url('images/alert-icon.png')";
        rePassword.style.backgroundPosition="right";
        rePassword.style.backgroundRepeat="no-repeat";
        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Passwords do not match. Please re-enter passwords to continue with the reset</div>');
        flag=0;
    }
    else if(flag==1)
    {
        var formData="email="+email+"&password="+password+"&code="+code+"&client=browser&action=reset";
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="do";
        req.open("POST", url,true);
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("Connection", "close");
        req.send(formData);
        req.onreadystatechange = function() 
        {
            if(req.readyState == 4)
            {
                if(req.status==200)
                {
                    var check=eval('('+req.responseText+')');
                    if(check.response=="success")
                    {
                       window.location="default?action=login&afterSignUp=false";
                    }
                    else if(check.response=="failure")
                    {
                       $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Invalid data found</div>'); 
                    }
                    else if(check.response=="invalidData")
                    {
                        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Invalid data found</div>');
                    }
                }
            }
        };    
    }
}
function forgotPassword()
{
    var email=document.getElementById("email");
    var reg=new RegExp("^[0-9]*[a-z]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\..[a-z]{2,4})$");
    var reg2=new RegExp("^[0-9]{6,15}$");
    var flag=1;
    if(!reg.test(email.value))
    {
        email.style.backgroundImage="url('images/alert-icon.png')";
        email.style.backgroundPosition="right";
        email.style.backgroundRepeat="no-repeat";
        flag=0;
    }
    else
    {
        email.style.backgroundImage="none";
        flag=1;
    }
    if(flag==0)
    {
        if(!reg2.test(email.value))
        {
            email.style.backgroundImage="url('images/alert-icon.png')";
            email.style.backgroundPosition="right";
            email.style.backgroundRepeat="no-repeat";
            flag=0;
        }
        else
        {
            email.style.backgroundImage="none";
            flag=1;
        }
    }
    if(flag==1)
    {
        $("#success").html('');
        $("#formText").html('');
        $("#resetHideButton").html('');
        var formData="action=forgotPassword&email="+email.value;
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="do";
        req.open("POST", url,true);
        $("#ErrorDiv1").html('Please wait...');
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("Connection", "close");
        req.send(formData);
        req.onreadystatechange = function() 
        {
            if(req.readyState == 4)
            {
                if(req.status==200)
                {
                    var check=req.responseText;
                    var evaluatedResult=eval('(' + check + ')');
                    if(evaluatedResult.response=="success")
                    {
                        $("#success").append('<h4>Password reset confirmation sent!</h4>');
                        $("#formText").append('<p>We’ve sent an email to <b>'+evaluatedResult.email+'</b> containing a link that will allow you to reset your password.'+
                                                  '<br>Please check your spam folder if the email doesn’t appear within a few minutes. </p>');       
                        $("#ErrorDiv1").html(' ');
                        $("#resetHideButton").append('<button type="button" onclick="gotoOnSignIn();" id="signInButton"><strong>Return to sign in</strong></button>');
                    }
                    else if(check.response=="failure")
                    {
                        $("#ErrorDiv1").html('Invalid email or password.');
                    }
                    else
                    {
                        $("#ErrorDiv1").html('Can not find the email, Try again');
                    }
                }
                else
                {
                    $("#ErrorDiv1").html('Please check your internet connection');
                }
            }
        };
    }
}
function contactUs()
{
    var email=document.getElementById("email").value;
    var name=document.getElementById("name").value;
    var mobile=document.getElementById("mobile").value;
    var subject=document.getElementById("subject").value;
    var message=document.getElementById("message").value;
    var emailReg=new RegExp("^[0-9]*[a-z]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\..[a-z]{2,4})$");
    var mobileReg=new RegExp("^[0-9]{7,12}$");
    var status=1;
    if(name=="" || !new RegExp("[a-zA-Z]{1}[a-zA-Z/s]*[a-zA-Z]{1}").test(name))
    {
       document.getElementById("name").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("name").style.backgroundPosition="right";
       document.getElementById("name").style.backgroundRepeat="no-repeat";
       $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Please fill all the fields to continue.</div>');
       status=0;
    }
    else if(email=="" || !emailReg.test(email))
    {
       document.getElementById("email").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("email").style.backgroundPosition="right";
       document.getElementById("email").style.backgroundRepeat="no-repeat";
       $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Please fill all the fields to continue.</div>');
        status=0;
    }
    else if(mobile=="" || !mobileReg.test(mobile) || mobile.length<7 || mobile.length>12)
    {
       document.getElementById("mobile").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("mobile").style.backgroundPosition="right";
       document.getElementById("mobile").style.backgroundRepeat="no-repeat";
       $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Please fill all the fields to continue.</div>');
       status=0;
    }
    else if(subject=="" || !new RegExp("[a-zA-Z]{1}[a-zA-Z0-9/s]*[a-zA-Z]{1}").test(subject))
    {
       document.getElementById("subject").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("subject").style.backgroundPosition="right";
       document.getElementById("subject").style.backgroundRepeat="no-repeat";
       $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Please fill all the fields to continue.</div>');
       status=0;
    }
    else if(message=="" || !new RegExp("[a-zA-Z]{1}[a-zA-Z0-9/s]*[a-zA-Z]{1}").test(message))
    {
       $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Please fill all the fields to continue.</div>');
       status=0;
    }
    if(status==1)
    {
        var formData="name="+name+"&phone="+mobile+"&email="+email+"&subject="+subject+"&message="+message+"&client=browser&action=contact";
        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Please wait...</div>');
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="do";
        req.open("POST", url,true);
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("Connection", "close");
        req.send(formData);
        req.onreadystatechange = function() 
        {
            if(req.readyState == 4)
            {
                if(req.status==200)
                {
                    var check=eval('('+req.responseText+')');
                    if(check.response=="success")
                    {
                        document.getElementById("name").value="";
                        document.getElementById("email").value="";
                        document.getElementById("mobile").value="";
                        document.getElementById("subject").value="";
                        document.getElementById("message").value="";
                        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Your message has been sent successfully.</div>');
                    }
                    else if(check.response=="failure")
                    {
                       $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Service unavailable, please try again.</div>');
                    }
                    else if(check.response=="invalidData"){
                        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Invalid data found.</div>');
                    }
                    else
                    {
                        $("#ErrorDiv").html('<div class="col-md-6 col-md-offset-3 bg-danger" id="warningMsg">Service unavailable, please try again.</div>');
                    }
                }
            }
        };
    }
}
function gotoOnSignIn(){
    window.location="default?action=login";
}