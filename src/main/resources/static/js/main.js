/*  ---------------------------------------------------
    Template Name: Fashi
    Description: Fashi eCommerce HTML Template
    Author: Colorlib
    Author URI: https://colorlib.com/
    Version: 1.0
    Created: Colorlib
---------------------------------------------------------  */

'use strict';

(function ($) {

    /*------------------
        Preloader
    --------------------*/
    $(window).on('load', function () {
        $(".loader").fadeOut();
        $("#preloder").delay(200).fadeOut("slow");
    });

    /*------------------
        Background Set
    --------------------*/
    $('.set-bg').each(function () {
        var bg = $(this).data('setbg');
        $(this).css('background-image', 'url(' + bg + ')');
    });

    /*------------------
		Navigation
	--------------------*/
    $(".mobile-menu").slicknav({
        prependTo: '#mobile-menu-wrap',
        allowParentLinks: true
    });

    /*------------------
        Hero Slider
    --------------------*/
    $(".hero-items").owlCarousel({
        loop: true,
        margin: 0,
        nav: true,
        items: 1,
        dots: false,
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        navText: ['<i class="ti-angle-left"></i>', '<i class="ti-angle-right"></i>'],
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
    });

    /*------------------
        Product Slider
    --------------------*/
   $(".product-slider").owlCarousel({
        loop: true,
        margin: 25,
        nav: true,
        items: 4,
        dots: true,
        navText: ['<i class="ti-angle-left"></i>', '<i class="ti-angle-right"></i>'],
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
        responsive: {
            0: {
                items: 1,
            },
            576: {
                items: 2,
            },
            992: {
                items: 2,
            },
            1200: {
                items: 3,
            }
        }
    });

    /*------------------
       logo Carousel
    --------------------*/
    $(".logo-carousel").owlCarousel({
        loop: false,
        margin: 30,
        nav: false,
        items: 5,
        dots: false,
        navText: ['<i class="ti-angle-left"></i>', '<i class="ti-angle-right"></i>'],
        smartSpeed: 1200,
        autoHeight: false,
        mouseDrag: false,
        autoplay: true,
        responsive: {
            0: {
                items: 3,
            },
            768: {
                items: 5,
            }
        }
    });

    /*-----------------------
       Product Single Slider
    -------------------------*/
    $(".ps-slider").owlCarousel({
        loop: false,
        margin: 10,
        nav: true,
        items: 3,
        dots: false,
        navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
    });
    
    /*------------------
        CountDown
    --------------------*/
    // For demo preview
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();

    if(mm == 12) {
        mm = '01';
        yyyy = yyyy + 1;
    } else {
        mm = parseInt(mm) + 1;
        mm = String(mm).padStart(2, '0');
    }
    var timerdate = mm + '/' + dd + '/' + yyyy;
    // For demo preview end

    console.log(timerdate);
    

    // Use this for real timer date
    /* var timerdate = "2020/01/01"; */

	$("#countdown").countdown(timerdate, function(event) {
        $(this).html(event.strftime("<div class='cd-item'><span>%D</span> <p>Days</p> </div>" + "<div class='cd-item'><span>%H</span> <p>Hrs</p> </div>" + "<div class='cd-item'><span>%M</span> <p>Mins</p> </div>" + "<div class='cd-item'><span>%S</span> <p>Secs</p> </div>"));
    });

        
    /*----------------------------------------------------
     Language Flag js 
    ----------------------------------------------------*/
    $(document).ready(function(e) {
    //no use
    try {
        var pages = $("#pages").msDropdown({on:{change:function(data, ui) {
            var val = data.value;
            if(val!="")
                window.location = val;
        }}}).data("dd");

        var pagename = document.location.pathname.toString();
        pagename = pagename.split("/");
        pages.setIndexByValue(pagename[pagename.length-1]);
        $("#ver").html(msBeautify.version.msDropdown);
    } catch(e) {
        // console.log(e);
    }
    $("#ver").html(msBeautify.version.msDropdown);

    //convert
    $(".language_drop").msDropdown({roundedBorder:false});
        $("#tech").data("dd");
    });
    /*-------------------
		Range Slider
	--------------------- */
	var rangeSlider = $(".price-range"),
		minamount = $("#minamount"),
		maxamount = $("#maxamount"),
		minPrice = rangeSlider.data('min'),
		maxPrice = rangeSlider.data('max');
	    rangeSlider.slider({
		range: true,
		min: minPrice,
        max: maxPrice,
		values: [minPrice, maxPrice],
		slide: function (event, ui) {
			minamount.val('$' + ui.values[0]);
			maxamount.val('$' + ui.values[1]);
		}
	});
	minamount.val('$' + rangeSlider.slider("values", 0));
    maxamount.val('$' + rangeSlider.slider("values", 1));

    /*-------------------
		Radio Btn
	--------------------- */
    $(".fw-size-choose .sc-item label, .pd-size-choose .sc-item label").on('click', function () {
        $(".fw-size-choose .sc-item label, .pd-size-choose .sc-item label").removeClass('active');
        $(this).addClass('active');
    });
    
    /*-------------------
		Nice Select
    --------------------- */
    $('.sorting, .p-show').niceSelect();

    /*------------------
		Single Product
	--------------------*/
	$('.product-thumbs-track .pt').on('click', function(){
		$('.product-thumbs-track .pt').removeClass('active');
		$(this).addClass('active');
		var imgurl = $(this).data('imgbigurl');
		var bigImg = $('.product-big-img').attr('src');
		if(imgurl != bigImg) {
			$('.product-big-img').attr({src: imgurl});
			$('.zoomImg').attr({src: imgurl});
		}
	});

    $('.product-pic-zoom').zoom();
    
    /*-------------------
		Quantity change
	--------------------- */
    var proQty = $('.pro-qty');
	proQty.prepend('<span class="dec qtybtn">-</span>');
	proQty.append('<span class="inc qtybtn">+</span>');
	proQty.on('click', '.qtybtn', function () {
		var $button = $(this);
		var oldValue = $button.parent().find('input').val();
		var dataOption = $button.parent().find('input').data('options');
		var unitPrice = $("#unite_price"+dataOption.productId).text().trim().substr(1).trim();
		var initialSubTotal = $("#order_sub_total").text().trim().substr(1).trim()-$("#sub_table_total"+dataOption.productId).text().trim().substr(1).trim();
		//var oldsubTotal = $("#sub_table_total"+updateData.productId).text().trim().substr(1).trim();
		
		
		if ($button.hasClass('inc')) {
			var newVal = parseFloat(oldValue) + 1;
			console.log("increment");
		} else {
			// Don't allow decrementing below zero
			if (oldValue > 1) {
				var newVal = parseFloat(oldValue) - 1;
			} else {
				newVal = 1;
			}
			
			console.log("decrement");
		}
		$button.parent().find('input').val(newVal);
		
		console.log("new sum value : "+unitPrice*newVal);
		ajaxUpdateProductQuantity(dataOption,newVal,unitPrice,initialSubTotal);
		
	});
	
	function ajaxUpdateProductQuantity(updateData,quantity,unitPrice,initialSubTotal){
        console.log(updateData);
        var formData = {
                productId : updateData.productId,
                orderId :  updateData.orderId,
                quantity :  quantity
        }
        // DO POST
        $.ajax({
        type : "POST",
        contentType : "application/json",
        url : window.location.origin + "/api/shopping/update-quantity",
        data : JSON.stringify(formData),
        dataType : 'json',
        success : function(result) {
          if(result.status == "Done"){
          	console.log(result);
          	//<span>&euro; [[${#numbers.formatDecimal(orders.getTotalOrderPrice(), 0,'COMMA', 2, 'POINT')}]]</span>
          	
          	$("#sub_table_total"+updateData.productId).html("&euro; "+(unitPrice*quantity).toFixed(2));
          	$("#order_sub_total").html("<span>&euro; "+(initialSubTotal+(unitPrice*quantity)).toFixed(2)+"</span>");
          	$("#order_total").html("<span>&euro; "+(initialSubTotal+(unitPrice*quantity)).toFixed(2)+"</span>");
          	//$("#order_total").html("&euro; "+(unitPrice*quantity).toFixed(2));
          	ajaxAddToCardListGet(result.data.id);
          	//ajaxGet(result.data.id);
           // $("#addToCardList").html(htmlVal);
          }else{
          	console.log(result);
          }
          console.log(result);
        },
        error : function(e) {
          alert("Error!")
          console.log("ERROR: ", e);
        }
        });
      }
	
	function ajaxAddToCardListGet(id){
        $.ajax({
          type : "GET",
          url : window.location.origin + "/api/shopping/order/"+id,
          success: function(result){
            if(result.status == "Done"){
              console.log("Success: ", result.data.orderProducts.length);
              var total =0;
              var htmlVal ="<ul class='nav-right'>"
            		+"<li class='heart-icon'>"
            			+"<a href='#'><i class='icon_heart_alt'></i><span>0</span></a>"
            		+"</li>"
            		+"<li class='cart-icon'><a href='#'> <i class='icon_bag_alt'></i> <span>"+result.data.orderProducts.length+"</span></a>"
            			+"<div class='cart-hover'>"
            				+"<div class='select-items'>"
            					+"<table>"
            						+"<tbody>";
              $.each(result.data.orderProducts, function(i, products){
            	  total = total + (products.product.price*products.quantity);
					htmlVal = htmlVal.concat("<tr>"
						+"<td class='si-pic'><img src='"+products.product.addToCardUrl+"' alt=''></td>"
						+"<td class='si-text'>"
							+"<div class='product-selected'>"
								+"<p>&euro; "+products.product.price+" x "+products.quantity+"</p>"
								+"<h6>"+products.product.name+"</h6>"
							+"</div>"
						+"</td>"
					+"</tr>");
				});
              htmlVal = htmlVal.concat("</tbody>"
      				+"</table>"
      			+"</div>"
      			+"<div class='select-total'>"
      				+"<span>total:</span>"
      				+"<h5>&euro; "+total.toFixed(2)+"</h5>"
      			+"</div>"
      			+"<div class='select-button'>"
      				+"<a href='/check-out/"+result.data.id+"' class='primary-btn checkout-btn'>CHECK OUT</a>"
      			+"</div>"
      		+"</div>"
      	+"</li>"
      	+"<li class='cart-price'>&euro; "+total.toFixed(2)+"</li>"
      +"</ul>");
              
            $("#addToCardList").html(htmlVal);

            }else{
              console.log("Fail: ", result);
            }
          },
          error : function(e) {
            console.log("ERROR: ", e);
          }
        });  
      }

})(jQuery);