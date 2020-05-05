$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='billingForm']").validate({
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			firstName : "required",
			lastName : "required",
			country : "required",
			addressLine1 : "required",
			postalZipCode : "required",
			townOrCity : "required",
			phone : "required",
			emailAddress : {
				required : true,
				// Specify that email should be validated
				// by the built-in "email" rule
				email : true
			}
		},
		// Specify validation error messages
		messages : {
			firstName : "Please enter your firstname",
			lastName : "Please enter your lastname",
			country : "Please enter your country",
			addressLine1 : "Please enter your address",
			postalZipCode : "Please enter postal code or zip code",
			townOrCity : "Please enter your city or town",
			emailAddress : "Please enter a valid email address",
			phone : "Please enter a valid phone number"
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			// form.submit();
			addBillingDetails();
		}
	});

	function addBillingDetails() {
		var formData = {
			orderId : $("#orderId").val(),
			firstName : $("#firstName").val(),
			lastName : $("#lastName").val(),
			country : $("#country").val(),
			addressLine1 : $("#addressLine1").val(),
			addressLine2 : $("#addressLine2").val(),
			postalZipCode : $("#postalZipCode").val(),
			townOrCity : $("#townOrCity").val(),
			emailAddress : $("#emailAddress").val(),
			phoneNumber : $("#phone").val()
		}
		console.log(JSON.stringify(formData));
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location.origin + "/api/shopping/add-billing-details",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if (result.status == "Done") {
					console.log("add billing details done");
					window.location.replace(window.location.origin
							+ "/view/cart-payment/" + result.data.orderId);
				} else {
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
});