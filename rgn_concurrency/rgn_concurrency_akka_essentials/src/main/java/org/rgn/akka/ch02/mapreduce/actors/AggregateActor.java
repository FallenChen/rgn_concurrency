package org.rgn.akka.ch02.mapreduce.actors;

import java.util.HashMap;
import java.util.Map;

import org.rgn.akka.ch02.mapreduce.messages.ReduceData;
import org.rgn.akka.ch02.mapreduce.messages.Result;

import akka.actor.UntypedActor;

public class AggregateActor extends UntypedActor {
	private Map<String, Integer> finalReduceMap = new HashMap<>();

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof ReduceData) {
			ReduceData reduceData = (ReduceData) message;
			aggregateInMemoryReduce(reduceData.getReduceList());
		} else if (message instanceof Result) {
			getSender().tell(finalReduceMap.toString(), getSelf());
		} else {
			unhandled(message);
		}
	}

	private void aggregateInMemoryReduce(Map<String, Integer> reduceList) {
		Integer count = null;
		for (String key : reduceList.keySet()) {
			if (finalReduceMap.containsKey(key)) {
				count = reduceList.get(key) + finalReduceMap.get(key);
				finalReduceMap.put(key, count);
			} else {
				finalReduceMap.put(key, reduceList.get(key));
			}
		}
	}

}
