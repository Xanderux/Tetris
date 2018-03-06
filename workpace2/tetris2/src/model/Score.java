package model;

public class Score {
	String author;
	int value;

	public Score(String author, int value) {
		super();
		this.author = author;
		this.value = value;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}