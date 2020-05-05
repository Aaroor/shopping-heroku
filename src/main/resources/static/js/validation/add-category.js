$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='categoryForm']").validate({
		// Specify validation rules
		rules : {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			categoryName : "required"
		},
		// Specify validation error messages
		messages : {
			categoryName : "Please enter product category"
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler : function(form) {
			addCategoryDetails();
		}
	});
	
	function addCategoryDetails() {
	    // Get form
	    var form = $('#categoryForm')[0];
	    var dataForm = new FormData(form);
	 
		$.ajax({
			type:"POST",
			url:window.location.origin + "/api/shopping/add-category",
			data:dataForm,
			cache:false,
			dataType:"json",
			contentType:false,
			processData:false,
			success:function(result) {
				if(result.status == "Done") {
					window.location.replace(window.location.origin+"/admin/category-list");
					console.log("--success-- "+result.data);
				} else {
					console.log("--fail-- "+result);
					alert("Add category failure ");
				}
			}
		});
	}
});