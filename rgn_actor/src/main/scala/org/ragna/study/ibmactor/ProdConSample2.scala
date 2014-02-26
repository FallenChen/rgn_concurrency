package org.ragna.study.ibmactor

import scala.actors._, Actor._

object ProdConSample2 {

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
              reply("RECEIVED")
          }
        }
      }

    //!? -> blocking send -> waits for reply()
    println("Sending...")
    consumer !? "Mares eat oats"
    println("Sending...")
    consumer !? "Dows eat oats"
    println("Sending...")
    consumer !? "Little lambs eat ivy"
    println("Sending...")
    consumer !? "Kids eat ivy too"
    println("Sending...")
    consumer !? "DONE"
  }

}