String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	console.log("in");
	var queryString = $("form[name=answer]").serialize();
	console.log(queryString);
	$.ajax({
		type: 'post',
		url: '/api/qna/addAnswer',
		data: queryString,
		dataType: 'json',
		//error: onError,
		//success: onSuccess
	}).done(function (data) {
		console.log(data);
		onSuccess(data,status);
	}).fail(function() {
		onError();
		//alert("error");
	});
}

function onSuccess (json,status) {
	console.log("성공");
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(json.writer,new Date(json.createdDate), 
		json.contents, json.answerId, json.answerId);
	$(".qna-comment-slipp-articles").prepend(template);
}

function onError (xhr,status) {
	alert("error");
}

