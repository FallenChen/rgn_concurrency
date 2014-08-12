package org.rgn.akka.ch03.futures;

import org.rgn.akka.ch03.futures.messages.Order;

import akka.actor.UntypedActor;

public class OrderActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {

		if (message instanceof Integer) {
			Integer userId = (Integer) message;

			// get the listof orders for given id....
			Order order = new Order(Integer.valueOf(123),
					Double.valueOf(345.0), Integer.valueOf(5));

			getSender().tell(order, getSender());
		}

	}
}
