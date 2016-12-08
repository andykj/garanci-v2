/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var FIRST_NAME = 1;

var LAST_NAME = 2;

var ADDRESS = 3;

var POSTAL_CODE = 4;

var COUNTRY = 5;

var STATE = 6;

var CITY = 7;

var INCIDENT_DESCRIPTION = 8;

var BRAND_NAME = 9;

var PRODUCT_NAME = 10;

var MODEL_NUMBER = 11;

var SERIAL = 12;

var MANUFACTURE_NAME = 13;

var PURCHASE_DATE = 14;

var RETAILER_LOCATION = 15;


$(document).ready(function () {
    
    $(".vtyper").click(function () {

        if (this.value == '1')
        {
            getInfoSelf();

        }
        else
        {
            $("#vfname").val('');

            $("#vlname").val('');
        }

    });
});


function selectCountry(Id)
{


    if (Id.value == 'India')
    {
        $('.stateList').hide();

        $('#stateIND').show();
        $('#stateIND2').show();

    }
    else if (Id.value == 'United States')
    {


        $('.stateList').hide();
        $('#stateUS').show();
        $('#stateUS2').show();

    }
    else
    {
        $('.stateList').hide();
        $('#stateName').show();
        $('#stateName2').show();

    }
}



function makeAllNextInActive()
{
    $("#vnext").attr('disabled', 'disabled');
    $("#inext").attr('disabled', 'disabled');
    $("#pnext").attr('disabled', 'disabled');
}


function doFade(Id)
{

    $(".infoholder").fadeOut(0, function () {

    });
    $("#" + Id).slideDown(100);

}



function allNone()
{

    $(".fstBox2").css({'box-shadow': 'none'
    });
    $(".fstBox3").css({'box-shadow': 'none'
    });

    $(".fstBox1").css({'box-shadow': 'none'
    });


}

function goToPage1()
{
    $(".fstBox2").css({'box-shadow': 'none'
    });
    $(".fstBox3").css({'box-shadow': 'none'
    });


    $(".fstBox1").css({'box-shadow': '0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23)',
        'background-color': '#FFBABA'
    });
    makeAllNextInActive();
}
function savePage1()
{


    makeAllNextInActive();


    /** validate to insert into database **/


    var victimType = 0;

    $('.vtyper').each(function () {

        if (this.checked)
        {
            victimType = this.value;

            return false;/** breal the loop **/
        }
    });

    if (victimType == 0)
    {
        showFailure('#body', 'Victim type must be selected.');

        return;
    }

    var vfname = $("#vfname").val().trim();

    if (!regExperienceReturnner(FIRST_NAME).test(vfname))
    {
        showFailure('#body', 'Victim First Name not correct.');

        return;
    }

    var vlname = $("#vlname").val().trim();

    if (!regExperienceReturnner(LAST_NAME).test(vlname))
    {
        showFailure('#body', 'Victim Last Name not correct.');

        return;
    }


    var vaddress = $("#vaddress").val().trim();

    if (!regExperienceReturnner(ADDRESS).test(vaddress))
    {
        showFailure('#body', 'Victim Address not correct.');

        return;
    }

    var vpin = $("#vpin").val().trim();

    if (!regExperienceReturnner(POSTAL_CODE).test(vpin))
    {
        showFailure('#body', 'Victim Postal Code not correct.');

        return;
    }

    var vcountry = $("#vcountry").val().trim();

    if (!regExperienceReturnner(COUNTRY).test(vcountry))
    {
        showFailure('#body', 'Victim Country not correct.');

        return;
    }

    var vstate = $("#vstate").val().trim();

    if (!regExperienceReturnner(STATE).test(vstate))
    {
        showFailure('#body', 'Victim State not correct.');

        return;
    }



    var data = {};

    data['vfname'] = vfname;

    data['vlname'] = vlname;

    data['vaddress'] = vaddress;

    data['vpostalCode'] = vpin;

    data['vstate'] = vstate;

    data['vcountry'] = vcountry;

    data['type'] = victimType;

    if ($('#currentIssueId').val() != "")
    {
        data['inId'] = $('#currentIssueId').val();
    }


    $('#hiddenHolder').show();

    $.post("ReportAnIssueInsert?page=1", data, function (response, status) {


        $('#hiddenHolder').hide();

        if (status == 'success')
        {
            var json = eval('(' + response + ')');

            if (json.response === 'success')
            {
                if ($('#currentIssueId').val() == "")
                {
                    $('#currentIssueId').val(json.Id);
                }


                $(".fstBox1").css({'box-shadow': '0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23)',
                    'background-color': '#DFF2BF'
                });




                showSuccess('#body', 'Page One saved successfully , go to <strong><i>NEXT</i></strong> for Page Two.');

                $("#vnext").removeAttr('disabled');

                doFade('victimInfo2');
                goToPage2();


            }
            else
            {

                showFailure('#body', 'Page One not save due to some error.');

            }
        }
    });

}


function goToPage2()
{
    $(".fstBox1").css({'box-shadow': 'none'
    });
    $(".fstBox3").css({'box-shadow': 'none'
    });


    $(".fstBox2").css({'box-shadow': '0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23)',
        'background-color': '#FFBABA'
    });
    makeAllNextInActive();
}
function savePage2()
{


    makeAllNextInActive();



    if (document.getElementById('isaddsame').checked)
    {
        /** enable all field to get data **/
        $("#inaddress").removeAttr('disabled');
        $("#incountry").removeAttr('disabled');
        $("#inpin").removeAttr('disabled');
        $("#instatename").removeAttr('disabled');
        $("#instateIN").removeAttr('disabled');
        $("#instateUS").removeAttr('disabled');
    }

    var inLocationType = 0;

    $('.inlocationType').each(function () {

        if (this.checked)
        {
            inLocationType = this.value;

            return false;/** breal the loop **/
        }
    });

    if (inLocationType == 0)
    {
        showFailure('#body', 'Incident Location type must be selected.');

        return;
    }

    var ides = $("#ides").val().trim();

    if (!regExperienceReturnner(INCIDENT_DESCRIPTION).test(ides))
    {
        showFailure('#body', 'Incident Description not correct.');

        return;
    }

    var inaddress = $("#inaddress").val().trim();

    if (!regExperienceReturnner(ADDRESS).test(inaddress))
    {
        showFailure('#body', 'Incident Address not correct.');

        return;
    }




    var inpin = $("#inpin").val().trim();

    if (!regExperienceReturnner(POSTAL_CODE).test(inpin))
    {
        showFailure('#body', 'Incident Postal Code not correct.');

        return;
    }

    var incountry = $("#incountry").val().trim();

    if (!regExperienceReturnner(COUNTRY).test(incountry))
    {
        showFailure('#body', 'Incident Country not correct.');

        return;
    }

    var instatename = $("#instatename").val().trim();


    if (!regExperienceReturnner(STATE).test(instatename))
    {
        showFailure('#body', 'Incident State not correct.');

        return;
    }
    var incity = $("#incity").val().trim();

    if (!regExperienceReturnner(CITY).test(incity))
    {
        showFailure('#body', 'Incident City not correct.');

        return;
    }

    if (document.getElementById('isaddsame').checked) {
        /** disabled all field after getting data **/

        $("#inaddress").attr('disabled', 'disabled');
        $("#incountry").attr('disabled', 'disabled');
        $("#inpin").attr('disabled', 'disabled');
        $("#instatename").attr('disabled', 'disabled');
        $("#instateIN").attr('disabled', 'disabled');
        $("#instateUS").attr('disabled', 'disabled');

    }

    var data = {};

    data['inId'] = $("#currentIssueId").val();



    data['inDesc'] = ides;

    data['locationType'] = inLocationType;

    data['inaddress'] = inaddress;

    data['inpostalCode'] = inpin;

    data['incountry'] = incountry;

    data['instate'] = instatename;

    data['incity'] = incity;


    $('#hiddenHolder').show();

    $.post("ReportAnIssueInsert?page=2", data, function (response, status) {


        $('#hiddenHolder').hide();

        if (status == 'success')
        {
            var json = eval('(' + response + ')');

            if (json.response === 'success')
            {

                showSuccess('#body', 'Page Two saved successfully , go to <strong><i>NEXT</i></strong> for Page Three.');

                $(".fstBox2").css({'box-shadow': '0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23)',
                    'background-color': '#DFF2BF'
                });


                $("#inext").removeAttr('disabled');

                doFade('victimInfo3');

                goToPage3();


            }
            else
            {

                showFailure('#body', 'Page Two not save due to some error.');

            }
        }
    });







}


function doSubmit()
{


    var data = {};


    data['inId'] = $("#currentIssueId").val();

    data['key'] = document.getElementsByName("g-recaptcha-response")[0].value;

    $('#hiddenHolder').show();

    $.post("ReportAnIssueInsert?page=4", data, function (response, status) {


        $('#hiddenHolder').hide();

        if (status == 'success')
        {
            var json = eval('(' + response + ')');

            if (json.response === 'success')
            {

                showSuccess('#body', 'Your Issue has been submit successfully');

                doFade("victimInfo5");

                $("#topround").hide();


            }
            else if (json.response === 'recapError')
            {

                showFailure('#body', 'reCAPTCHA incorrect.');


            }
            else
            {

                showFailure('#body', 'Page Three not save due to some error.');

            }
        }
    });

}



function doCancel()
{

    var data = {};

    data['inId'] = $("#currentIssueId").val();

    $('#hiddenHolder').show();

    $.post("ReportAnIssueInsert?page=5", data, function (response, status) {


        $('#hiddenHolder').hide();

        if (status == 'success')
        {
            var json = eval('(' + response + ')');

            if (json.response === 'success')
            {

                showSuccess('#body', 'Your Issue has been deleted successfully');

                doFade("victimInfo5");

                $("#topround").hide();


            }
            else
            {

                showFailure('#body', 'Page Three not save due to some error.');

            }
        }
    });

}



function goToPage3()
{


    $(".fstBox2").css({'box-shadow': 'none'
    });
    $(".fstBox1").css({'box-shadow': 'none'
    });


    $(".fstBox3").css({'box-shadow': '0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23)',
        'background-color': '#FFBABA'
    });
    makeAllNextInActive();
}
function savePage3()
{


    makeAllNextInActive();





    var pname = $("#pname").val().trim();

    if (!regExperienceReturnner(PRODUCT_NAME).test(pname))
    {
        showFailure('#body', 'Product Name not correct.');

        return;
    }

    var bname = $("#bname").val().trim();

    if (!regExperienceReturnner(BRAND_NAME).test(bname))
    {
        showFailure('#body', 'Brand Name not correct.');

        return;
    }




    var model = $("#model").val().trim();

    if (!regExperienceReturnner(MODEL_NUMBER).test(model))
    {
        showFailure('#body', 'Model Number not correct.');

        return;
    }

    var serial = $("#serial").val().trim();

    if (serial != "" && !regExperienceReturnner(SERIAL).test(serial))
    {
        showFailure('#body', 'Serial Number not correct.');

        return;
    }

    var manuname = $("#manuname").val().trim();

    if (!regExperienceReturnner(MANUFACTURE_NAME).test(manuname))
    {
        showFailure('#body', 'Manufacturer Name not correct.');

        return;
    }
    var pdate = $("#pdate").val().trim();

    if (!regExperienceReturnner(PURCHASE_DATE).test(pdate))
    {
        showFailure('#body', 'Purchase Date not correct.');

        return;
    }

    var rlocation = $("#rlocation").val().trim();

    if (!regExperienceReturnner(RETAILER_LOCATION).test(rlocation))
    {
        showFailure('#body', 'Retailer Location not correct.');

        return;
    }


    if (new Date(pdate).getTime() > new Date().getTime())
    {
        showFailure('#body', 'Purchase Date Should be less than Current Date.');

        return;
    }

    var data = {};

    data['inId'] = $("#currentIssueId").val();

    data['pname'] = pname;

    data['bname'] = bname;

    data['model'] = model;

    data['serial'] = serial;

    data['manufacname'] = manuname;

    data['purchasedate'] = new Date(pdate).getTime();

    data['retailerlocation'] = rlocation;

    $('#hiddenHolder').show();

    $.post("ReportAnIssueInsert?page=3", data, function (response, status) {


        $('#hiddenHolder').hide();

        if (status == 'success')
        {
            var json = eval('(' + response + ')');

            if (json.response === 'success')
            {

                showSuccess('#body', 'Page Three saved successfully .');


                $("#pnext").removeAttr('disabled');

                doFade('victimInfo4');

                allNone();


                $(".fstBox3").css({'box-shadow': 'none',
                    'background-color': '#DFF2BF'
                });


            }
            else
            {

                showFailure('#body', 'Page Three not save due to some error.');

            }
        }
    });
}



function getInfoSelf()
{

    $('#hiddenHolder').show();

    $.post("getReportData?action=self", function (response, status) {


        $('#hiddenHolder').hide();

        if (status == 'success')
        {
            var json = eval('(' + response + ')');

            $("#vfname").val(json.fname);

            $("#vlname").val(json.lname);
        }
    });
}


function checkNameValidation(Id)
{

    if (!regExperienceReturnner(FIRST_NAME).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}




function checkIncedentDescriptiom(Id)
{
    if (!regExperienceReturnner(INCIDENT_DESCRIPTION).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}




function checkProductName(Id)
{
    if (!regExperienceReturnner(PRODUCT_NAME).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}



function checkBrandName(Id)
{
    if (!regExperienceReturnner(BRAND_NAME).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}



function checkModelNumber(Id)
{
    if (!regExperienceReturnner(MODEL_NUMBER).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}



function checkSerial(Id)
{
    if (!regExperienceReturnner(SERIAL).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}


function checkManufactureName(Id)
{
    if (!regExperienceReturnner(MANUFACTURE_NAME).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}




function checkRetailerLocation(Id)
{
    if (!regExperienceReturnner(RETAILER_LOCATION).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}


function checkPurchedDate(Id)
{
    if (!regExperienceReturnner(PURCHASE_DATE).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}



function checkCity(Id)
{
    if (!regExperienceReturnner(CITY).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}




function checkAddressValidation(Id)
{

    if (!regExperienceReturnner(ADDRESS).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}



function doCopy(Id)
{

    if (Id.checked) {

        var vadd = $("#vaddress").val();

        var vpin = $("#vpin").val();

        var vcountry = $("#vcountry").val();

        var vstate = $("#vstate").val();

        if (vcountry == "United States")
        {
            vstate = $("#vstateselectUS").val();

            $("#instateUS").val(vstate);

        }
        else if (vcountry == "India")
        {

            vstate = $("#vstateselectIN").val();

            $("#instateIN").val(vstate);

        }

        $("#instatename").val(vstate);


        $("#inaddress").val(vadd);
        $("#inaddress").attr('disabled', 'disabled');

        $("#inpin").val(vpin);
        $("#inpin").attr('disabled', 'disabled');

        $("#incountry").val(vcountry);
        $("#incountry").attr('disabled', 'disabled');
        $("#instatename").attr('disabled', 'disabled');


        $("#instateIN").attr('disabled', 'disabled');
        $("#instateUS").attr('disabled', 'disabled');

    }
    else
    {
        $("#inaddress").val('');

        $("#inpin").val('');

        $("#incountry").val('Afghanistan');

        $("#stateIND2").hide();

        $("#stateUS2").hide();

        $("#stateName2").show();

        $("#instatename").val('');


        $("#inaddress").removeAttr('disabled');
        $("#incountry").removeAttr('disabled');
        $("#inpin").removeAttr('disabled');
        $("#instatename").removeAttr('disabled');
        $("#instateIN").removeAttr('disabled');
        $("#instateUS").removeAttr('disabled');

    }

}


function checkPinValidation(Id)
{

    if (!regExperienceReturnner(POSTAL_CODE).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}



function checkStateValidation(Id)
{

    if (!regExperienceReturnner(STATE).test(Id.value))
    {

        $(Id).css({
            'background-image': 'url("images/Error-25.png")',
            'background-repeat': 'no-repeat',
            'background-position-x': '100%',
            'background-position-y': '3px'
        });
    }
    else
    {
        $(Id).css({
            'background-image': 'none'
        });
    }
}


function showSuccess(to, message)
{
    $('.succ').remove();

    var random = Math.floor((Math.random() * 100) + 1);

    var data =
            "\n" +
            "        <div  id='succ" + random + "' class=\"succ alert alert-success center-block\" style=\"position: fixed;width: 700px;;z-index: 250\">\n" +
            "   <strong>Done!</strong>&nbsp;" + message + "\n" +
            "</div>";

    $(to).prepend(data);

    if ($(to).width() <= 700)
    {
        $("#succ" + random).css('width', $(to).width());

        $("#succ" + random).css({"margin-left": 0, 'margin-top': '50px', 'display': 'none'});
    }
    else {

        $("#succ" + random).css({"margin-left": $(to).width() / 2 - 700 / 2, 'margin-top': '50px', 'display': 'none'});
    }
    $("#succ" + random).slideDown();

    setTimeout(function () {
        $('#succ' + random).slideUp();
    }, 3000);

}



function showFailure(to, message)
{
    $('.succ').remove();

    var random = Math.floor((Math.random() * 100) + 1);

    var data =
            "\n" +
            "        <div  id='succ" + random + "'  class=\"succ alert alert-danger center-block\" style=\"position: fixed;width: 700px;;z-index: 250\">\n" +
            "   <strong>Danger!</strong>&nbsp;" + message + "\n" +
            "</div>";

    $(to).prepend(data);

    if ($(to).width() <= 700)
    {
        $("#succ" + random).css('width', $(to).width());

        $("#succ" + random).css({"margin-left": 0, 'margin-top': '50px', 'display': 'none'});
    }
    else {

        $("#succ" + random).css({"margin-left": $(to).width() / 2 - 700 / 2, 'margin-top': '50px', 'display': 'none'});
    }
    $("#succ" + random).slideDown();

    setTimeout(function () {
        $('#succ' + random).slideUp();
    }, 3000);

}






function regExperienceReturnner(type)
{

    if (type == FIRST_NAME || type == LAST_NAME)
        regex = /^[a-zA-Z]{2,30}$/;

    if (type == ADDRESS)
        regex = /^[a-zA-Z0-9\s,'-]{2,100}$/;

    if (type == STATE || type == CITY)
        regex = /^[a-zA-Z ]{2,30}$/;

    if (type == INCIDENT_DESCRIPTION)
        regex = /^[a-zA-Z ]{2,100}$/;

    if (type == PRODUCT_NAME)
        regex = /^[a-zA-Z ]{2,30}$/;

    if (type == POSTAL_CODE)
        regex = /^[0-9]{6}$/;

    if (type == BRAND_NAME)
        regex = /^[a-zA-Z. ]{2,30}$/;

    if (type == MODEL_NUMBER)
        regex = /^[a-zA-Z0-9 ]{2,30}$/;

    if (type == PURCHASE_DATE)
        regex = /^((0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)?[0-9]{2})*$/;

    if (type == RETAILER_LOCATION)
         regex = /^[a-zA-Z0-9\s,'-]{2,100}$/;

    if (type == COUNTRY)
        regex = /^[a-zA-Z0-9 ]{2,30}$/;


    return regex;
}