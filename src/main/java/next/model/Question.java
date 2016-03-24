package next.model;

public class Question {
	private String questionId;
	private String writer;
	private String title;
	private String contents;
	private String createdDate;
	private Integer countOfAnswer;
	
	public Question(String writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public Question(String questionId, String writer, String title, String createdDate,Integer countOfAnswer) {
		super();
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.createdDate = createdDate;
		this.countOfAnswer = countOfAnswer;
	}

	public Question(String questionId, String writer, String title, String contents, String createdDate,
			Integer countOfAnswer) {
		super();
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = createdDate;
		this.countOfAnswer = countOfAnswer;
	}

	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Integer getCountOfAnswer() {
		return countOfAnswer;
	}
	public void setCountOfAnswer(Integer countOfAnswer) {
		this.countOfAnswer = countOfAnswer;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
}
