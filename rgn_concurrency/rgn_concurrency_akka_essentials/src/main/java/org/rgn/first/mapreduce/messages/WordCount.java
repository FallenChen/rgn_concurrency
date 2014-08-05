package org.rgn.first.mapreduce.messages;

public final class WordCount {
	private final String word;
	private final Integer count;

	public WordCount(String word, Integer count) {
		super();
		this.word = word;
		this.count = count;
	}

	public String getWord() {
		return word;
	}

	public Integer getCount() {
		return count;
	}

}
