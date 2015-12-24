package org.ragna.study.ibmactor

import scala.actors._, Actor._

object CountingSample {
  case class Incr()
  case class Value(sender: Actor)
  case class Lock(sender: Actor)
  case class Unlock(value: Int)

  class Counter extends Actor {
    override def act(): Unit = loop(0)
    def loop(value: Int): Unit = {
      receive {
        case Incr() =>
          //each case issues a new call to loop who stands for the next message
          loop(value + 1)
        case Value(a) =>
          a ! value; loop(value)
        case Lock(a) =>
          a ! value
          receive { case Unlock(v) => loop(v) }
        case "DONE" => println ("Done!!!")
        case _ => loop(value)
        
      }
    }

  }

  def main(args: Array[String]): Unit = {
    val counter = new Counter
    counter.start()
    counter ! Incr()
    counter ! Incr()
    counter ! Incr()
    counter ! Value(self)
    receive { case cvalue => println(cvalue) }
    counter ! Incr()
    counter ! Incr()
    counter ! Value(self)
    receive { case cvalue => println(cvalue) }
    counter ! "DONE"
  }

}