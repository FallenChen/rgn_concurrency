package org.ragna.study.ibmactor

import scala.actors._, Actor._

object Actor1 {

  def main(args: Array[String]): Unit = {

    
    val badActor =
      //Actor.actor
      actor {
    	// receive method
    	receive {
    	  case msg => println(msg)
    	}
      
      }

    //! is a method that dumps message in MailBox
    badActor ! "Do ya feel lycky, punk!?"
    
    
  }

}