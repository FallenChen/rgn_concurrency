package org.ragna.study.ibmactor

import concurrent.MailBox
import concurrent.ops._

object ProdConSample {
  class Drop {
    private val m = new MailBox()
    private case class Empty()
    private case class Full(x: String)

    m send Empty() //initialization

    def put(msg: String): Unit = {
      m receive {
        case Empty() =>
          m send Full(msg)
      }
    }

    def take(): String = {
      m receive {
        case Full(msg) =>
          m send Empty(); msg
      }
    }

  }

  def main(args: Array[String]): Unit = {
    val drop = new Drop()

    //Spawn producer
    spawn {
      val importantInfo: Array[String] = Array(
        "Mares eat oats", "Does eat oats", "Little lambs eat ivy", "A kid will eat ivy too")

      importantInfo.foreach(msg => drop.put(msg))
      drop.put("DONE")
    }

    //Spawn Consumer
    spawn {
      var message = drop.take()
      while (message != "DONE") {
    	  System.out.format("MESSAGE RECEIVED: %s\n", message)
    	  message = drop.take
      }

    }

  }

}