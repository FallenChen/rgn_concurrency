package org.ragna.disruptor.vecclocks;

import com.lmax.disruptor.EventFactory;

public class Event {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void add(String suffix) {

		value = value + suffix;
	}

	@Override
	public String toString() {
		return "Event [value=" + value + "]";
	}

	public final static EventFactory<Event> FACTORY = new EventFactory<Event>() {

		@Override
		public Event newInstance() {
			return new Event();
		}
	};

}
