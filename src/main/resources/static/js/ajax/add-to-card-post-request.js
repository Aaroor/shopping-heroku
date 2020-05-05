$(document).ready(function() {
  // SUBMIT FORM
    $("form.addToCardForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    var data = getFormData($(this).serializeArray());
    console.log("add to card");
    ajaxAddToCardPost(data);
  });

    
    $("#paymentForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        addPaymentDetails();
       // var data = getFormData($(this).serialize());
       // addBillingDetails();
        console.log("test ");
      });
    
    $(document).on("submit", 'form.orderRemove', function(event) { 
        console.log("submit event"+$(this).serialize());
        
        event.preventDefault();
        var removeData=jsonToSerialize($(this).serialize());
        removeProduct(removeData);
    });
    
    $('input[type=radio][name=deliveryOptions]').change(function() {
        addDeliveryType(this.value);
    });
    
    $('input[type=checkbox]').change(function() {
    	
    	if(this.id.startsWith("input-visible")){
    		console.log(this.id);
    		var id = this.id.substring(13);
    		console.log(id);
    		var isShow = "false";
    		 if($(this).prop("checked") == true){
    			 isShow = "true";
    		 }else{
    			 isShow = "false";
    		 }
    		 addProductVisibility(id,isShow)
    	}else if(this.id.startsWith("cat-input-visible")){
    		console.log(this.id);
    		var id = this.id.substring(17);
    		console.log("user "+id);
    		var isShow = "false";
    		 if($(this).prop("checked") == true){
    			 isShow = "true";
    		 }else{
    			 isShow = "false";
    		 }
    		 console.log("show "+isShow);
    		 addCategoryVisibility(id,isShow);
    	}else if(this.id.startsWith("user-input-visible")){
    		console.log(this.id);
    		var id = this.id.substring(18);
    		console.log("user "+id);
    		var isShow = "false";
    		 if($(this).prop("checked") == true){
    			 isShow = "true";
    		 }else{
    			 isShow = "false";
    		 }
    		 console.log("show "+isShow);
    		 changeUserActive(id,isShow);
    	}else if(this.id.startsWith("order-input-visible")){
    		console.log(this.id);
    		var id = this.id.substring(19);
    		console.log("order "+id);
    		var isShow = "false";
	   		 if($(this).prop("checked") == true){
	   			 isShow = "true";
	   		 }else{
	   			 isShow = "false";
	   		 }
	   		changeOrderDeliveryStatus(id,isShow);
    	}
    });
    
    function changeOrderDeliveryStatus(orderId,isShow){
    	var formData = {
    			orderId : orderId,
    			isShow : isShow
    	}
    	
    	console.log(JSON.stringify(formData));
    	
    	 $.ajax({
 	        type : "POST",
 	        contentType : "application/json",
 	        url : window.location.origin + "/api/shopping/add-delivery-status",
 	        data : JSON.stringify(formData),
 	        dataType : 'json',
 	        success : function(result) {
 	          if(result.status == "Done"){
 	        	 var visibleId= "#order-input-visible"+orderId;
 	        	 if(isShow == "true"){ 
 	        		$(visibleId).attr("checked", "checked");
 	        	 }else{
 	        		$(visibleId).attr("checked", "");
 	        	 }
 	          }else{
 	        	  alert("Error!")
 	          }
 	          console.log(result);
 	        },
 	        error : function(e) {
 	          alert("Error!")
 	          console.log("ERROR: ", e);
 	        }
 	  });
    	
    }
    
    
    $("button").click(function(){
        
        if(this.id.startsWith("user-btn-remove")){
        	var userId = this.id.substring(15);
        	console.log(this.id);
        	console.log("user id "+userId);
        	removeUser(userId);
        }
     });
    
    function removeUser(userId){
    	var formData = {
    			userId : userId
    	}
    	
    	console.log(JSON.stringify(formData));
    	
    	 $.ajax({
 	        type : "POST",
 	        contentType : "application/json",
 	        url : window.location.origin + "/api/shopping/remove-user",
 	        data : JSON.stringify(formData),
 	        dataType : 'json',
 	        success : function(result) {
 	          if(result.status == "Done"){
 	        	 var rowId= "#user-row"+userId;
 	        	$(rowId).remove();
 	        	
 	          }else{
 	        	  alert("Error!")
 	          }
 	          console.log(result);
 	        },
 	        error : function(e) {
 	          alert("Error!")
 	          console.log("ERROR: ", e);
 	        }
 	  });
    	
    }
    
    
    function changeUserActive(userId,isShow){
    	var formData = {
    			userId : userId,
    			isShow : isShow
    	}
    	
    	console.log(JSON.stringify(formData));
    	
    	 $.ajax({
 	        type : "POST",
 	        contentType : "application/json",
 	        url : window.location.origin + "/api/shopping/add-user-visibility",
 	        data : JSON.stringify(formData),
 	        dataType : 'json',
 	        success : function(result) {
 	          if(result.status == "Done"){
 	        	 var visibleId= "#user-input-visible"+userId;
 	        	 if(isShow == "true"){ 
 	        		$(visibleId).attr("checked", "checked");
 	        	 }else{
 	        		$(visibleId).attr("checked", "");
 	        	 }
 	          }else{
 	        	  alert("Error!")
 	          }
 	          console.log(result);
 	        },
 	        error : function(e) {
 	          alert("Error!")
 	          console.log("ERROR: ", e);
 	        }
 	  });
    	
    }
    

    
    $(document).on("submit", 'form.orderCheckOutRemove', function(event) { 
        event.preventDefault();
        var removeData=jsonToSerialize($(this).serialize());
        console.log(removeData.productId);
       // removeProduct(removeData);
        var newSubTotal = $("#order_sub_total").text().trim().substr(1).trim()-$("#sub_table_total"+removeData.productId).text().trim().substr(1).trim();
        removeProductFromCheckout(removeData,newSubTotal);
        console.log(newSubTotal);
        
    });
    
    function addProductVisibility(productId,isShow){
    	var formData = {
    			productId : productId,
    			isShow : isShow
    	}
    	
    	console.log(JSON.stringify(formData));
    	
    	 $.ajax({
 	        type : "POST",
 	        contentType : "application/json",
 	        url : window.location.origin + "/api/shopping/add-product-visibility",
 	        data : JSON.stringify(formData),
 	        dataType : 'json',
 	        success : function(result) {
 	          if(result.status == "Done"){
 	        	 var visibleId= "#input-visible"+productId;
 	        	 if(isShow == "true"){ 
 	        		$(visibleId).attr("checked", "checked");
 	        	 }else{
 	        		$(visibleId).attr("checked", "");
 	        	 }
 	          }else{
 	        	  alert("Error!")
 	          }
 	          console.log(result);
 	        },
 	        error : function(e) {
 	          alert("Error!")
 	          console.log("ERROR: ", e);
 	        }
 	  });
    	
    }
    
    function addCategoryVisibility(categoryId,isShow){
    	var formData = {
    			categoryId : categoryId,
    			isShow : isShow
    	}
    	
    	console.log(JSON.stringify(formData));
    	
    	 $.ajax({
 	        type : "POST",
 	        contentType : "application/json",
 	        url : window.location.origin + "/api/shopping/add-category-visibility",
 	        data : JSON.stringify(formData),
 	        dataType : 'json',
 	        success : function(result) {
 	          if(result.status == "Done"){
 	        	 var visibleId= "#cat-input-visible"+categoryId;
 	        	 if(isShow == "true"){ 
 	        		$(visibleId).attr("checked", "checked");
 	        	 }else{
 	        		$(visibleId).attr("checked", "");
 	        	 }
 	          }else{
 	        	  alert("Error!")
 	          }
 	          console.log(result);
 	        },
 	        error : function(e) {
 	          alert("Error!")
 	          console.log("ERROR: ", e);
 	        }
 	  });
    	
    }
    
    function addDeliveryType(deliveryType){
    	var formData = {
    			orderId : $("#orderId").val(),
    			deliveryType : deliveryType
    	}
    	console.log(JSON.stringify(formData));
    	 $.ajax({
    	        type : "POST",
    	        contentType : "application/json",
    	        url : window.location.origin + "/api/shopping/add-delivery-type",
    	        data : JSON.stringify(formData),
    	        dataType : 'json',
    	        success : function(result) {
    	          if(result.status == "Done"){
    	          	console.log("add delivery type");
    	          	var subTotal = parseFloat($("#subTotalAmount").text().trim().substr(1).trim());
    	          	console.log("subtotoal : "+subTotal);
    	          	var deliveryAmount = 0;
                    if(result.data.deliveryType == "delivery"){
                    	deliveryAmount = 10.0;
                    }else{
                    	deliveryAmount = 0.0;
                    }
                    var total = subTotal + deliveryAmount;
                    console.log("total"+total);
                    $("#deliveryChargeAmount").html("&euro; "+deliveryAmount.toFixed(2));
    	            $("#totalOrderAmount").html("&euro; "+total.toFixed(2));
    	          }else{
    	        	  alert("Error!")
    	          }
    	          console.log(result);
    	        },
    	        error : function(e) {
    	          alert("Error!")
    	          console.log("ERROR: ", e);
    	        }
    	  });
    	//console.log(JSON.stringify(formData));
    }

    function addPaymentDetails(){
    	var formData = {
    			orderId : $("#orderId").val()
    	}
    	console.log(JSON.stringify(formData));
    	 $.ajax({
    	        type : "POST",
    	        contentType : "application/json",
    	        url : window.location.origin + "/api/shopping/add-cart-payment",
    	        data : JSON.stringify(formData),
    	        dataType : 'json',
    	        success : function(result) {
    	          if(result.status == "Done"){
    	          	console.log("add payment details done");
    	          	window.location.replace(window.location.origin+"/view/order-confirmed/"+result.data.id);
    	          	//ajaxGet(result.data.orderId);
    	           // $("#addToCardList").html(htmlVal);
    	          }else{
    	        	  alert("Error!")
    	          }
    	          console.log(result);
    	        },
    	        error : function(e) {
    	          alert("Error!")
    	          console.log("ERROR: ", e);
    	        }
    	  });
    	//console.log(JSON.stringify(formData));
    }

    function removeProduct(removeData){
        console.log(JSON.stringify(removeData));
        // DO POST
        $.ajax({
        type : "POST",
        contentType : "application/json",
        url : window.location.origin + "/api/shopping/remove-add-to-card",
        data : JSON.stringify(removeData),
        dataType : 'json',
        success : function(result) {
          if(result.status == "Done"){
          	console.log("remove product done");
          	ajaxGet(result.data.orderId);
           // $("#addToCardList").html(htmlVal);
          }else{
        	  alert("Error!")
          }
          console.log(result);
        },
        error : function(e) {
          alert("Error!")
          console.log("ERROR: ", e);
        }
      });
   
      }
    
    function removeProductFromCheckout(removeData,newSubTotal){
        
        // DO POST
        $.ajax({
        type : "POST",
        contentType : "application/json",
        url : window.location.origin + "/api/shopping/remove-add-to-card",
        data : JSON.stringify(removeData),
        dataType : 'json',
        success : function(result) {
          if(result.status == "Done"){
        	$("#rowProduct"+result.data.productId).remove();
        	$("#order_sub_total").html("<span>&euro; "+newSubTotal.toFixed(2)+"</span>");
            $("#order_total").html("<span>&euro; "+newSubTotal.toFixed(2)+"</span>");
            ajaxCheckoutListGet(result.data.orderId);
           // $("#addToCardList").html(htmlVal);
          }else{
        	  alert("Error!")
          }
          console.log(result);
        },
        error : function(e) {
          alert("Error!")
          console.log("ERROR: ", e);
        }
      });
   
      }
    
    
    function ajaxAddToCardPost(formData){
      console.log(JSON.stringify(formData));
      // DO POST
      $.ajax({
      type : "POST",
      contentType : "application/json",
      url : window.location.origin + "/api/shopping/add-to-card",
      data : JSON.stringify(formData),
      dataType : 'json',
      success : function(result) {
        if(result.status == "Done"){
        	console.log(result.data.id);
        	ajaxGet(result.data.id);
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
    
    
    function getFormData(val){
        var indexed_array = {};

        $.map(val, function(n, i){
            indexed_array[n['name']] = n['value'];
        });

        return indexed_array;
    }
    
    function ajaxGet(id){
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
						+"<td class='si-close'>"
							+"<form class='orderRemove'>"
								+"<input type='hidden' name='productId' value='"+products.product.id+"'/>"
								+"<input type='hidden' name='orderId' value='"+result.data.id+"'/>"
								+"<button type='submit'>"
									+"<i class='ti-close'></i>"
								+"</button>"
							+"</form>"
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
      				+"<a href='/view/shopping-cart/"+id+"' class='primary-btn view-card'>VIEW CARD</a>"
      				+"<a href='/view/check-out/"+id+"' class='primary-btn checkout-btn'>CHECK OUT</a>"
      			+"</div>"
      		+"</div>"
      	+"</li>"
      	+"<li class='cart-price'>&euro; "+total.toFixed(2)+"</li>"
      +"</ul>");
              
            var htmlVal2 = "<ul class='nav-right'>"
							+"<li class='cart-price'>&euro; "+total.toFixed(2)+"</li>"
							+"<li><a href='/view/shopping-cart/"+id+"' class='btn btn-info'><i class='fa fa-shopping-cart' aria-hidden='true'></i> Shopping Cart</a></li>"
							+"</ul>";  
              
            $("#addToCardList").html(htmlVal2);

            }else{
              console.log("Fail: ", result);
            }
          },
          error : function(e) {
            console.log("ERROR: ", e);
          }
        });  
      }
    
    function ajaxCheckoutListGet(id){
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
      				+"<a href='/view/check-out/"+result.data.id+"' class='primary-btn checkout-btn'>CHECK OUT</a>"
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
    
});