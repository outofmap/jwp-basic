$(document).ready(function() {/* jQuery toggle layout */
	$('#btnToggle').click(function() {
		if ($(this).hasClass('on')) {
			$('#main .col-md-6').addClass('col-md-4').removeClass('col-md-6');
			$(this).removeClass('on');
		} else {
			$('#main .col-md-4').addClass('col-md-6').removeClass('col-md-4');
			$(this).addClass('on');
		}
	});

	$(".submit-write button").click(addAnswer);

	var addAnswer = function(e){
		e.preventDefault();
		var queryString = $("form[name=answer]").serialize();
		$.ajax({
			type:'post',
			url:'/api/qna/addAnswer',
			data: queryString,
			dataType:'json',
			error: onError,
			success: onSuccess
		})
	}

	function onSuccess(json,status){
		console.log(json);
	}
});
