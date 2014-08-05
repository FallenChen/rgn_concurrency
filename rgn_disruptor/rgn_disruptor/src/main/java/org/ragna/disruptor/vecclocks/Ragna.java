package org.ragna.disruptor.vecclocks;

import java.util.Date;

public class Ragna {

	public static void log(Event event) {
		Date date = new Date();
		System.out.printf("%1$tF %1$tT: %2$s\n", date, event);

	}

	public static void log(Throwable e) {
		Date date = new Date();
		System.out.printf("%1$tF %1$tT: %2$s\n", date, e.getMessage());

	}
}
