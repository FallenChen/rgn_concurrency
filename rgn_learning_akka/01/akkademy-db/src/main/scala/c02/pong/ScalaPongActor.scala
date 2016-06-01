package c02.pong

import akka.actor.{Status, Actor}

/**
  * Created by ragnarokkrr on 5/31/16.
  */
class ScalaPongActor extends Actor {
  override def receive: Receive = {
    case "Ping" => sender() ! "Pong"
    case _ => Status.Failure(new Exception("unknown message"))
  }
}
