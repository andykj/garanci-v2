/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function changeTimer(Button)
{
    var val = document.getElementById("time").value;

    var hr = parseInt(val.split(":")[0].trim());

    var mn = parseInt(val.split(":")[1].trim());

    if (isNaN(parseInt(hr) + parseInt(mn)))
    {
        alert("time not correct");
        return;
    }
    if (hr < 0 || hr > 23)
    {
        alert("time not correct");
        return;
    }

    if (hr < 0 || hr > 59)
    {
        alert("time not correct");
        return;
    }
    var d = new Date();

    Button.disabled = true;

    var url = "";

    if (document.getElementById("zone").value == '0')
    {
        url = "hr=" + hr + "&mn=" + mn + "&td=" + encodeURIComponent(parseInt(d.getTimezoneOffset()) * 60 * 1000);  //local time
    }
    else
    {
        url = "hr=" + hr + "&mn=" + mn + "&td=" + encodeURIComponent(0);//gmt time
    }
    var xml = new XMLHttpRequest();

    xml.open("GET", "timerReseter?" + url, true);

    xml.onreadystatechange = function ()
    {
        if (xml.status == 200 && xml.readyState == 4)
        {
            Button.innerHTML = xml.responseText;

            location.reload();
        }


    };
    xml.send();
}

function GmtTimeCacculater(timefiff)
{
    return;
    if (timefiff >= 0)
        $(".gnttimespan").html("+ " + timefiff / 1000 / 60);
    else
        document.getElementById("gnttimespan").innerHTML = timefiff / 1000 / 60;
}