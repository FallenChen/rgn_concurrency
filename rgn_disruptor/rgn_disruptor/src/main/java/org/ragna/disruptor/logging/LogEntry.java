package org.ragna.disruptor.logging;

import com.lmax.disruptor.EventFactory;

public class LogEntry {

	public static final EventFactory<LogEntry> FACTORY = new EventFactory<LogEntry>() {

		@Override
		public LogEntry newInstance() {
			return new LogEntry();
		}

	};

	long time;
	int level;
	String text;

	@Override
	public String toString() {
		return "LogEntry [time=" + time + ", level=" + level + ", text=" + text
				+ "]";
	}

}
