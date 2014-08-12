package org.rgn.akka.ch02.mapreduce.messages;

import java.util.List;

public final class MapData {
	private final List<WordCount> dataList;

	public MapData(List<WordCount> dataList) {
		super();
		this.dataList = dataList;
	}

	public List<WordCount> getDataList() {
		return dataList;
	}

}
