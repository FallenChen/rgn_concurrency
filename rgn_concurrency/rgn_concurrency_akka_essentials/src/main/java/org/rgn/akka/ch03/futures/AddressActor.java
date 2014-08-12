package org.rgn.akka.ch03.futures;

import org.rgn.akka.ch03.futures.messages.Address;

import akka.actor.UntypedActor;

public class AddressActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Integer) {
			Integer userId = (Integer) message;
			Address address = new Address(userId, "Munish Gupta",
					"Sarjapura Road", "Bangalore, India");
			getSender().tell(address, getSender());
		}
	}

}
