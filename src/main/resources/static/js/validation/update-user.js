$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='updateuserForm']").validate({
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			userName : "required",
			email : "required",
			password : "required",
			name : "required",
			lastName : "required"
		},
		// Specify validation error messages
		messages : {
			userName : "Please enter user name",
			email : "Please enter email",
			password : "Please enter password",
			lastName : "Please enter last name",
			name : "please enter first name"
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			console.log("success");
			updateUserDetails();
		}
	});
	
	function updateUserDetails() {
	    // Get form
	    var form = $('#updateuserForm')[0];
	    var dataForm = new FormData(form);
	    console.log(JSON.stringify(dataForm));
	 
		$.ajax({
			type:"POST",
			url:window.location.origin + "/api/shopping/edit-user",
			data:dataForm,
			cache:false,
			dataType:"json",
			contentType:false,
			processData:false,
			success:function(result) {
				if(result.status == "Done") {
					window.location.replace(window.location.origin+"/admin/user-list");
					console.log("--success-- "+result.data);
				} else {
					console.log("--fail-- "+result);
					alert("update failure");
				}
			}
		});
	}
});