package org.rgn.first.mapreduce.actors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rgn.first.mapreduce.messages.MapData;
import org.rgn.first.mapreduce.messages.ReduceData;
import org.rgn.first.mapreduce.messages.WordCount;

import akka.actor.UntypedActor;

public class ReduceActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof MapData) {
			MapData mapData = (MapData) message;
			getSender().tell(reduce(mapData.getDataList()), getSelf());

		} else {
			unhandled(message);
		}
	}

	private ReduceData reduce(List<WordCount> dataList) {
		Map<String, Integer> reduceMap = new HashMap<>();

		for (WordCount wordCount : dataList) {
			if (reduceMap.containsKey(wordCount.getWord())) {
				Integer value = reduceMap.get(wordCount.getWord());
				value++;
				reduceMap.put(wordCount.getWord(), value);

			} else {
				reduceMap.put(wordCount.getWord(), 1);
			}
		}

		return new ReduceData(reduceMap);
	}

}
