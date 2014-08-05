package org.ragna.disruptor.logging;

public class LogMain {

	public static void main(String[] args) {
		BackgroundLogger backgroundLogger = new BackgroundLogger();

		for (int i = 0; i < 1500; i++) {
			String name = Thread.currentThread().getName();
			String msg = String.format("mesg '%d'", i);
			System.out.println(name + ": " + msg);
			backgroundLogger.log(msg);
		}

		backgroundLogger.stop();
	}

}
