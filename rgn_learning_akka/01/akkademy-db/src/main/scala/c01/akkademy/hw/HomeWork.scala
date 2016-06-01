package c01.akkademy.hw

import akka.actor.Actor
import akka.event.Logging
import c01.akkademy.hw.messages.SetString

/**
  * Created by ragnarokkrr on 5/30/16.
  */
class HomeWork extends Actor{

  val stringStack = new scala.collection.mutable.Stack[String]
  val log = Logging(context.system, this)

  override def receive = {
    case SetString(value) => {
      log.info("received SetString value: {}", value)
      stringStack.push(value)
    }
    case o => log.info("received unknown object: {}", o );
  }
}
