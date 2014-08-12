package org.rgn.akka.ch03.futures;

import scala.actors.threadpool.TimeUnit;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.util.Timeout;

public class ProcessOrderActor extends UntypedActor {
	Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));

	ActorRef orderActor = getContext().actorOf(Props.create(OrderActor.class),
			"order");
	ActorRef addressActor = getContext().actorOf(
			Props.create(AddressActor.class), "address");
	ActorRef orderAggregateActor = getContext().actorOf(
			Props.create(OrderAggregateActor.class), "orderAggregate");

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Integer) {

		}
	}

}
