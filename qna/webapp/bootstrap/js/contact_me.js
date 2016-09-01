$(function() {

    $("#contactForm input,#contactForm textarea").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
            // additional error messages or events
        },
        submitSuccess: function($form, event) {
            // Prevent spam click and default submit behaviour
            $("#btnSubmit").attr("disabled", true);
            event.preventDefault();
            
            // get values from FORM
            var userId = $("input#id").val();
            var password = $("input#password").val();
            var name = $("input#name").val();
            var email = $("input#email").val();
            var phone = $("input#phone").val();
            var message = $("textarea#message").val();
            var firstName = name; // For Success/Failure Message
            // Check for white space in name for Success/Fail message
            if (firstName.indexOf(' ') >= 0) {
                firstName = name.split(' ').slice(0, -1).join(' ');
            }
            var form_val = $('#contactForm').serialize();
            form_val = decodeURIComponent((form_val + "").replace(/\+/g, "%20"));
            
            $.ajax({
                url: "/bootstrap/create.do",
                type: "GET",
                dataType: 'jsonp',
                jsonpCallback: "callback",
                data:form_val,
                cache: false,
                success: function(data) {
                	if(data.key == "03"){
	                    // Enable button & show success message
	                    $("#btnSubmit").attr("disabled", false);
	                    $('#success').html("<div class='alert alert-success'>");
	                    $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
	                        .append("</button>");
	                    $('#success > .alert-success')
	                        .append("<strong>방명록이 정상적으로 작성 되었습니다! </strong>");
	                    $('#success > .alert-success')
	                        .append('</div>');
	                    
	                    $('#bookDiv')
	                    	.append(
	                    			'<div class="col-md-12 wrap_content">'+
	                    			'<div class="page-header">'+
	                    			'<div class="panel panel-info">'+
	                    			'<div class="panel-heading">'+
	                    			'<h4 class="panel-title"><small> 글쓴이 : '+ name+' /이메일 : '+email+' / 휴대폰 : '+phone+'</small></h4>'+
	                    			'</div>'+
	                    			'<div class="panel-body">'+
	                    			message+
	                    			'<div class="clearfix">'+
	                    			'<div class="pull-right">'+
	                    			'<span class="btn-group">'+
	                    			'<a href="#//" class="btn btn-danger" name="btnDel" id="'+ name +'">삭제</a>'+
	                    			'</div>'+
	                    			'</div>'+
	                    			'</div>'+
	                    			'</div>'+
	                    			'</div>'+
	                    			'</div>'
	                    	);
	                   
	
	                    //clear all fields
	                    //$('#contactForm').trigger("reset");
                	}else{
                		// Fail message
                        $('#success').html("<div class='alert alert-danger'>");
                        $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                            .append("</button>");
                        $('#success > .alert-danger').append("<strong>죄송합니다 " + firstName + "님, 이미 등록된 이름입니다. 다른 이름으로 등록해주세요!");
                        $('#success > .alert-danger').append('</div>');
                        //clear all fields
                        $('#contactForm').trigger("reset");
                	}
                },
                error: function() {
                    // Fail message
                    $('#success').html("<div class='alert alert-danger'>");
                    $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-danger').append("<strong>Sorry " + firstName + ", This is Server Error!! Please try again later!");
                    $('#success > .alert-danger').append('</div>');
                    //clear all fields
                    $('#contactForm').trigger("reset");
                },
            });
        },
        filter: function() {
            return $(this).is(":visible");
        },
    });

    $("a[data-toggle=\"tab\"]").click(function(e) {
        e.preventDefault();
        $(this).tab("show");
    });
});

// When clicking on Full hide fail/success boxes
$('#name').focus(function() {
    $('#success').html('');
});
