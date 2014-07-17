package org.rgn.first.mapreduce;

import java.util.concurrent.TimeUnit;

import org.rgn.first.mapreduce.actors.MasterActor;
import org.rgn.first.mapreduce.messages.Result;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;

public class MapReduceApplication {

	public static void main(String[] args) throws Exception {
		Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));
		ActorSystem _system = ActorSystem.create("MapReduceApp");

		ActorRef master = _system.actorOf(Props.create(MasterActor.class));
		master.tell(
				"The quick brownfox tried to jumo over the lazy dog and fell on the dog",
				null);

		master.tell("lazy dog and fell on the dog", null);
		master.tell("Dog is man's best friend", null);
		master.tell("Dog and Fox belong to the same family", null);

		Thread.sleep(5000);

		Future<Object> future = Patterns.ask(master, new Result(), timeout);

		String result = (String) Await.result(future, timeout.duration());

		System.out.println(result);
		_system.shutdown();
	}

}
