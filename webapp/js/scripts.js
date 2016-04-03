// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();

	var queryString = $("form[name=answer]").serialize();

	$.ajax({
		type : 'post',
		url : '/api/qna/addAnswer',
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onSuccess,
	});
}

function onSuccess(json, status) {
	var answer = json.answer;
	var question = json.question;
	var countOfComment = question.countOfComment;
	console.log(countOfComment);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(answer.writer, new Date(
			answer.createdDate), answer.contents, answer.answerId,
			answer.answerId);
	$(".qna-comment-slipp-articles").prepend(template);
}

function onError(xhr, status) {
	alert("error");
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};

$(".qna-comment").on("click", ".form-delete", deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault();
	console.log("in");
	var delButton = $(e.target);
	var queryString = delButton.closest("form").serialize();

	$.ajax({
		type : 'post',
		url : "/api/qna/deleteAnswer",
		data : queryString, 
		dataType : 'json'
	}).done(function(json, status) {
		console.log(json.answerId);
		var answerId = json.answerId;
		//selector로 delButton가져오기 실패.
		if (json.status) {
			delButton.closest("article").remove();
		}
	}).fail(function(xhr, status) {
		onError(xhr, status);
	});
};
