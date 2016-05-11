package com.Spreadsheet;

public class Data {

	String word;
	String meaning;

	Data(String word, String meaning) {
		setWord(word);
		setMeaning(meaning);
	}

	void setWord(String word) {
		this.word = word;
	}

	void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	String getWord() {
		return this.word;
	}

	String getMeaning() {
		return this.meaning;
	}

}
