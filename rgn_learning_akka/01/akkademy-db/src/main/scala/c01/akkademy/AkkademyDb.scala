package c01.akkademy

import akka.actor.Actor
import akka.event.Logging
import c01.akkademy.messages.SetRequest

import scala.collection.mutable

/**
  * Created by ragnarokkrr on 5/30/16.
  */
class AkkademyDb extends Actor{
  val map = new mutable.HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    }
    case o => log.info("received unknown message: {}", o);
  }
}
