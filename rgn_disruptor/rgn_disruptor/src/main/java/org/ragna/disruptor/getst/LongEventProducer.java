package org.ragna.disruptor.getst;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {
	private RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(ByteBuffer bb) {
		long sequence = ringBuffer.next(); // grab the next sequence

		try {
			LongEvent event = ringBuffer.get(sequence); // get the entry in the
														// Disruptor for the
														// sequence

			event.set(bb.getLong(0)); // fill with data
		} finally {
			ringBuffer.publish(sequence);
		}

	}
}
