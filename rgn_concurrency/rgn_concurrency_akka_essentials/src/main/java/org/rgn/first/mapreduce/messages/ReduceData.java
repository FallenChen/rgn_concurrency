package org.rgn.first.mapreduce.messages;

import java.util.Map;

public class ReduceData {
	private final Map<String, Integer> reduceList;

	public ReduceData(Map<String, Integer> reduceList) {
		super();
		this.reduceList = reduceList;
	}

	public Map<String, Integer> getReduceList() {
		return reduceList;
	}

}
