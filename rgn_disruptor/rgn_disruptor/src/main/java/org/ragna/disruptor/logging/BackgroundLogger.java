package org.ragna.disruptor.logging;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class BackgroundLogger {

	private static final int ENTRIES = 64;

	private final ExecutorService executorService;
	private final Disruptor<LogEntry> disruptor;

	private final RingBuffer<LogEntry> ringBuffer;

	public BackgroundLogger() {
		executorService = Executors.newFixedThreadPool(4);
		disruptor = new Disruptor<LogEntry>(LogEntry.FACTORY, ENTRIES,
				executorService);
		disruptor.handleEventsWith(new LogEntryHandler());
		disruptor.start();
		ringBuffer = disruptor.getRingBuffer();
	}

	public void log(String text) {
		final long sequence = ringBuffer.next();
		final LogEntry logEntry = ringBuffer.get(sequence);

		logEntry.time = System.currentTimeMillis();
		logEntry.level = new Random().nextInt(5);
		String name = Thread.currentThread().getName();
		logEntry.text = name + ": " + text;

		ringBuffer.publish(sequence);
	}

	public void stop() {
		disruptor.shutdown();
		executorService.shutdown();
	}

}
