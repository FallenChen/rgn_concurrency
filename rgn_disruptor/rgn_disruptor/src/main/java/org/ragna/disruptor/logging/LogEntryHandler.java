package org.ragna.disruptor.logging;

import java.util.Date;

import com.lmax.disruptor.EventHandler;

public class LogEntryHandler implements EventHandler<LogEntry> {

	@Override
	public void onEvent(LogEntry event, long sequence, boolean endOfBatch)
			throws Exception {
		Date date = new Date(event.time);
		System.out.printf("%1$tF %1$tT: %2$s\n", date, event);
	}

}
