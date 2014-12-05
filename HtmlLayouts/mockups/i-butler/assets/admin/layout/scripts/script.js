$(document).ready(function(){

	$("input[name=group1]").change(function() {
		var test = $(this).val();
		$(".desc").hide();
		$("#"+test).show();
	}); 

	$('.add-info-btn').click(function(){
		if( $(this).is(':checked')) {
	        $(".additional-info").show();
	    } else {
	        $(".additional-info").hide();
	    }
	});

});
