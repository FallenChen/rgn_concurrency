package org.ragna.study.ibmactor

import scala.actors._, Actor._

object ProdConSample1 {

  //case class Message(msg: String)

  def main(args: Array[String]): Unit = {
    val consumer =
      actor {
        var done = false
        while (!done) {
          receive {
            case msg =>
              println("Received Message! -> " + msg)
              done = (msg == "DONE")
          }
        }
      }

    consumer ! "Mares eat oats"
    consumer ! "Dows eat oats"
    consumer ! "Little lambs eat ivy"
    consumer ! "Kids eat ivy too"
    consumer ! "DONE"
  }

}