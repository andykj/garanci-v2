/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var planTypeName=[];
var planTypeValue=[];
var planDurationUnitName=[];
var planDurationUnitValue=[];
$(document).ready(function(){
    fetchWarranties();
});

function hideWarning(id)
{
    document.getElementById(id).style.backgroundImage="none";
}
function hideMessage()
{
    $("#errorDiv").html('');
}
function home()
{
    $("#container").html('<div class="row">'+
                '<div class="col-xs-12 col-sm-6 col-md-8" id="dashboardDiv">'+
                            '<div id="dashboardHeader">My Dashboard'+
                                '<div id="createReceiptDiv">'+
                                    '<div id="colorDiv">'+
                                        '<span id="yellow" class="label label-warning">< 1 Week</span>&nbsp'+
                                        '<span id="orange" class="label label-warning">< 3 Day</span>&nbsp'+
                                        '<span id="red" class="label label-danger">< 1 Day</span>'+               
                                    '</div>'+
                                    '<a id="createReceipt" onclick="showReceiptDiv();">Create New Receipt</a>'+
                                '</div>'+
                            '</div>'+
                        '<div id="dashboardBody">'+
                            
                        '</div>'+
                '</div>'+
            '</div>');
            
    fetchWarranties();
}
function showReturnProduct(id,productId,saleDate,expirationDate)
{
    $("#"+id).html('<table class="table table-striped table-bordered table-responsive">'+
                                            '<tr><th colspan="2">Return Product</th></tr>'+
                                            '<tr><td>Return Date:</td><td><input type="text" id="productReturnDate'+productId+'" class="returnDate" readonly onclick="hideWarning(\'productReturnDate'+productId+'\');"/></td></tr>'+
                                            '<tr><td>Return Notes:</td><td><textarea id="productNotes'+productId+'" class="textArea" onclick="hideWarning(\'productNotes'+productId+'\');" maxlength="255"></textarea><input type="hidden" id="productId'+productId+'" value="'+productId+'"></textarea><div class="textareaNote"><div></td></tr>'+
                                            '<tr><td>Customer Service Rating</td><td><div class="rating">'+
                                                                '<input type="radio" name="rating" value="0" checked /><span id="hide"></span>'+
                                                                '<input type="radio" name="rating" value="1" onclick="selectedStarValue(\'1\',\'starProduct'+productId+'\')" /><span></span>'+
                                                                '<input type="radio" name="rating" value="2" onclick="selectedStarValue(\'2\',\'starProduct'+productId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="3" onclick="selectedStarValue(\'3\',\'starProduct'+productId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="4" onclick="selectedStarValue(\'4\',\'starProduct'+productId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="5" onclick="selectedStarValue(\'5\',\'starProduct'+productId+'\')"/><span></span>'+
                                                            '</div><div id="ratingDiv'+productId+'" class="ratingDiv"></div></td></tr>'+
                                            '<tr><td colspan="2"><input type="hidden" id="starProduct'+productId+'" value=""/><button type="button" class="btn btn-default btn-xs save" onclick="submitReturnProduct(\''+productId+'\');"><strong>Save</strong></button></td></tr>'+
                                        '</table><div id="msgDiv'+productId+'" class="msgDiv"></div>');
                               $(document).ready(function() {
                                   var text_max = 255;
                                   $('.textareaNote').html(text_max + ' characters remaining');
                                   $('.textArea').keydown(function() {
                                       var text_length=$('.textArea').val().length;
                                       var text_remaining = text_max - text_length;
                                       $('.textareaNote').html(text_remaining + ' characters remaining');
                               });
                             });
    
    var datePast=new Date(saleDate.replace( /(\d{4})-(\d{2})-(\d{2})/,"$2/$3/$1"));
    var dateToday=new Date();
    var dateToday2=new Date(expirationDate.replace( /(\d{4})-(\d{2})-(\d{2})/,"$2/$3/$1"));
    if(dateToday2.getTime()<dateToday.getTime())
    {
        dateToday=dateToday2;
    }
    $(".returnDate").datepicker({changeMonth: true,changeYear: true,dateFormat: 'yy-mm-dd',maxDate: dateToday,minDate:datePast});
    $(".save").click(function(event){
      event.stopPropagation();  
    });
}
function showReturnPlan(id,planId,saleDate,expirationDate)
{
    $("#"+id).html('<table class="table table-striped table-bordered table-responsive">'+
                                            '<tr><th colspan="2">Return Plan</th></tr>'+
                                            '<tr><td>Return Date:</td><td><input type="text" id="returnDate'+planId+'" class="returnDate" readonly onclick="hideWarning(\'returnDate'+planId+'\');"/></td></tr>'+
                                            '<tr><td>Return Notes:</td><td><textarea id="notes'+planId+'" class="textArea" onclick="hideWarning(\'notes'+planId+'\');" maxlength="255"></textarea><input type="hidden" id="planId'+planId+'" value="'+planId+'"/><div class="textareaNote"><div></td></tr>'+
                                            '<tr><td>Customer Service Rating</td><td><div class="rating">'+
                                                                '<input type="radio" name="rating" value="0" checked /><span id="hide"></span>'+
                                                                '<input type="radio" name="rating" value="1" onclick="selectedStarValue(\'1\',\'starPlan'+planId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="2" onclick="selectedStarValue(\'2\',\'starPlan'+planId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="3" onclick="selectedStarValue(\'3\',\'starPlan'+planId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="4" onclick="selectedStarValue(\'4\',\'starPlan'+planId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="5" onclick="selectedStarValue(\'5\',\'starPlan'+planId+'\')"/><span></span>'+
                                                            '</div><div id="ratingDiv'+planId+'" class="ratingDiv"></div></td></tr>'+
                                            '<tr><td colspan="2"><input type="hidden" id="starPlan'+planId+'" value=""/><button type="button" class="btn btn-default btn-xs save" onclick="submitReturnPlan(\''+planId+'\');"><strong>Save</strong></button></td></tr>'+
                                        '</table><div id="msgDiv'+planId+'" class="msgDiv"></div>');
                                $(document).ready(function() {
                                   var text_max = 255;
                                   $('.textareaNote').html(text_max + ' characters remaining');
                                   $('.textArea').keydown(function() {
                                       var text_length=$('.textArea').val().length;
                                       var text_remaining = text_max - text_length;
                                       $('.textareaNote').html(text_remaining + ' characters remaining');
                               });
                             });
    var dateToday=new Date();
    var datePast=new Date(saleDate.replace( /(\d{4})-(\d{2})-(\d{2})/,"$2/$3/$1"));
    var dateToday2=new Date(expirationDate.replace( /(\d{4})-(\d{2})-(\d{2})/,"$2/$3/$1"));
    if(dateToday2.getTime()<dateToday.getTime())
    {
        dateToday=dateToday2;
    }
    $(".returnDate").datepicker({changeMonth: true,changeYear: true,dateFormat: 'yy-mm-dd',maxDate: dateToday,minDate:datePast});
    $(".save").click(function(event){
      event.stopPropagation();  
    });
}
function showServiceReview(id,receiptId,saleDate,expirationDate)
{
    $("#"+id).html('<table class="table table-striped table-bordered table-responsive">'+
                                            '<tr><th colspan="2">Service Review</th></tr>'+
                                            '<tr><td>Service Date:</td><td><input type="text" id="serviceDate'+receiptId+'" class="returnDate" readonly onclick="hideWarning(\'serviceDate'+receiptId+'\');"/></td></tr>'+
                                            '<tr><td>Service Notes:</td><td><textarea id="serviceNotes'+receiptId+'" class="textArea" onclick="hideWarning(\'serviceNotes'+receiptId+'\');" maxlength="255"></textarea><input type="hidden" id="receiptId'+receiptId+'" value="'+receiptId+'"/><div class="textareaNote"><div></td></tr>'+
                                            '<tr><td>Service Rating</td><td><div class="rating">'+
                                                                '<input type="radio" name="rating" value="0" checked /><span id="hide"></span>'+
                                                                '<input type="radio" name="rating" value="1"  onclick="selectedStarValue(\'1\',\'customerRating'+receiptId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="2"  onclick="selectedStarValue(\'2\',\'customerRating'+receiptId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="3"  onclick="selectedStarValue(\'3\',\'customerRating'+receiptId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="4"  onclick="selectedStarValue(\'4\',\'customerRating'+receiptId+'\')"/><span></span>'+
                                                                '<input type="radio" name="rating" value="5"  onclick="selectedStarValue(\'5\',\'customerRating'+receiptId+'\')"/><span></span>'+
                                                            '</div><div id="ratingDiv'+receiptId+'" class="ratingDiv"></div></td></tr>'+
                                            '<tr><td colspan="2"><input type="hidden" id="customerRating'+receiptId+'" value=""/><button type="button" class="btn btn-default btn-xs save" onclick="submitServiceReview(\''+receiptId+'\');"><strong>Save</strong></button></td></tr>'+
                                        '</table><div id="msgDiv'+receiptId+'" class="msgDiv"></div>');
                                $(document).ready(function() {
                                   var text_max = 255;
                                   $('.textareaNote').html(text_max + ' characters remaining');
                                   $('.textArea').keydown(function() {
                                       var text_length=$('.textArea').val().length;
                                       var text_remaining = text_max - text_length;
                                       $('.textareaNote').html(text_remaining + ' characters remaining');
                               });
                             });
    var dateToday=new Date();
    var datePast=new Date(saleDate.replace( /(\d{4})-(\d{2})-(\d{2})/,"$2/$3/$1"));
    var dateToday2=new Date(expirationDate.replace( /(\d{4})-(\d{2})-(\d{2})/,"$2/$3/$1"));
    if(dateToday2.getTime()<dateToday.getTime())
    {
        dateToday=dateToday2;
    }
    $(".returnDate").datepicker({changeMonth: true,changeYear: true,dateFormat: 'yy-mm-dd',maxDate: dateToday,minDate:datePast});
    $(".save").click(function(event){
      event.stopPropagation();  
    });
}
function selectedStarValue(val,id)
{
    $("#"+id).val(val);
}
function fetchWarranties()
{
    var formData="action=getWarranties&client=browser";
    var req=null;
    if(window.XMLHttpRequest)
    {
        req=new XMLHttpRequest();
    }
    else
    {
        req=new ActiveXObject("Microsoft.XMLHttp");
    }
    var url="action.do";
    req.open("POST", url,true);
    $("#dashboardBody").html('<p class="msg">Please wait...</p>');
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.setRequestHeader("Content-length", formData.length);
    req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                    var warrantyData=check.warrantyData;
                    
                    if(warrantyData.length>0)
                    {
                        $("#dashboardBody").html('');
                        for(var i=0;i<warrantyData.length;i++)
                        {
                           $("#dashboardBody").append('<h3 class="h3"><table><tr><td class="colorDivTd"><div id="h3'+i+'" class="colorDiv"></div></td><td class="productHeader"><div class="productDesc">'+warrantyData[i].productDesc+'</div></td><td class="warrentyHeader">Sale Date: </td><td class="productHeader"><div class="productDesc">'+warrantyData[i].saleDt+'</div></td><td class="warrentyHeaderType">Warranty Type: </td><td class="productHeader"><div  class="productDesc">'+warrantyData[i].warrantyTypeCd+'</div></td><td>Expiration: </td><td class="productHeader"><div class="productDesc">'+warrantyData[i].expirationDate+'</div></td></tr></table></h3>'+
                            '<div class="accordionDiv">'+
                                '<div class="row">'+
                                    '<div class="col-xs-12 col-sm-6 col-md-8">'+
                                        '<table class="table table-striped table-bordered table-hover table-responsive">'+
                                            '<tr>'+
                                                '<td>Sale Date</td>'+
                                                '<td>'+warrantyData[i].saleDt+'</td>'+
                                            '</tr>'+
                                            '<tr>'+
                                                '<td>Currency</td>'+
                                                '<td>'+warrantyData[i].currencyUnitCd+'</td>'+
                                            '</tr>'+
                                            '<tr>'+
                                                '<td>Product Description</td>'+
                                                '<td>'+warrantyData[i].productDesc+'</td>'+
                                            '</tr>'+
                                            '<tr>'+
                                                '<td>Sale Amount</td>'+
                                                '<td>'+warrantyData[i].saleAmt+'</td>'+
                                            '</tr>'+
                                            '<tr>'+
                                                '<td>Plan Type</td>'+
                                                '<td>'+warrantyData[i].warrantyTypeCd+'</td>'+
                                            '</tr>'+
                                            '<tr>'+
                                                '<td>Plan Duration</td>'+
                                                '<td>'+warrantyData[i].durationNbr+'&nbsp;'+warrantyData[i].durationUnit+'</td>'+
                                            '</tr>'+
                                            '<tr>'+
                                                '<td>Plan Amount</td>'+
                                                '<td>'+warrantyData[i].planAmt+'</td>'+
                                            '</tr>'+
                                            '<tr>'+
                                                '<td>Vendor</td>'+
                                                '<td>'+warrantyData[i].vendorNm+'</td>'+
                                            '</tr>'+
                                            '<tr>'+
                                                '<td>Location</td>'+
                                                '<td>'+warrantyData[i].locationNm+'</td>'+
                                            '</tr>'+
                                        '</table>'+
                                        '<div class="row-fluid" id="optionDiv">'+
                                            '<div class="row-fluid" id="optionDivNotes">'+
                                                'If the product has been returned or a plan has been canceled, please select the appropriate menu below to update us with the latest'+
                                            '</div>'+
                                            '<div class="row-fluid"><a class="productPlanService" onclick="showReturnProduct(\'returnProductDiv'+warrantyData[i].productPlanId+'\',\''+warrantyData[i].productId+'\',\''+warrantyData[i].saleDt+'\',\''+warrantyData[i].expirationDate+'\');"><b>Return Product</b></a>'+
                                            '<a class="productPlanService" onclick="showReturnPlan(\'returnProductDiv'+warrantyData[i].productPlanId+'\',\''+warrantyData[i].productPlanId+'\',\''+warrantyData[i].saleDt+'\',\''+warrantyData[i].expirationDate+'\');"><b>Return Plan</b></a>'+
                                            '<a class="productPlanService" onclick="showServiceReview(\'returnProductDiv'+warrantyData[i].productPlanId+'\',\''+warrantyData[i].receiptId+'\',\''+warrantyData[i].saleDt+'\',\''+warrantyData[i].expirationDate+'\');"><b>Service Review</b></a></div>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="col-xs-6 col-md-4" id="returnProductDiv'+warrantyData[i].productPlanId+'">'+
                                    '</div>'+
                                '</div>'+
                            '</div>');
                          $("#h3"+i).css("background-color",warrantyData[i].color);
                        }
                        $("#dashboardBody").accordion({
                            active: false,
                            collapsible: true,
                            heightStyle: "content",
                            icons: {
                                "header": "ui-icon-plus",
                                "activeHeader": "ui-icon-minus"
                            }
                        });
                    }
                    else
                    {
                        $("#dashboardBody").html("<p class=\"msg\">No warranty list found</p>");
                    }
                }
                else if(check.response=="failure")
                {
                    $("#dashboardBody").html('<p class="msg">Service unavilable, please try again</p>');
                }
                else if(check.response=="sessionExpired")
                {
                    location.reload();
                }
                else
                {
                    $("#dashboardBody").html('<p class="msg">Service unavilable, please try again</p>');
                }
            }
            else
            {
                $("#dashboardBody").html('<p class="msg">Error: Please check your internet connection</p>');
            }
        }
    };
}
function showReceiptDiv()
{
account="";
cardType="";
transactionId="";
vendorCode="";
    $("#container").html('<div class="row-fluid" id="householdDiv">'+
                '<div class="row-fluid" id="householdHeader">'+
                    'Create New Receipt'+
                '</div>'+
                '<div class="row-fluid" id="householdBody">'+
                    '<form class="form-inline" role="form" id="addReceipt" name="addReceipt">'+
                        '<div class="form-group col-md-8">'+
                            '<label for="chooseReceipt" class="col-sm-5 lableFloat">Add Receipt</label>'+
                             '<input class="col-md-5" type="file" id="chooseReceipt"/>'+
                        '</div>'+
                        '<div class="form-group">'+
                           '<div class="col-md-8">'+
                                '<button type="button" class="btn btn-default btn-sm" onclick="showScannedProducts();" id="uploadButton"><strong>Upload</strong></button>'+
                                '<div class="showScanProd">'+
                                    '<div class="insideScanProd">'+
                                    '</div>'+
                                '</div>'+
                           '</div>'+
                        '</div>'+
                    '</form>'+
                    '<form class="form-horizontal"role="form" id="householdForm" action="#" >'+
                      '<div class="form-group">'+
                        '<label for="saleDate" class="col-sm-3 lableFloat">Sale Date</label>'+
                        '<div class="col-md-4">'+
                          '<input type="text" class="form-control" id="saleDate" placeholder="" onblur="checkBlank(\'saleDate\');" readonly onchange="hideWarning(\'saleDate\');">'+
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="currency" class="col-sm-3 lableFloat">Currency</label>'+
                        '<div class="col-md-4">'+
                          '<select class="form-control" id="currency0" required onclick="hideWarning(\'currency0\');">'+
                          '</select>'+
                          '</div>'+
                      '</div>'+
                      '<div class="row-fluid" id="productDescList">'+
                       '<div class="" id="productList1">'+
                       '<div class="form-group">'+
                        '<label for="productDesc" class="col-sm-3 lableFloat">Product Description</label>'+
                        '<div class="col-md-4">'+
                          '<input type="text" class="form-control" id="productDesc0" placeholder="Product Name or Description" onblur="checkBlank(\'productDesc0\');" required onclick="hideWarning(\'productDesc0\');" maxlength="255">'+
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="saleAmount" class="col-sm-3 lableFloat">Sale Amount</label>'+
                        '<div class="col-md-4">'+
                          '<input type="text" class="form-control" id="saleAmount0" maxlength="11" placeholder="99999999.99" required onblur="checkAmount(\'saleAmount0\');" onclick="hideWarning(\'saleAmount0\');">'+
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="planType" class="col-sm-3 lableFloat">Plan Type</label>'+
                        '<div class="col-md-4">'+
                          '<select class="form-control" id="planType0" required onclick="hideWarning(\'planType0\');">'+
                          '<option value="">Choose plan type</option>'+
                          '</select>'+
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="planDuration" class="col-sm-3 lableFloat">Plan Duration</label>'+
                        '<div class="col-md-4">'+
                        '<div class="input-group" id="planDurDiv">'+
                          '<input type="text" class="form-control" id="planDuration0" placeholder="Plan Duration" maxlength="2" required onblur="checkPlanDur(\'planDuration0\');" onclick="hideWarning(\'planDuration0\');">'+
                          '<span class="input-group-addon" id="PlanSpan"><select class="planDurSelect form-control" id="planSelect0">'+
                           '</select></span>'+
                         '</div>'+ 
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="planAmount" class="col-sm-3 lableFloat">Plan Amount</label>'+
                        '<div class="col-md-4">'+
                          '<input type="text" class="form-control" id="planAmount0" maxlength="9" placeholder="999999.99" required onblur="checkAmount(\'planAmount0\');" onclick="hideWarning(\'planAmount0\');">'+
                        '</div>'+
                      '</div>'+
                      '</div>'+
                      '</div>'+
                        '<div class="form-group">'+
                        '<label for="vendor" class="col-sm-3 lableFloat">Vendor</label>'+
                        '<div class="col-md-4">'+
                          '<input type="text" class="form-control" id="vendor" placeholder="Walmart, Costco, etc." onblur="checkBlank(\'vendor\');" required onclick="hideWarning(\'vendor\');" maxlength="100">'+
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="location" class="col-sm-3 lableFloat">Location</label>'+
                        '<div class="col-md-4">'+
                          '<input type="text" class="form-control" id="location" placeholder="Columbus, OH"  onblur="checkBlank(\'location\');" required onclick="hideWarning(\'location\');" maxlength="35">'+
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<div class="col-md-8">'+
                          '<button type="button" class="btn btn-default btn-group-lg submitButton" onclick="submitShowReceipt();" ><strong>Save</strong></button>'+
                        '</div>'+
                     '</div>'+
                     '<div class="form-group">'+
                        '<div class="col-md-7" id="receiptFormNotes">'+
                            '* Please make sure to verify the information for accuracy before saving.<br>'+
                            '&nbsp;&nbsp;&nbsp;My dashboard will display the content on what you submit'+
                        '</div>'+
                     '</div>'+
                    '</form>'+
                '</div>'+
            '</div>');
                              $("#planDuration0").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9, 27,110,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                              $("#saleAmount0").keydown(function (e) {
                                           if($.inArray(e.keyCode, [46,8,9,190,27,110,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                              $("#planAmount0").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9,190,27,110,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                            $("#vendor").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                        });
                            
                                   
    var dateToday=new Date();
    var dateCurrent=dateToday.getFullYear();
    var datePast=new Date();
    datePast.setFullYear(dateCurrent-70);
    $("#saleDate").datepicker({changeMonth: true,changeYear: true,dateFormat: 'yy-mm-dd',maxDate: dateToday,minDate:datePast});
    $("#uploadButton").click(function(event){
      event.stopPropagation();  
    });
    $(".submitButton").click(function(event){
      event.stopPropagation();  
    });
    fetchCurrencyPlanTypePlanDurationUnit();
}
var account="";
var cardType="";
var transactionId="";
var vendorCode="";
var receiptData="";
function showScannedProducts()
{
    if( window.FormData === undefined  ||  window.FormData === null )
    {
        $("#errorDiv").html('Please update your browser to higher version to use this feature (or) Go ahead and enter the warranty details below and save them.');
    }
    var file=document.getElementById("chooseReceipt").files[0];
    if(file==null || file=="")
    {
        $("#errorDiv").html('Please choose a file!');
    }
    else
    {
    productId.length=0;
    receiptData="";
    var formData=new FormData();
    formData.append("client","browser");
    formData.append("file1", document.getElementById("chooseReceipt").files[0]);
    $.ajax({
        url: 'UploadFile.do',
        data: formData,
        cache: true,
        dataType: 'json',
        contentType: false,
        processData: false,
        type: 'POST',
        beforeSend:function()
        {
            $("#errorDiv").html('Processing receipt...');
            $("#uploadButton").html("Processing...");
        },
        success: function (evaluatedResult) 
        {
            $("#uploadButton").html("Upload");console.log(evaluatedResult);
            if(evaluatedResult.response=="success")
            {
                $("#errorDiv").html('');
                var productDesc= evaluatedResult.productDesc;
                if(productDesc.length==0)
                {
                    var vendorName= evaluatedResult.vendorName;
                    var address= evaluatedResult.address;
                    receiptData=evaluatedResult.imageData;
                     account= evaluatedResult.account;
                     cardType= evaluatedResult.cardType;
                     transactionId= evaluatedResult.transactionId;
                     vendorCode= evaluatedResult.vendorCode;
                     saleDate=evaluatedResult.saleDate;
                     if(account=="" || account==undefined)
                     {
                         account=" ";
                     }
                     if(cardType=="" || cardType==undefined)
                     {
                         cardType=" ";
                     }
                     if(transactionId=="" || transactionId==undefined)
                     {
                         transactionId=" ";
                     }
                     if(vendorCode=="" || vendorCode==undefined)
                     {
                         vendorCode=" ";
                     }
                    if(saleDate==undefined || saleDate=="")
                    {
                           saleDate = "";
                    }
                    if(vendorName==undefined || vendorName=="")
                    {
                           vendorName= "";
                    }
                    if(address==undefined){
                           address= "";
                    }
                    
                    $("#saleDate").val(saleDate);
                    $("#vendor").val(vendorName);
                    $("#location").val(address);
                    $("#errorDiv").html('The receipt has not been processed. Please key in the warranty data on the create new receipt form or retry the photo image upload again.');
                }
                else
                {
                    $(".showScanProd").css("visibility","visible");
                    $(".insideScanProd").html('<div class="cancel"><img src="images/close.png"  onclick="hidePopUp();"/></div><div id="popUpTable"><div id="popUpHeader"><center><strong>Select Product To Add</strong></center></div><table class="table table-striped table-bordered table-hover table-responsive" id="productTable">'+
                                                    '<tr><th></th><th class="text-center">Prod Description</th><th class="text-center">Sale Amount</th><th class="text-center">Plan Type</th><th class="text-center">Plan Duration</th><th class="text-center">Plan Duration Unit</th><th class="text-center">Plan Amount</th></tr>');
                    var saleDate= evaluatedResult.saleDate;
                    var productAmount= evaluatedResult.productAmount;
                    var planDuration= evaluatedResult.planDuration;
                    var planType= evaluatedResult.planType;
                    var planDurationUnit= evaluatedResult.planDurationUnit;
                    var planAmount= evaluatedResult.planAmount;
                    var vendorName= evaluatedResult.vendorName;
                    var address= evaluatedResult.address;
                    receiptData=evaluatedResult.imageData;
                     account= evaluatedResult.account;
                     cardType= evaluatedResult.cardType;
                     transactionId= evaluatedResult.transactionId;
                     vendorCode= evaluatedResult.vendorCode;
                     if(account=="" || account==undefined)
                     {
                         account=" ";
                     }
                     if(cardType=="" || cardType==undefined)
                     {
                         cardType=" ";
                     }
                     if(transactionId=="" || transactionId==undefined)
                     {
                         transactionId=" ";
                     }
                     if(vendorCode=="" || vendorCode==undefined)
                     {
                         vendorCode=" ";
                     }
                    if(saleDate==undefined || saleDate=="")
                    {
                           saleDate = "";
                    }
                    if(vendorName==undefined || vendorName=="")
                    {
                           vendorName= "";
                    }
                    if(address==undefined){
                           address= "";
                    }
                    $("#saleDate").val(saleDate);
                    $("#vendor").val(vendorName);
                    $("#location").val(address);
                    var i=0;var k=0;
                    for(i=0;i<productDesc.length;i++)
                    {
                       var productDesc1=productDesc[i];
                       var productAmount1=productAmount[i];
                       var planDuration1=planDuration[i];
                       var planType1=planType[i];
                       var planDurationUnit1=planDurationUnit[i];
                       var planAmount1=planAmount[i];
                       if(productDesc1==undefined){
                           productDesc1= "";
                       }
                       if(productAmount1==undefined){
                           productAmount1= "";
                       }
                       if(planDuration1==undefined){
                           planDuration1= "";
                       }
                       if(planType1==undefined){
                           planType1= "";
                       }
                       if(planDurationUnit1==undefined){
                           planDurationUnit1= "";
                       }
                       if(planAmount1==undefined){
                           planAmount1= "";
                       }
                       var currentPlanTypeValue="<select class=\"form-control\" id=planType"+i+">";
                       for(var j=0;j<planTypeName.length;j++)
                       {
                            
                            if(planType1==planTypeName[j])
                            {
                                currentPlanTypeValue=currentPlanTypeValue+"<option value=\""+planTypeValue[j]+"\" selected>"+planTypeName[j]+"</option>";
                            }else
                            {
                                currentPlanTypeValue=currentPlanTypeValue+"<option value=\""+planTypeValue[j]+"\">"+planTypeName[j]+"</option>";
                            }
                       }
                       currentPlanTypeValue=currentPlanTypeValue+"</select>";
                       var currentPlanDurationUnitValue="<select class=\"form-control\" id=planDurationUnit"+i+">";

                       for( k=0;k<planDurationUnitName.length;k++)
                       {
                            if(planDurationUnit1==planDurationUnitName[k])
                            {
                                currentPlanDurationUnitValue=currentPlanDurationUnitValue+"<option value=\""+planDurationUnitValue[k]+"\" selected>"+planDurationUnitName[k]+"</option>"; 
                            }
                            else
                            {
                                currentPlanDurationUnitValue=currentPlanDurationUnitValue+"<option value=\""+planDurationUnitValue[k]+"\">"+planDurationUnitName[k]+"</option>"; 
                            }
                       }

                       currentPlanDurationUnitValue=currentPlanDurationUnitValue+"</select>";
                       $("#productTable").append('<tr id="proRow"><th><input type="checkbox" class="productCheckbox" id="'+i+'" /></th><th><input type="text" class="form-control" value="'+productDesc1+'" id="checkedProdDresc'+i+'"/></th><th><input type="text" class="form-control" value="'+productAmount1+'" id="checkedProdAmt'+i+'"/></th><th>'+currentPlanTypeValue+'</th><th><input type="text" class="form-control" value="'+planDuration1+'" id="checkedPlanDur'+i+'"/></th><th>'+currentPlanDurationUnitValue+'</th><th><input type="text" class="form-control" value="'+planAmount1+'" id="checkedPlanAmt'+i+'"/></th></tr>');
                       $("#checkedProdAmt"+i).css("width","115px");
                       $("#checkedPlanDur"+i).css("width","115px");
                       $("#checkedPlanAmt"+i).css("width","115px");
                    }

                    $(".insideScanProd").append('</table></div><div class="row-fluid text-center"><button type="button" class="btn btn-default btn-group-lg" onclick="submitProductPopUp();"><strong>Ok</strong></button></div>');
                }
            }
            else if(evaluatedResult.response=="failure")
            {
                $("#errorDiv").html('The receipts has not been processed. Please key in the warranty data on the create new receipt form or retry the photo image upload again'); 
            }
            else if(evaluatedResult.response=="invalidData")
            {
                $("#errorDiv").html('The receipts has not been processed. Please key in the warranty data on the create new receipt form or retry the photo image upload again');
            }
            else
            {
                $("#errorDiv").html('The receipts has not been processed. Please key in the warranty data on the create new receipt form or retry the photo image upload again');
            }
        },
        error: function()
        {
            $("#uploadButton").html("Upload");
            $("#errorDiv").html('The receipts has not been processed. Please key in the warranty data on the create new receipt form or retry the photo image upload again');
        }
    });
    }
}
var productId=[];
function submitProductPopUp()
{
    $(".productCheckbox").each(function(){ 
        if($(this).prop('checked')==true){
         
             productId.push($(this).attr("id"));
        }
    });
    $("#productDescList").html('');
    for(var i=0;i<productId.length;i++)
        {
            var planDurationUnit1=$("#planDurationUnit"+productId[i]).find(":selected").text();
            var planType1=$("#planType"+productId[i]).find(":selected").text();
            
            var currentPlanTypeValue="<select class=\"form-control\" id=planType"+i+">";
               for(var j=0;j<planTypeName.length;j++)
               {
                    if(planType1==planTypeName[j])
                    {
                        currentPlanTypeValue=currentPlanTypeValue+"<option value=\""+planTypeValue[j]+"\" selected>"+planTypeName[j]+"</option>";
                    }else
                    {
                        currentPlanTypeValue=currentPlanTypeValue+"<option value=\""+planTypeValue[j]+"\">"+planTypeName[j]+"</option>";
                        
                    }
               }
               currentPlanTypeValue=currentPlanTypeValue+"</select>";
               var currentPlanDurationUnitValue="<select class=\"form-control\" id=planSelect"+i+">";
               for(var k=0;k<planDurationUnitName.length;k++)
               {
                    if(planDurationUnit1==planDurationUnitName[k])
                    {
                        currentPlanDurationUnitValue=currentPlanDurationUnitValue+"<option value=\""+planDurationUnitValue[k]+"\" selected>"+planDurationUnitName[k]+"</option>"; 
                    }
                    else
                    {
                        currentPlanDurationUnitValue=currentPlanDurationUnitValue+"<option value=\""+planDurationUnitValue[k]+"\">"+planDurationUnitName[k]+"</option>"; 
                    }
               }
               currentPlanDurationUnitValue=currentPlanDurationUnitValue+"</select>";
            var productDesc=$("#checkedProdDresc"+productId[i]).val();
            var productAmount=$("#checkedProdAmt"+productId[i]).val();
            var planDuration=$("#checkedPlanDur"+productId[i]).val();
            var planAmount=$("#checkedPlanAmt"+productId[i]).val();
    
            $("#productDescList").append('<div id="productList"><div class="form-group">'+
                                        '<label for="productDesc" class="col-sm-3 lableFloat">Product Description</label>'+
                                        '<div class="col-md-4">'+
                                          '<input type="text" class="form-control" id="productDesc'+i+'" placeholder="Product Name or Description" onblur="checkBlank(\'productDesc'+i+'\');"required onclick="hideWarning(\'productDesc'+i+'\');" maxlength="255" value="'+productDesc+'">'+
                                        '</div>'+
                                      '</div>'+
                                      '<div class="form-group">'+
                                        '<label for="saleAmount" class="col-sm-3 lableFloat">Sale Amount</label>'+
                                        '<div class="col-md-4">'+
                                          '<input type="text" class="form-control" id="saleAmount'+i+'" maxlength="11" placeholder="999999999.99" required onblur="checkAmount(\'saleAmount'+i+'\');" onclick="hideWarning(\'saleAmount'+i+'\');" value="'+productAmount+'">'+
                                        '</div>'+
                                      '</div>'+
                                      '<div class="form-group">'+
                                        '<label for="planType" class="col-sm-3 lableFloat">Plan Type</label>'+
                                        '<div class="col-md-4">'+currentPlanTypeValue+
                                        '</div>'+
                                      '</div>'+
                                      '<div class="form-group">'+
                                        '<label for="planDuration" class="col-sm-3 lableFloat">Plan Duration</label>'+
                                        '<div class="col-md-4">'+
                                        '<div class="input-group" id="planDurDiv">'+
                                          '<input type="text" class="form-control" id="planDuration'+i+'" placeholder="Plan Duration" maxlength="4" required onblur="checkPlanDur(\'planDuration'+i+'\');" onclick="hideWarning(\'planDuration'+i+'\');" value="'+planDuration+'">'+
                                          '<span class="input-group-addon" id="PlanSpan">'+currentPlanDurationUnitValue+'</span>'+
                                         '</div>'+ 
                                        '</div>'+
                                      '</div>'+
                                      '<div class="form-group">'+
                                        '<label for="planAmount" class="col-sm-3 lableFloat">Plan Amount</label>'+
                                        '<div class="col-md-4">'+
                                          '<input type="text" class="form-control" id="planAmount'+i+'" maxlength="9" placeholder="999.99" required onblur="checkAmount(\'planAmount'+i+'\');" onclick="hideWarning(\'planAmount'+i+'\');" value="'+planAmount+'">'+
                                        '</div>'+
                                      '</div></div>');
            $("#planSelect"+i).css("height","34px");
            $("#planSelect"+i).css("width","80px");
            $("#planSelect"+i).css("padding","0px 0px");    
            $("#planSelect"+i).css("border-top-right-radius","4px");
            $("#planSelect"+i).css("border-bottom-right-radius","4px");
    }
    if(productId.length!=0)
    {
        $("#productList1").hide(); 
    }else{
        $("#productDescList").html('<div id="productList1">'+
                       '<div class="form-group">'+
                        '<label for="productDesc" class="col-sm-3 lableFloat">Product Desc</label>'+
                        '<div class="col-md-4">'+
                          '<input type="text" class="form-control" id="productDesc0" placeholder="Product Name or Description" onblur="checkBlank(\'productDesc0\');" required onclick="hideWarning(\'productDesc0\');" maxlength="255">'+
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="saleAmount" class="col-sm-3 lableFloat">Sale Amount</label>'+
                        '<div class="col-md-4">'+
                          '<input type="text" class="form-control" id="saleAmount0" maxlength="11" placeholder="999999999.99" required onblur="checkAmount(\'saleAmount0\');" onclick="hideWarning(\'saleAmount0\');">'+
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="planType" class="col-sm-3 lableFloat">Plan Type</label>'+
                        '<div class="col-md-4">'+
                          '<select class="form-control" id="planType0" required onclick="hideWarning(\'planType0\');">'+
                          '<option value="">Choose plan type</option>'+
                          '</select>'+
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="planDuration" class="col-sm-3 lableFloat">Plan Duration</label>'+
                        '<div class="col-md-4">'+
                        '<div class="input-group" id="planDurDiv">'+
                          '<input type="text" class="form-control" id="planDuration0" placeholder="Plan Duration" maxlength="4" required onblur="checkPlanDur(\'planDuration0\');" onclick="hideWarning(\'planDuration0\');">'+
                          '<span class="input-group-addon" id="PlanSpan"><select class="planDurSelect form-control" id="planSelect0">'+
                           '</select></span>'+
                         '</div>'+ 
                        '</div>'+
                      '</div>'+
                      '<div class="form-group">'+
                        '<label for="planAmount" class="col-sm-3 lableFloat">Plan Amount</label>'+
                        '<div class="col-md-4">'+
                          '<input type="text" class="form-control" id="planAmount0" maxlength="9" placeholder="999.99" required onblur="checkAmount(\'planAmount0\');" onclick="hideWarning(\'planAmount0\');">'+
                        '</div>'+
                      '</div>'+
                        '</div>');
                              $("#planDuration0").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9, 27,110,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                              $("#saleAmount0").keydown(function (e) {
                                           if($.inArray(e.keyCode, [46,8,9,190,27,110,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                              $("#planAmount0").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9,190,27,110,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                    //alert("1 :" + planTypeName.length);
                   for(var i=0;i<planTypeName.length;i++)
                    {
                        var selected=document.getElementById("planType0");
                        var option = document.createElement("option");
                        option.text =planTypeName[i];
                        option.value=planTypeValue[i];
                        selected.add(option);
                    }
                    for(var i=0;i<planDurationUnitName.length;i++)
                    {
                        var selected=document.getElementById("planSelect0");
                        var option = document.createElement("option");
                        option.text =planDurationUnitName[i];
                        option.value=planDurationUnitValue[i];
                        selected.add(option);
                    }
    }
    hidePopUp();
}
function hidePopUp()
{
    $(".showScanProd").css("visibility","hidden");
}

function fetchCurrencyPlanTypePlanDurationUnit()
{
    var formData="action=receiptOptionValue&client=browser";
    var req=null;
    
    planTypeName=[];
    planTypeValue=[];
    planDurationUnitName=[];
    planDurationUnitValue=[];
    
    if(window.XMLHttpRequest)
    {
        req=new XMLHttpRequest();
    }
    else
    {
        req=new ActiveXObject("Microsoft.XMLHttp");
    }
    var url="action.do";
    req.open("POST", url,true);
    $("#errorDiv").html('Please wait...');
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.setRequestHeader("Content-length", formData.length);
    req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                    var currency =evaluatedResult.currencyUnit;
                    for(var i=0;i<currency.length;i++)
                    {
                        var selected=document.getElementById("currency0");
                        var option = document.createElement("option");
                        option.text =currency[i].name;
                        option.value=currency[i].value;
                        selected.add(option);
                    }
                    var planType =evaluatedResult.planType;
                    for(var i=0;i<planType.length;i++)
                    {
                        var selected=document.getElementById("planType0");
                        var option = document.createElement("option");
                        option.text =planType[i].name;
                        option.value=planType[i].value;
                        selected.add(option);
                    }
                    for(var j=0;j<planType.length;j++)
                    {
                        planTypeName.push(planType[j].name);
                        planTypeValue.push(planType[j].value);
                    }
                    var planDurationUnit =evaluatedResult.durationUnit;
                    for(var i=0;i<planDurationUnit.length;i++)
                    {
                        var selected=document.getElementById("planSelect0");
                        var option = document.createElement("option");
                        option.text =planDurationUnit[i].name;
                        option.value=planDurationUnit[i].value;
                        selected.add(option);
                    }
                    for(var j=0;j<planDurationUnit.length;j++)
                    {
                        planDurationUnitName.push(planDurationUnit[j].name);
                        planDurationUnitValue.push(planDurationUnit[j].value);
                    }
                }   
                else if(evaluatedResult.response=="failure")
                {
                    fetchCurrencyPlanTypePlanDurationUnit();
                }
                else if(evaluatedResult.response=="sessionExpired")
                {
                    location.reload();
                }
                else
                {
                    $("#errorDiv").html('Service unavilable, please try again');
                }
            }
        }
    };  
}
function showHouseHold()
{
    var formData="action=getHousehold&client=browser";
    $("#errorDiv").html("Please wait...");
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                        var name=evaluatedResult.FullName;
                        var streetAdd=evaluatedResult.StreetAddress;
                        var cityName=evaluatedResult.CityName;
                        var provinceCode=evaluatedResult.ProvinceCode;
                        var PostalCode=evaluatedResult.PostalCode;
                        var HomePhone=evaluatedResult.HomePhone;
                        $("#container").html('<div class="row-fluid" id="householdDiv">'+
                                    '<div class="row-fluid" id="householdHeader">'+
                                        'Household Detail'+
                                    '</div>'+
                                    '<div class="row-fluid" id="householdBody">'+
                                        'If you want to utilize our service of registering your purchased product or a warranty '+
                                        'plan to the manufacturing websites, enter these details where you would never miss a '+
                                        'warranty or product registration in oversight during the busy times. All details are '+
                                        'securely saved.'+
                                        '<form class="form-horizontal" role="form" id="householdForm" action="#">'+
                                          '<div class="form-group">'+
                                            '<label for="name" class="col-sm-3 lableFloat ">Name</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="name" placeholder="First Name,Last Name" maxlength="75" onblur="checkBlank(\'name\');" onclick="hideWarning(\'name\');" value="'+name+'">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="address" class="col-sm-3 lableFloat">Street Address</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="address" placeholder="Street Address" maxlength="30" onblur="checkBlank(\'address\');" onclick="hideWarning(\'address\');" value="'+streetAdd+'">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="postalCode" class="col-sm-3 lableFloat">Postal Code</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="postalCode" placeholder="Postal Code" onblur="checkBlank(\'postalCode\');" onclick="hideWarning(\'postalCode\');" maxlength="10" value="'+PostalCode+'">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="state" class="col-sm-3 lableFloat">City Name</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="city" onblur="checkBlank(\'city\');" placeholder="City Name" onclick="hideWarning(\'city\');" maxlength="30" value="'+cityName+'">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="state" class="col-sm-3 lableFloat">Province Code</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="state" placeholder="ProvinceCd" onblur="checkBlank(\'state\');" onclick="hideWarning(\'state\');" maxlength="3" value="'+provinceCode+'">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="phone" class="col-sm-3 lableFloat">Household Phone</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="tel" class="form-control" id="phone" placeholder="Household Phone" onblur="checkBlank(\'phone\');" onclick="hideWarning(\'phone\');" maxlength="12" value="'+HomePhone+'">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                              '<div class="col-md-8">'+
                                              '<button type="button" class="btn btn-default btn-group-lg submitButton" class="submitButton" onclick="submitHouseHold();"><strong>Save</strong></button>'+
                                              '</div>'+
                                          '</div>'+
                                        '</form>'+
                                    '</div>'+
                                '</div>');
                                         $("#name").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8,32,9,27,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                           if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#postalCode").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#city").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8,32,9,27,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                           if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                         $("#state").keydown(function (e) {
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
                                        $(".submitButton").click(function(event){
                                          event.stopPropagation();  
                                        });
                    }
                     else if(check.response=="failure")
                    {
                        $("#errorDiv").html('Unable to fetch your saved data');
                        $("#container").html('<div class="row-fluid" id="householdDiv">'+
                                    '<div class="row-fluid" id="householdHeader">'+
                                        'Household Detail'+
                                    '</div>'+
                                    '<div class="row-fluid" id="householdBody">'+
                                        'If you want to utilize our service of registering your purchased product or a warranty '+
                                        'plan to the manufacturing websites, enter these details where you would never miss a '+
                                        'warranty or product registration in oversight during the busy times. All details are '+
                                        'securely saved.'+
                                        '<form class="form-horizontal" role="form" id="householdForm" action="#">'+
                                          '<div class="form-group">'+
                                            '<label for="name" class="col-sm-3 lableFloat">Name</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="name" placeholder="Name" maxlength="75" onblur="checkBlank(\'name\');" onclick="hideWarning(\'name\');" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="address" class="col-sm-3 lableFloat">Street Address</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="address" placeholder="Street Address" maxlength="30" onblur="checkBlank(\'address\');" onclick="hideWarning(\'address\');"value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="postalCode" class="col-sm-3 lableFloat">Postal Code</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="postalCode" placeholder="Postal Code" onblur="checkBlank(\'postalCode\');" onclick="hideWarning(\'postalCode\');" maxlength="10" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="state" class="col-sm-3 lableFloat">City Name</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="city" placeholder="City Name" onblur="checkBlank(\'city\');" onclick="hideWarning(\'city\');" maxlength="" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="state" class="col-sm-3 lableFloat">ProvinceCd</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="state" placeholder="ProvinceCd" onblur="checkBlank(\'state\');" onclick="hideWarning(\'state\');" maxlength="3" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="phone" class="col-sm-3 lableFloat">Household Phone</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="tel" class="form-control" id="phone" placeholder="Household Phone" onblur="checkBlank(\'phone\');" onclick="hideWarning(\'phone\');" maxlength="12" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<div class="col-md-8">'+
                                              '<button type="button" class="btn btn-default btn-group-lg submitButton" onclick="submitHouseHold();"><strong>Save</strong></button>'+
                                            '</div>'+
                                          '</div>'+
                                        '</form>'+
                                    '</div>'+
                                '</div>');
                                       $("#name").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8,32,9,27,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                           if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#postalCode").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#city").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8,32,9,27,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                           if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                         $("#state").keydown(function (e) {
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
                                        $(".submitButton").click(function(event){
                                          event.stopPropagation();  
                                        });
                    }
                    else if(check.response=="noResult")
                    {
                         $("#container").html('<div class="row-fluid" id="householdDiv">'+
                                    '<div class="row-fluid" id="householdHeader">'+
                                        'Household Detail'+
                                    '</div>'+
                                    '<div class="row-fluid" id="householdBody">'+
                                        'If you want to utilize our service of registering your purchased product or a warranty '+
                                        'plan to the manufacturing websites, enter these details where you would never miss a '+
                                        'warranty or product registration in oversight during the busy times. All details are '+
                                        'securely saved.'+
                                        '<form class="form-horizontal" role="form" id="householdForm" action="#">'+
                                          '<div class="form-group">'+
                                            '<label for="name" class="col-sm-3 lableFloat">Name</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="name" placeholder="Name" maxlength="75" onblur="checkBlank(\'name\');"onclick="hideWarning(\'name\');" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="address" class="col-sm-3 lableFloat">Street Address</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="address" placeholder="Street Address" maxlength="30" onblur="checkBlank(\'address\');" onclick="hideWarning(\'address\');"value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="postalCode" class="col-sm-3 lableFloat">Postal Code</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="postalCode" placeholder="Postal Code" onblur="checkBlank(\'postalCode\');" onclick="hideWarning(\'postalCode\');" maxlength="10" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="state" class="col-sm-3 lableFloat">City Name</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="city" placeholder="City Name" onblur="checkBlank(\'city\');" onclick="hideWarning(\'city\');" maxlength="" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="state" class="col-sm-3 lableFloat">ProvinceCd</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="state" placeholder="ProvinceCd" onblur="checkBlank(\'state\');" onclick="hideWarning(\'state\');" maxlength="3" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="phone" class="col-sm-3 lableFloat">Household Phone</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="tel" class="form-control" id="phone" placeholder="Household Phone" onblur="checkBlank(\'phone\');" onclick="hideWarning(\'phone\');" maxlength="12" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<div class="col-md-8">'+
                                              '<button type="button" class="btn btn-default btn-group-lg submitButton" onclick="submitHouseHold();" ><strong>Save</strong></button>'+
                                            '</div>'+
                                          '</div>'+
                                        '</form>'+
                                    '</div>'+
                                '</div>');
                                    $("#name").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8,32,9,27,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                           if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#postalCode").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#city").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8,32,9,27,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                           if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                         $("#state").keydown(function (e) {
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
                                        $(".submitButton").click(function(event){
                                          event.stopPropagation();  
                                        });
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else
                    {
                        $("#container").html('<div class="row-fluid" id="householdDiv">'+
                                    '<div class="row-fluid" id="householdHeader">'+
                                        'Household Detail'+
                                    '</div>'+
                                    '<div class="row-fluid" id="householdBody">'+
                                        'If you want to utilize our service of registering your purchased product or a warranty '+
                                        'plan to the manufacturing websites, enter these details where you would never miss a '+
                                        'warranty or product registration in oversight during the busy times. All details are '+
                                        'securely saved.'+
                                        '<form class="form-horizontal" role="form" onsubmit="submitHouseHold(); return false" id="householdForm" action="#">'+
                                          '<div class="form-group">'+
                                            '<label for="name" class="col-sm-3 lableFloat">Name</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="name" placeholder="Name" maxlength="75" onblur="checkBlank(\'name\');" onclick="hideWarning(\'name\');" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="address" class="col-sm-3 lableFloat">Street Address</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="address" placeholder="Street Address" onblur="checkBlank(\'address\');" maxlength="30" onclick="hideWarning(\'address\');"value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="postalCode" class="col-sm-3 lableFloat">Postal Code</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="postalCode" placeholder="Postal Code" onblur="checkBlank(\'postalCode\');" onclick="hideWarning(\'postalCode\');" maxlength="10" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="state" class="col-sm-3 lableFloat">City Name</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="city" placeholder="City Name" onblur="checkBlank(\'city\');" onclick="hideWarning(\'city\');" maxlength="" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="state" class="col-sm-3 lableFloat">ProvinceCd</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="text" class="form-control" id="state" placeholder="ProvinceCd" onblur="checkBlank(\'state\');" onclick="hideWarning(\'state\');" maxlength="3" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="phone" class="col-sm-3 lableFloat">Household Mobile</label>'+
                                            '<div class="col-md-4">'+
                                              '<input type="tel" class="form-control" id="phone" placeholder="Household Mobile" onblur="checkBlank(\'phone\');" onclick="hideWarning(\'phone\');" maxlength="12" value="">'+
                                            '</div>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label class="col-sm-3 lableFloat"></label>'+
                                            '<div class="col-md-4">'+
                                              '<button type="submit" class="btn btn-default btn-group-lg submitButton" ><strong>Save</strong></button>'+
                                            '</div>'+
                                          '</div>'+
                                        '</form>'+
                                    '</div>'+
                                '</div>');
                                 $("#name").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8,32,9,27,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                           if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#postalCode").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $("#city").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8,32,9,27,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                           if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });
                                         $("#state").keydown(function (e) {
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
                                        $(".submitButton").click(function(event){
                                          event.stopPropagation();  
                                        });
                    }
                }
                else
                {
                    $("#errorDiv").html('Error: Please check your internet connection');
                }
            }
        };
}
function showNotification()
{   
    var formData="action=getNotification&client=browser";
    $("#errorDiv").html("Please wait...");
   
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                       var day=0;
                       var week=0;    
                       var month=0;
                       var notification=evaluatedResult.notification;
                       for(var i=0;i<notification.length;i++){
                           var termCd=notification[i].termCd;
                           var alertFlg=notification[i].alertFlg;
                           if(termCd==1)
                           {
                               if(alertFlg=="Y"){
                               day=1;    
                               }else
                               {
                                   day=0;
                               }
                               
                           }
                           else if(termCd==7)
                           { 
                               if(alertFlg=="Y"){
                               week=1;
                               }else
                               {
                                  week=0;
                               }
                               
                           }else if(termCd==30)
                           {
                                if(alertFlg=="Y"){
                                   month=1;
                                 }else
                                 {
                                  month=0;
                                 }
                           }
                       }
                       
                        $("#container").html('<div class="row-fluid" id="householdDiv">'+
                                '<div class="row-fluid" id="householdHeader">'+
                                    'Notifications'+
                                '</div>'+
                                '<div class="row-fluid" id="householdBody">'+
                                    'You will be notified about the product warranty expirations. Define the time period you '+
                                    'want to receive alerts ahead.'+
                                    '<form class="form-inline" role="form" id="householdForm" action="#">'+
                                      '<div class="form-group">'+
                                        '<div class=" col-sm-10">'+
                                          '<div class="checkbox">'+
                                            '<label>'+
                                              '<input type="checkbox" class="noteChecked" value="30" id="month"><strong>Month</strong>'+
                                            '</label>'+
                                          '</div>'+
                                        '</div>'+
                                      '</div>'+
                                      '<div class="form-group">'+
                                        '<div class="col-sm-10">'+
                                          '<div class="checkbox">'+
                                            '<label>'+
                                              '<input type="checkbox" class="noteChecked" value="7" id="week"><strong>Week</strong>'+
                                            '</label>'+
                                          '</div>'+
                                        '</div>'+
                                      '</div>'+
                                      '<div class="form-group">'+
                                        '<div class="col-sm-10">'+
                                          '<div class="checkbox">'+
                                            '<label>'+
                                              '<input type="checkbox" class="noteChecked" value="1" id="day"><strong>Day</strong>'+
                                            '</label>'+
                                          '</div>'+
                                        '</div>'+
                                      '</div>'+
                                      '<div class="form-group">'+
                                        '<div class="col-sm-offset-2 col-sm-10">'+
                                          '<button type="button" class="btn btn-default btn-group-lg submitButton" onclick="submitNotification();"><strong>Save</strong></button>'+
                                        '</div>'+
                                      '</div>'+
                                    '</form>'+
                                '</div>'+
                            '</div>');
                    $(".submitButton").click(function(event){
                                          event.stopPropagation();  
                                        });
                    if(day==1)
                    {
                        $("#day").prop("checked",true);
                    }
                    if(week==1)
                    {
                        $("#week").prop("checked",true);
                    }
                    if(month==1)
                    {
                        $("#month").prop("checked",true);
                    }
                    }   
                    else if(check.response=="failure")
                    {
                        $("#errorDiv").html('Unable to fetch your saved data');
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else
                    {
                        $("#errorDiv").html('Service unavilable, please try again');
                    }
                }
                else
                {
                    $("#errorDiv").html('Error: Please check your internet connection');
                }
            }
        };
}
function profile()
{
    var formData="action=getProfileData&client=browser";
    $("#errorDiv").html("Please wait...");
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                        var email=check.email;
                        var password=check.password;
                        var phone=check.phone;
                        $("#container").html('<div class="row">'+
                        '<div id="userProfileDiv">'+
                                '<div id="profileHeader">My Profile</div>'+
                                '<div id="profileBody">'+
                                    '<form role="form">'+
                                          '<div class="form-group">'+
                                            '<label for="profileMobile">Mobile Number</label>'+
                                            '<input type="number" class="form-control" id="profileMobile" placeholder="Enter mobile number (7-12)" onblur="checkBlank(\'profileMobile\');" onclick="hideWarning(\'profileMobile\');" maxlength="12" value="'+phone+'">'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="profilePassword">Password</label>'+
                                            '<input type="password" class="form-control" id="profilePassword" placeholder="Password" onblur="checkBlank(\'profilePassword\');" onclick="hideWarning(\'profilePassword\');" maxlength="50" value="'+password+'">'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="profileEmail">Email Address</label>'+
                                            '<input type="email" class="form-control" id="profileEmail" placeholder="Enter email" onclick="hideWarning(\'profileEmail\');" maxlength="128" value="'+email+'" readonly>'+
                                          '</div>'+
                                        '<button type="button" class="btn btn-default btn-group-lg" id="profileButton" onclick="resetProfile();"><strong>Save</strong></button><button type="button" class="btn btn-default btn-group-lg" onclick="home();" id="cancelButton"><strong>Cancel</strong></button>'+
                                    '</form>'+
                                '</div>'+
                            '</div>'+
                        '</div>');
                                    $("#profileMobile").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });
                                        $(".profileButton").click(function(event){
                                          event.stopPropagation();  
                                        });
                    }
                    else if(check.response=="failure")
                    {
                        $("#errorDiv").html('Unable to fetch your saved data');
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else
                    {
                        $("#errorDiv").html('Service unavailable, please try again');
                    }
                }
                else
                {
                    $("#errorDiv").html('Error: Please check your internet connection');
                }
            }
        };
}
function resetProfile()
{
    var profileMobile=$("#profileMobile").val();
    var profilePassword=$("#profilePassword").val();
    var profileEmail=$("#profileEmail").val();
    var formData="action=updateProfile&client=browser&profileMobile="+escape(profileMobile)+"&profilePassword="+escape(profilePassword)+"&profileEmail="+escape(profileEmail);
    var flag=1;
    var reg=new RegExp("^[0-9]*[a-z]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\..[a-z]{2,4})$");
    if(profileMobile=="" || profileMobile.length<7 || profileMobile.length>12 || !new RegExp("[0-9]{7,12}").test(profileMobile))
    {
        flag=0;
        document.getElementById("profileMobile").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("profileMobile").style.backgroundPosition="right";
        document.getElementById("profileMobile").style.backgroundRepeat="no-repeat";
    }
    else if(profilePassword=="" || profilePassword.length<6 || !new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{4,18}[a-zA-Z0-9._!@#$%^&*()+<>,?~`-]{1}").test(profilePassword))
    {
        flag=0;
        document.getElementById("profileMobile").style.backgroundImage="none";
        document.getElementById("profilePassword").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("profilePassword").style.backgroundPosition="right";
        document.getElementById("profilePassword").style.backgroundRepeat="no-repeat";
    }
    else if(profileEmail=="" || reg.test(profileEmail)!=true)
    {
        flag=0;
        document.getElementById("profilePassword").style.backgroundImage="none";
        document.getElementById("profileEmail").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("profileEmail").style.backgroundPosition="right";
        document.getElementById("profileEmail").style.backgroundRepeat="no-repeat";
    }
    else if(flag==1)
    {
        document.getElementById("profileEmail").style.backgroundImage="none";
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        $("#errorDiv").html('Please wait...');
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                        $("#errorDiv").html('Profile updated successfully');
                    }
                    else if(check.response=="alreadyPresent")
                    {
                        $("#errorDiv").html('Error: Either email or mobile number is already used by someone');
                    }
                    else if(check.response=="failure")
                    {
                        $("#errorDiv").html('Service unavilable, please try again');
                    }
                    else if(check.response=="invalidData")
                    {
                        $("#errorDiv").html('Error: Invalid data is found');
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else
                    {
                        $("#errorDiv").html('Service unavilable, please try again');
                    }
                }
                else
                {
                    $("#errorDiv").html('Error: Please check your internet connection');
                }
            }
        };
    }
}

function submitReturnPlan(id)
{
    var returnDate=$("#returnDate"+id).val();
    var notes=$("#notes"+id).val();
    notes=notes.trim();
    $("#notes"+id).val(notes);
    var planId=$("#planId"+id).val();
    var customerRating=$("#starPlan"+id).val();
    var flag=0;
    if(customerRating=="")
    {
        document.getElementById("ratingDiv"+id).innerHTML="<img src=\"images/alert-icon.png\"/>";
        flag=0;
    }
    else
    {
        flag=1;
        document.getElementById("ratingDiv"+id).innerHTML="";
        if(planId=="")
        {
            flag=0;
        }
        else
        {
            flag=1;
            if(returnDate=="")
            {
                document.getElementById("returnDate"+id).style.backgroundImage="url('images/alert-icon.png')";
                document.getElementById("returnDate"+id).style.backgroundPosition="right";
                document.getElementById("returnDate"+id).style.backgroundRepeat="no-repeat";
                flag=0;
            }
            else
            {
                document.getElementById("returnDate"+id).style.backgroundImage="none";
                flag=1;
                if(notes=="")
                {
                    document.getElementById("notes"+id).style.backgroundImage="url('images/alert-icon.png')";
                    document.getElementById("notes"+id).style.backgroundPosition="right";
                    document.getElementById("notes"+id).style.backgroundRepeat="no-repeat";
                    flag=0;
                }
                else
                {
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        var formData="action=returnPlan&client=browser&planId="+escape(planId)+"&returnDate="+escape(returnDate)+"&notes="+escape(notes)+"&customerRating="+escape(customerRating);
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        $("#msgDiv"+id).html('Please wait...');
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                        $("#msgDiv"+id).html('Plan return detail submitted successfully.');
                        $("#returnDate"+id).val('');
                        $("#notes"+id).val('');
                    }
                    else if(check.response=="failure")
                    {
                        $("#msgDiv"+id).html('Service unavilable, please try again');
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else if(check.response=="alreadyPresent")
                    {
                        $("#msgDiv"+id).html('This product plan has been returned on '+check.returnedDate);
                    }
                    else
                    {
                        $("#msgDiv"+id).html('Service unavilable, please try again');
                    }
                }
                else
                {
                    $("#msgDiv"+id).html('Error: Please check your internet connection');
                }
            }
        };
    }
}
function submitReturnProduct(id)
{
    var returnDate=$("#productReturnDate"+id).val();
    var notes=$("#productNotes"+id).val();
    notes=notes.trim();
    $("#productNotes"+id).val(notes);
    var productId=$("#productId"+id).val();
    var customerRating=$("#starProduct"+id).val();
    var flag=0;
   
    if(customerRating=="")
    {
        document.getElementById("ratingDiv"+id).innerHTML="<img src=\"images/alert-icon.png\"/>";
        flag=0;
    }
    else
    {
        flag=1;
        document.getElementById("ratingDiv"+id).innerHTML="";
        if(productId=="")
        {
            flag=0;
        }
        else
        {
            flag=1;
            if(returnDate=="")
            {
                document.getElementById("productReturnDate"+id).style.backgroundImage="url('images/alert-icon.png')";
                document.getElementById("productReturnDate"+id).style.backgroundPosition="right";
                document.getElementById("productReturnDate"+id).style.backgroundRepeat="no-repeat";
                flag=0;
            }
            else
            {
                document.getElementById("productReturnDate"+id).style.backgroundImage="none";
                flag=1;
                if(notes=="")
                {
                    document.getElementById("productNotes"+id).style.backgroundImage="url('images/alert-icon.png')";
                    document.getElementById("productNotes"+id).style.backgroundPosition="right";
                    document.getElementById("productNotes"+id).style.backgroundRepeat="no-repeat";
                    flag=0;
                }
                else
                {
                    document.getElementById("productNotes"+id).style.backgroundImage="none";
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        var formData="action=returnProduct&client=browser&productId="+escape(productId)+"&returnDate="+escape(returnDate)+"&notes="+escape(notes)+"&customerRating="+escape(customerRating);
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        $("#msgDiv"+id).html('Please wait...');
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                        $("#errorDiv").html('Product return detail submitted successfully.');
                        $("#productReturnDate"+id).val('');
                        $("#productNotes"+id).val('');
                        home();
                    }
                    else if(check.response=="failure")
                    {
                        $("#msgDiv"+id).html('Service unavilable, please try again');
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else if(check.response=="alreadyPresent")
                    {
                        $("#msgDiv"+id).html('You already returned this product');
                    }
                    else
                    {
                        $("#msgDiv"+id).html('Service unavilable, please try again');
                    }
                }
                else
                {
                    $("#msgDiv"+id).html('Error: Please check your internet connection');
                }
            }
        };
    }
}
function submitServiceReview(id)
{
    var serviceDate=$("#serviceDate"+id).val();
    var srviceNotes=$("#serviceNotes"+id).val();
    srviceNotes=srviceNotes.trim();
    $("#serviceNotes"+id).val(srviceNotes);
    var receiptId=$("#receiptId"+id).val();
    var customerRating=$("#customerRating"+id).val();
    var flag=0;
    if(customerRating=="")
    {
        document.getElementById("ratingDiv"+id).innerHTML="<img src=\"images/alert-icon.png\"/>";
        flag=0;
    }
    else
    {
        flag=1;
        document.getElementById("ratingDiv"+id).innerHTML="";
        if(receiptId=="")
        {
            flag=0;
        }
        else
        {
            flag=1;
            if(serviceDate=="")
            {
                document.getElementById("serviceDate"+id).style.backgroundImage="url('images/alert-icon.png')";
                document.getElementById("serviceDate"+id).style.backgroundPosition="right";
                document.getElementById("serviceDate"+id).style.backgroundRepeat="no-repeat";
                flag=0;
            }
            else
            {
                document.getElementById("serviceDate"+id).style.backgroundImage="none";
                flag=1;
                if(srviceNotes=="")
                {
                    document.getElementById("serviceNotes"+id).style.backgroundImage="url('images/alert-icon.png')";
                    document.getElementById("serviceNotes"+id).style.backgroundPosition="right";
                    document.getElementById("serviceNotes"+id).style.backgroundRepeat="no-repeat";
                    flag=0;
                }
                else
                {
                    document.getElementById("serviceNotes"+id).style.backgroundImage="none";
                    flag=1;
                }
            }
        }
    }
    if(flag==1)
    {
        var formData="action=serviceReview&client=browser&receiptId="+escape(receiptId)+"&serviceDate="+escape(serviceDate)+"&notes="+escape(srviceNotes)+"&customerRating="+escape(customerRating);
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        $("#msgDiv"+id).html('Please wait...');
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                        $("#msgDiv"+id).html('Service review detail submitted successfully');
                        $("#serviceDate"+id).val('');
                        $("#serviceNotes"+id).val('');
                    }
                    else if(check.response=="failure")
                    {
                        $("#msgDiv"+id).html('Service unavilable, please try again');
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else
                    {
                        $("#msgDiv"+id).html('Service unavilable, please try again');
                    }
                }
                else
                {
                    $("#msgDiv"+id).html('Error: Please check your internet connection');
                }
            }
        };
    }
}
function submitShowReceipt()
{
    var flag=1;  
    var saleDate=$("#saleDate").val();
    var currency=$("#currency0").val();
    var vendor=$("#vendor").val();
    vendor=vendor.trim();
    var location=$("#location").val();
    location=location.trim();
    var planDurationReg=new RegExp("[0-9]{1}[0-9]{0,7}[.]{0,1}[0-9]{0,2}");
    var venLoc=new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9\s]*[a-zA-Z0-9]{1}");
     var AmtReg=new RegExp(/^(\d{1,6}(\.\d{1,2})?)$/);
     var saleAmt=new RegExp(/^(\d{1,8}(\.\d{1,2})?)$/);
    if(saleDate=="")
    {
        flag=0;
        document.getElementById("saleDate").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("saleDate").style.backgroundPosition="right";
        document.getElementById("saleDate").style.backgroundRepeat="no-repeat";
    }
    else if(currency=="")
    {
        flag=0;
        document.getElementById("saleDate").style.backgroundImage="none";
        document.getElementById("currency").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("currency").style.backgroundPosition="right";
        document.getElementById("currency").style.backgroundRepeat="no-repeat";
    }
    
    else if(vendor=="" || !venLoc.test(vendor))
    {
        flag=0;
        document.getElementById("vendor").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("vendor").style.backgroundPosition="right";
        document.getElementById("vendor").style.backgroundRepeat="no-repeat";
    }
    else if(location=="" || !new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9._!()#,\s-]*[a-zA-Z0-9._!(),-]{1}"))
    {
        flag=0;
        document.getElementById("vendor").style.backgroundImage="none";
        document.getElementById("location").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("location").style.backgroundPosition="right";
        document.getElementById("location").style.backgroundRepeat="no-repeat";
    }
    if(productId.length==0)
    {
        var productDesc=$("#productDesc"+0).val();
        productDesc=productDesc.trim();
        var saleAmount=$("#saleAmount"+0).val();
        var planType=$("#planType"+0).val();
        var planDuration=$("#planDuration"+0).val();
        var planDurationType=$("#planSelect"+0).val();
        var planAmount=$("#planAmount"+0).val();
        if(productDesc=="" || !new RegExp("[a-zA-Z]{1}[a-zA-Z0-9\s]*[a-zA-Z0-9]{1}").test(productDesc)){
           flag=0;
            document.getElementById("currency0").style.backgroundImage="none";
            document.getElementById("productDesc"+0).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("productDesc"+0).style.backgroundPosition="right";
            document.getElementById("productDesc"+0).style.backgroundRepeat="no-repeat";
           
        }
        else if(saleAmount=="" || !saleAmt.test(saleAmount)){
            flag=0;
            document.getElementById("productDesc"+0).style.backgroundImage="none";
            document.getElementById("saleAmount"+0).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("saleAmount"+0).style.backgroundPosition="right";
            document.getElementById("saleAmount"+0).style.backgroundRepeat="no-repeat";
         
        }
        else if(planType=="")
        {
            flag=0;
            document.getElementById("saleAmount"+0).style.backgroundImage="none";
            document.getElementById("planType"+0).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("planType"+0).style.backgroundPosition="right";
            document.getElementById("planType"+0).style.backgroundRepeat="no-repeat";
        }
        else if(planDuration=="" || !planDurationReg.test(planDuration))
        {
            flag=0;
            document.getElementById("saleAmount"+0).style.backgroundImage="none";
            document.getElementById("planDuration"+0).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("planDuration"+0).style.backgroundPosition="right";
            document.getElementById("planDuration"+0).style.backgroundRepeat="no-repeat";
        
        }
        else if(planAmount=="" || !AmtReg.test(planAmount)){
           flag=0;
            document.getElementById("planDuration"+0).style.backgroundImage="none";
            document.getElementById("planAmount"+0).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("planAmount"+0).style.backgroundPosition="right";
            document.getElementById("planAmount"+0).style.backgroundRepeat="no-repeat";
     }
    }else{
        
    
    for(var j=0;j<productId.length;j++)
    {
        var productDesc=$("#productDesc"+j).val();
        productDesc=productDesc.trim();
        var saleAmount=$("#saleAmount"+j).val();
        var planType=$("#planType"+j).val();
        var planDuration=$("#planDuration"+j).val();
        var planDurationType=$("#planSelect"+j).val();
        var planAmount=$("#planAmount"+j).val();
       if(productDesc=="" || !new RegExp("[a-zA-Z]{1}[a-zA-Z0-9\s]*[a-zA-Z0-9]{1}").test(productDesc)){
           flag=0;
            document.getElementById("currency").style.backgroundImage="none";
            document.getElementById("productDesc"+j).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("productDesc"+j).style.backgroundPosition="right";
            document.getElementById("productDesc"+j).style.backgroundRepeat="no-repeat";
            break;
        }
        else if(saleAmount=="" || !saleAmt.test(saleAmount)){
           flag=0;
            document.getElementById("productDesc"+j).style.backgroundImage="none";
            document.getElementById("saleAmount"+j).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("saleAmount"+j).style.backgroundPosition="right";
            document.getElementById("saleAmount"+j).style.backgroundRepeat="no-repeat";
            break;
        }
        else if(planType=="")
        {
            flag=0;
            document.getElementById("saleAmount"+j).style.backgroundImage="none";
            document.getElementById("planType"+j).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("planType"+j).style.backgroundPosition="right";
            document.getElementById("planType"+j).style.backgroundRepeat="no-repeat";
        }
        else if(planDuration=="" || !planDurationReg.test(planDuration)){
            flag=0;
            document.getElementById("saleAmount"+j).style.backgroundImage="none";
            document.getElementById("planDuration"+j).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("planDuration"+j).style.backgroundPosition="right";
            document.getElementById("planDuration"+j).style.backgroundRepeat="no-repeat";
            break;
        }
        else if(planAmount=="" || !AmtReg.test(planAmount)){
           flag=0;
            document.getElementById("planDuration"+j).style.backgroundImage="none";
            document.getElementById("planAmount"+j).style.backgroundImage="url('images/alert-icon.png')";
            document.getElementById("planAmount"+j).style.backgroundPosition="right";
            document.getElementById("planAmount"+j).style.backgroundRepeat="no-repeat";
            break;
        }
    }
}
    if(flag==1)
    {
    var productDescJsonArray="[";
    var saleAmountJsonArray="[";
    var planTypeJsonArray="[";
    var planDurationJsonArray="[";
    var planDurationTypeJsonArray="[";
    var planAmountJsonArray="[";
    for(var i=0;i<productId.length;i++)
    {
         
        var productDesc=$("#productDesc"+i).val();
        productDesc=productDesc.replace(/"/g, '\\"');
        var saleAmount=$("#saleAmount"+i).val();
        var planType=$("#planType"+i).val();
        var planDuration=$("#planDuration"+i).val();
        var planDurationType=$("#planSelect"+i).val();
        var planAmount=$("#planAmount"+i).val();
        if(productId.length==i+1)
        {
             
             productDescJsonArray=productDescJsonArray+"\""+productDesc+"\"";
             saleAmountJsonArray=saleAmountJsonArray+"\""+saleAmount+"\"";
             planTypeJsonArray=planTypeJsonArray+"\""+planType+"\"";
             planDurationJsonArray=planDurationJsonArray+"\""+planDuration+"\"";
             planDurationTypeJsonArray=planDurationTypeJsonArray+"\""+planDurationType+"\"";
             planAmountJsonArray=planAmountJsonArray+"\""+planAmount+"\"";
        }
        else{
           
            productDescJsonArray=productDescJsonArray+"\""+productDesc+"\",";
            saleAmountJsonArray=saleAmountJsonArray+"\""+saleAmount+"\",";
             planTypeJsonArray=planTypeJsonArray+"\""+planType+"\",";
             planDurationJsonArray=planDurationJsonArray+"\""+planDuration+"\",";
             planDurationTypeJsonArray=planDurationTypeJsonArray+"\""+planDurationType+"\",";
             planAmountJsonArray=planAmountJsonArray+"\""+planAmount+"\",";
        }
    }
    if(productId.length==0)
    {
        var productDesc=$("#productDesc"+0).val();
        productDesc=productDesc.replace(/"/g, '\\"');
        var saleAmount=$("#saleAmount"+0).val();
        var planType=$("#planType"+0).val();
        var planDuration=$("#planDuration"+0).val();
        var planDurationType=$("#planSelect"+0).val();
        var planAmount=$("#planAmount"+0).val();
        productDescJsonArray=productDescJsonArray+"\""+productDesc+"\"";
             saleAmountJsonArray=saleAmountJsonArray+"\""+saleAmount+"\"";
             planTypeJsonArray=planTypeJsonArray+"\""+planType+"\"";
             planDurationJsonArray=planDurationJsonArray+"\""+planDuration+"\"";
             planDurationTypeJsonArray=planDurationTypeJsonArray+"\""+planDurationType+"\"";
             planAmountJsonArray=planAmountJsonArray+"\""+planAmount+"\"";
    }
    productDescJsonArray=productDescJsonArray+"]";
    saleAmountJsonArray=saleAmountJsonArray+"]";
     planTypeJsonArray=planTypeJsonArray+"]";
     planDurationJsonArray=planDurationJsonArray+"]";
     planDurationTypeJsonArray=planDurationTypeJsonArray+"]";
     planAmountJsonArray=planAmountJsonArray+"]";
    var formData="action=createReceipt&client=browser&saleDate="+escape(saleDate)
            +"&currency="+escape(currency)+"&productDesc="+escape(productDescJsonArray)
            +"&saleAmount="+escape(saleAmountJsonArray)+"&planType="+
            escape(planTypeJsonArray)+"&planDuration="+escape(planDurationJsonArray)
            +"&durationUnit="+escape(planDurationTypeJsonArray)+"&planAmount="+
            escape(planAmountJsonArray)+"&vendor="+escape(vendor)+"&location="+
            escape(location)+"&account="+escape(account)+"&cardType="+escape(cardType)+
            "&transactionId="+escape(transactionId)+"&vendorCode="+escape(vendorCode)
            +"&receiptData="+receiptData;
     document.getElementById("location").style.backgroundImage="none";
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        $("#errorDiv").html('Please wait...');
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                        $("#errorDiv").html('Receipt added successfully.');
                        $("#saleDate").val('');
                        $("#currency").val('');
                        $("#productDesc").val('');
                        $("#saleAmount").val('');
                        $("#planType").val('');
                        $("#planDuration").val('');
                        $("#planSelect").val('');
                        $("#planAmount").val('');
                        $("#vendor").val('');
                        $("#location").val('');
                        productId.length=0;
                        home();
                    }
                    else if(check.response=="failure")
                    {
                        $("#errorDiv").html('Service unavailable, please try again');
                    }
                    else if(check.response=="invalidData")
                    {
                         $("#errorDiv").html('Invalid data is found');
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else
                    {
                        $("#errorDiv").html('Service unavailable, please try again');
                    }
                }
                else
                {
                    $("#errorDiv").html('Error: Please check your internet connection');
                }
            }
        };
    }
}
function submitHouseHold()
{
    var name=$("#name").val();
    var address=$("#address").val();
    var postalCode=$("#postalCode").val();
    var city=$("#city").val();
    var state=$("#state").val();
    var phone=$("#phone").val();
    var formData="action=household&client=browser&name="+escape(name)+"&address="+escape(address)+"&cityName="+escape(city)+"&postalCode="+escape(postalCode)+"&state="+escape(state)+"&phone="+escape(phone);
    var flag=1;
    if(name=="" || !new RegExp("[a-zA-Z]{1}[a-zA-Z0-9\s]*").test(name))
    {
        flag=0;
        document.getElementById("name").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("name").style.backgroundPosition="right";
        document.getElementById("name").style.backgroundRepeat="no-repeat";
    }
    
    else if(address=="" || !new RegExp("[a-zA-Z0-9]{1}[a-zA-Z0-9\s]*[a-zA-Z0-9.]{1}").test(address)){
        flag=0;
        document.getElementById("name").style.backgroundImage="none";
        document.getElementById("address").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("address").style.backgroundPosition="right";
        document.getElementById("address").style.backgroundRepeat="no-repeat";
        
    }
    else if(postalCode=="" || !new RegExp("^[0-9]{3,9}$").test(postalCode)){
        flag=0;
        document.getElementById("address").style.backgroundImage="none";
        document.getElementById("postalCode").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("postalCode").style.backgroundPosition="right";
        document.getElementById("postalCode").style.backgroundRepeat="no-repeat";
    }
    else if(city=="" || !new RegExp("[a-zA-Z]{1}[a-zA-Z0-9\s]*[a-zA-Z0-9.]{1}").test(city)){
        flag=0;
        document.getElementById("postalCode").style.backgroundImage="none";
        document.getElementById("city").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("city").style.backgroundPosition="right";
        document.getElementById("city").style.backgroundRepeat="no-repeat";
    }
    else if(state=="" || state.length>3 || !new RegExp("[a-zA-Z]{2,3}").test(state)){
        flag=0;
        document.getElementById("city").style.backgroundImage="none";
        document.getElementById("state").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("state").style.backgroundPosition="right";
        document.getElementById("state").style.backgroundRepeat="no-repeat";
    }
    else if(phone=="" || !new RegExp("^[0-9]{7,12}$").test(phone)){
        flag=0;
        document.getElementById("state").style.backgroundImage="none";
        document.getElementById("phone").style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById("phone").style.backgroundPosition="right";
        document.getElementById("phone").style.backgroundRepeat="no-repeat";
    }
    else if(flag==1)
    {
    document.getElementById("phone").style.backgroundImage="none";
    var req=null;
    if(window.XMLHttpRequest)
    {
        req=new XMLHttpRequest();
    }
    else
    {
        req=new ActiveXObject("Microsoft.XMLHttp");
    }
    var url="action.do";
    req.open("POST", url,true);
    $("#errorDiv").html('Please wait...');
    req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    req.setRequestHeader("Content-length", formData.length);
    req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                    $("#errorDiv").html('Household detail submitted successfully');
                }
                else if(check.response=="failure")
                {
                    $("#errorDiv").html('Service unavilable, please try again');
                }
                else if(check.response=="invalidData")
                {
                    $("#errorDiv").html('Error: Invalid data is found');
                }
                else if(check.response=="sessionExpired")
                {
                    location.reload();
                }
                else
                {
                    $("#errorDiv").html('Service unavilable, please try again');
                }
            }
            else
            {
                $("#errorDiv").html('Error: Please check your internet connection');
            }
        }
    };
    }
}
function submitNotification()
{
    var day="";
    if($("#day").prop("checked")==true)
    {
        day=$("#day").val();
    }
    var week="";
    if($("#week").prop("checked")==true)
    {
        week=$("#week").val();
    }
    var month="";
    if($("#month").prop("checked")==true)
    {
        month=$("#month").val();
    }
        var formData="action=notification&client=browser&day="+escape(day)+"&month="+escape(month)+"&week="+escape(week);
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        $("#errorDiv").html('Please wait...');
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                        $("#errorDiv").html('Notification time period updated successfully');
                    }
                    else if(check.response=="failure")
                    {
                        $("#errorDiv").html('Service unavilable, please try again');
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else
                    {
                        $("#errorDiv").html('Service unavilable, please try again');
                    }
                }
            }
        };
}
function checkBlank(id)
{
    var blankValue=document.getElementById(id).value;
    if(blankValue=="")
    {
        document.getElementById(id).style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById(id).style.backgroundPosition="right";
        document.getElementById(id).style.backgroundRepeat="no-repeat";
   
    }
    else
    {
         document.getElementById(id).style.backgroundImage="none";
       
    }
}
function checkAmount(id){
    var amtValue=document.getElementById(id).value;
    var reg=new RegExp("^[0-9](\.){0,11}$");
    if(!reg.test(amtValue))
    {
        document.getElementById(id).style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById(id).style.backgroundPosition="right";
        document.getElementById(id).style.backgroundRepeat="no-repeat";
   
    }
    else
    {
         document.getElementById(id).style.backgroundImage="none";
       
    }
}
function checkPlanDur(id){
    var amtValue=document.getElementById(id).value;
    var reg=new RegExp("^[0-9](\.){0,4}$");
    if(!reg.test(amtValue))
    {
        document.getElementById(id).style.backgroundImage="url('images/alert-icon.png')";
        document.getElementById(id).style.backgroundPosition="right";
        document.getElementById(id).style.backgroundRepeat="no-repeat";
   
    }
    else
    {
         document.getElementById(id).style.backgroundImage="none";
       
    }
}
function fetchContact()
{
    var name=$("#welcomeMsg").html();
    var formData="action=getProfileData&client=browser";
    $("#errorDiv").html("Please wait...");
        var req=null;
        if(window.XMLHttpRequest)
        {
            req=new XMLHttpRequest();
        }
        else
        {
            req=new ActiveXObject("Microsoft.XMLHttp");
        }
        var url="action.do";
        req.open("POST", url,true);
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", formData.length);
        req.setRequestHeader("X-Requested-With", "XMLHttpRequest");
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
                        var email=check.email;
                        var phone=check.phone;
                        $("#container").html('<div class="row">'+
                        '<div id="userProfileDiv">'+
                                '<div id="profileHeader">Contact Us</div>'+
                                '<div id="profileBody">'+
                                    '<form role="form" onsubmit="contactUs(); return false">'+
                                          '<div class="form-group">'+
                                            '<label for="name">Name</label>'+
                                            '<input type="text" class="form-control" id="name" placeholder="Enter your name" onclick="hideWarning(\'name\');" value="'+name+'">'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="email">Email Address</label>'+
                                            '<input type="email" class="form-control" id="email" placeholder="Enter your email" onclick="hideWarning(\'email\');" maxlength="128" value="'+email+'" readonly>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="mobile">Mobile Number</label>'+
                                            '<input type="text" class="form-control" id="mobile" placeholder="Enter your mobile" onclick="hideWarning(\'mobile\');" value="'+phone+'" readonly>'+
                                          '</div>'+
                                          '<div class="form-group">'+
                                            '<label for="subject">Subject</label>'+
                                            '<input type="text" class="form-control" id="subject" placeholder="Subject" onblur="checkBlank(\'subject\');" onclick="hideWarning(\'subject\');">'+
                                          '</div>'+
                                           '<div class="form-group" id="formText">'+
                                                '<label for="message">Message</label>'+
                                                '<textarea value="" class="form-control" id="message" placeholder="Message" onblur="checkBlank(\'message\');" onclick="hideWarning(\'message\');"></textarea>'+
                                            '</div>'+
                                        '<button type="submit" class="btn btn-default btn-group-lg" id="profileButton"><strong>Send Message</strong></button>'+
                                    '</form>'+
                                '</div>'+
                            '</div>'+
                        '</div>');
                                      $("#name").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8,32,9,27,13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                                (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                   return;
                                            }
                                           if ((e.keyCode < 65 || e.keyCode > 90)) {
                                                e.preventDefault();
                                            }
                                        });  
                                 $("#mobile").keydown(function (e) {
                                           if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                                               (e.keyCode == 65 && e.ctrlKey === true) || 
                                               (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                 return;
                                            }
                                          if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });   
                    }
                    else if(check.response=="failure")
                    {
                        $("#errorDiv").html('Unable to fetch your saved data');
                    }
                    else if(check.response=="sessionExpired")
                    {
                        location.reload();
                    }
                    else
                    {
                        $("#errorDiv").html('Service unavilable, please try again');
                    }
                }
                else
                {
                    $("#errorDiv").html('Error: Please check your internet connection');
                }
            }
        };
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
       status=0;
    }
    else if(email=="" || !emailReg.test(email))
    {
       document.getElementById("email").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("email").style.backgroundPosition="right";
       document.getElementById("email").style.backgroundRepeat="no-repeat";
       status=0;
    }
    else if(mobile=="" || !mobileReg.test(mobile) || mobile.length<7 || mobile.length>12)
    {
       document.getElementById("mobile").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("mobile").style.backgroundPosition="right";
       document.getElementById("mobile").style.backgroundRepeat="no-repeat";
       status=0;
    }
    else if(subject=="" || !new RegExp("[a-zA-Z]{1}[a-zA-Z0-9/s]*[a-zA-Z]{1}").test(subject))
    {
       document.getElementById("subject").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("subject").style.backgroundPosition="right";
       document.getElementById("subject").style.backgroundRepeat="no-repeat";
       status=0;
    }
    else if(message=="" || !new RegExp("[a-zA-Z]{1}[a-zA-Z0-9/s]*[a-zA-Z]{1}").test(message))
    {
       document.getElementById("message").style.backgroundImage="url('images/alert-icon.png')";
       document.getElementById("message").style.backgroundPosition="right";
       document.getElementById("message").style.backgroundRepeat="no-repeat";
       status=0;
    }
    if(status==1)
    {
        var formData="name="+name+"&phone="+mobile+"&email="+email+"&subject="+subject+"&message="+message+"&client=browser&action=contact";
        $("#errorDiv").html('Please wait...');
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
                        document.getElementById("subject").value="";
                        document.getElementById("message").value="";
                        $("#errorDiv").html('Your message has been sent successfully.');
                    }
                    else if(check.response=="failure")
                    {
                       $("#errorDiv").html('Service unavailable, please try again.');
                    }
                    else if(check.response=="invalidData"){
                        $("#errorDiv").html('Invalid data found.');
                    }
                    else
                    {
                        $("#errorDiv").html('Service unavailable, please try again.');
                    }
                }
            }
        };
    }
}