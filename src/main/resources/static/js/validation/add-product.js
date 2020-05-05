$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='productForm']").validate({
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			productName : "required",
			quantity : "required",
			unitPrice : "required",
			productImgFile : "required",
			productImgFile2 : "required"
		},
		// Specify validation error messages
		messages : {
			productName : "Please enter product name",
			quantity : "Please enter quantity",
			unitPrice : "Please enter product price",
			productImgFile : "Please load image to upload product",
			productImgFile2 : "Please load image to upload product"
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			addProductDetails();
		}
	});
	
	function addProductDetails() {
	    // Get form
	    var form = $('#productForm')[0];
	    var dataForm = new FormData(form);
	    var isShow = $("input[type='radio']:checked").val();
	    dataForm.append('prodShow', isShow);
	   // console.log(dataForm);
	 
		$.ajax({
			type:"POST",
			url:window.location.origin + "/api/shopping/add-product",
			data:dataForm,
			cache:false,
			dataType:"json",
			contentType:false,
			processData:false,
			success:function(result) {
				if(result.status == "Done") {
					 $("#product-messgae").html("<div class='alert alert-success' role='alert'>Successfully added the product !</div>");
					console.log("--success-- "+result.data);
				} else {
					console.log("--fail-- "+result);
					$("#product-messgae").html("<div class='alert alert-danger' role='alert'>Can not add this product !</div>");
				}
			}
		});
	}
});