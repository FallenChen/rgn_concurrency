package org.rgn.akka.ch03.futures;

import org.rgn.akka.ch03.futures.messages.OrderHistory;

import akka.actor.UntypedActor;

public class OrderAggregateActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof OrderHistory) {
			OrderHistory orderHistory = (OrderHistory) message;

			System.out.println("=====>" + orderHistory);
		}
	}

}
