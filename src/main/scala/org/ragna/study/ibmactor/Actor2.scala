package org.ragna.study.ibmactor

import scala.actors._, Actor._

object Actor2 {
  case class Speak(line: String)
  case class Gesture(bodyPart: String, action: String)
  case class NegotiateNewContract

  def main(args: Array[String]): Unit = {
    val badActor =
      actor {
    	//just receives ONE message
        receive {
          case NegotiateNewContract =>
            println("I Won't do it for less than $1 million!")
          case Speak(line) =>
            println(line)
          case Gesture(bodyPart, action) =>
            println("(" + action + "s " + bodyPart + ")")
          case _ =>
            println("Huh? I'll be in my trailer.")

        }
      }//actor

    
    //badActor ! NegotiateNewContract
    badActor ! Speak("Do ya feel lucky, punk?")
    badActor ! Gesture("face", "grimaces")
    badActor ! Speak("well, do ya?")
    badActor ! "Damn You!"
  }

}