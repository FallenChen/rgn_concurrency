package org.ragna.study.ibmactor

import scala.actors._, Actor._

object ActorDropSample {

  class Drop {
    private case class Put(x: String)
    private case object Take
    private case object Stop

    private val buffer =
      actor {
        var data = ""
        loop {
          react {
            case Put(x) if data == "" =>
              data = x; reply()
            case Take if data != "" =>
              val r = data; data = ""; reply(r)
            case Stop =>
              reply(); exit("stopped")
          }
        }

      }

    def put(x: String) { buffer !? Put(x) }
    def take(): String = (buffer !? Take).asInstanceOf[String]
    def stop() { buffer !? Stop }
  }

  def main(args: Array[String]): Unit = {
    import concurrent.ops._

    val drop = new Drop()
    //Spawn Producer
    spawn {
      val importantInfo: Array[String] = Array("Mares eat oats", "Does eat oats", "Little lambs eat ivy", "A kid will eat ivy too")
      importantInfo.foreach(msg => drop.put(msg))
      drop.put("DONE")
    }

    //Spawn Consumer
    spawn{
      var message = drop.take()
      while(message != "DONE"){
        printf("MESSAGE RECEIVED: %s\n", message)
        message = drop.take()
      }
      
      drop.stop();
    }
    
  }

}