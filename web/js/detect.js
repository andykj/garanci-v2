/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    var isMobile = {
        Android: function() {
            return navigator.userAgent.match(/Android/i);
        },
        BlackBerry: function() {
            return navigator.userAgent.match(/BlackBerry/i);
        },
        iOS: function() {
            return navigator.userAgent.match(/iPhone|iPad|iPod/i);
        },
        Opera: function() {
            return navigator.userAgent.match(/Opera Mini/i);
        },
        Windows: function() {
            return navigator.userAgent.match(/IEMobile/i);
        },
        any: function() {
            return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
        }
    };
    if(isMobile.any())
    {
        $('body').html('<div id="header">'+
'<div id="headerInside">'+
    '<div id="logoDiv"><img src="images/Garanci_Logo.png" id="GaranciLogo" title="Home"/></div>'+
'</div>'+
'</div>'+
'<div class="row text-center" id="errorDiv"></div>'+
'<div class="container" id="container">'+
'<div class="row">'+
    '<img src="images/AppleApp.png" class="img-responsive" id="appleStore" onclick="goToAppleStore();"><br>'+
    '<div onclick="continueToSite();"><strong>Continue to site.<strong></div>'+
'</div>'+
    '<img src="images/IosScreenShot/ScreenShot1.PNG" class="img-responsive" id="iosApp">'+
'</div>'+
'<div id="footer">'+
'<div class="footerInside" id="footerInsideLeft"><a id="copyRight">&COPY;2014 Garanci.</a><a href="default?action=terms" class="footerInsideLeft">Terms</a><a href="default?action=privacy" class="footerInsideLeft">Privacy</a><a class="footerInsideLeft" title="Contact" href="default?action=contact">Contact</a></div><img src="images/Garanci_Logo.png" id="GaranciFooterLogo"/><div class="footerInside" id="footerInsideRight"><a href="http://garanci.blog.com" target="_blank" class="footerInsideRight">Blog</a><a href="default?action=about" class="footerInsideRight">About</a></div>'+
'</div>');
    }
    else
    {
        window.location="default";
    }
});
function goToAppleStore()
{
    window.location="https://itunes.apple.com/us/app/garanci/id934726813?ls=1&mt=8";
}
function continueToSite()
{
    window.location="default";
}