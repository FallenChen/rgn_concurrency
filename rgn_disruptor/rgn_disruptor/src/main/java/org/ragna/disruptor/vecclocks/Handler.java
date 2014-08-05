package org.ragna.disruptor.vecclocks;

import com.lmax.disruptor.EventHandler;

public class Handler implements EventHandler<Event> {
	private String name;

	public Handler(String name) {
		this.name = name;
	}

	@Override
	public void onEvent(Event event, long sequence, boolean endOfBatch)
			throws Exception {
		event.add(name + "-" + sequence + "|");
		Ragna.log(event);
	}
}
