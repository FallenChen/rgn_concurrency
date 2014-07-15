package org.ragna.study.ibmactor

import scala.actors._, Actor._
import scala.concurrent.{ future, promise }
import scala.concurrent.ExecutionContext.Implicits.global


object ProdConSampleUsingSpawn {

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

    //Spawn producer
    //spawn {
    val r = future {
      val importantInfo: Array[String] = Array(
        "Mares eat oats", "Does eat oats", "Little lambs eat ivy", "A kid will eat ivy too", "DONE")

      importantInfo.foreach(msg => consumer !? msg)

    }

  }

}