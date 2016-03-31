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
$(".qna-comment").on("click",".link-delete-article",deleteAnswer);


function addAnswer(e) {
	e.preventDefault();
	//console.log("in");
	var queryString = $("form[name=answer]").serialize();
	console.log(queryString);
	$.ajax({
		type: 'post',
		url: '/api/qna/addAnswer',
		data: queryString,
		dataType: 'json'
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

 function deleteAnswer(e) {
 	e.preventDefault();
 	var button = $(this);
 	var answer = $(this).closest("form").serialize();
 	
 	console.log(url);
 	$.ajax({
 		type:'post',
 		url: '/api/qna/deleteAnswer',
 		data: answer,
 		dataType: 'json'
 	}).done(function(json,status) {
 		console.log(json);
 		//json.status가 뭔가요?
 		if(json.status){
 			button.closest("article").remove();
 		}
 	}).fail(function () {
		onError(); 		
 	})
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
//api/qna/list get요청으로 json으로 list받기
function showQnaList(e){

}
