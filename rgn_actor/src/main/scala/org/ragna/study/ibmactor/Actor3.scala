package org.ragna.study.ibmactor

import scala.actors._, Actor._

object Actor3 {
  case class Speak(line: String)
  case class Gesture(bodyPart: String, action: String)
  case class NegotiateNewContract
  case class ThatsAWrap

  def main(args: Array[String]): Unit = {
    //method that shows threadname
    def ct = 
      "Thread " + Thread.currentThread.getName() + ": "

    val badActor =
      actor {
        //infinite loop to receive all messages until ThatsAWrap
        var done = false
        while (!done) {
          receive {
            case NegotiateNewContract =>
              println(ct + "I Won't do it for less than $1 million!")
            case Speak(line) =>
              println(ct + line)
            case Gesture(bodyPart, action) =>
              println(ct + "(" + action + "s " + bodyPart + ")")
            case ThatsAWrap =>
              println(ct + "Great cast party, everybody! See ya!")
              done = true
            //case _ =>
            case anything =>
              println(ct + "Huh? I'll be in my trailer. => " + anything)

          }
        }
      } //actor

    println(ct +  "Negotaiting...")
    badActor ! NegotiateNewContract
    println(ct + "Speaking...")
    badActor ! Speak("Do ya feel lucky, punk?")
    println(ct + "Gesturing...")
    badActor ! Gesture("face", "grimaces")
    println(ct + "Speaking again...");
    badActor ! Speak("well, do ya?")
    println(ct + "Dummying...")
    badActor ! "Damn You!"
    println(ct + "Wrapping up")
    badActor ! ThatsAWrap
  }

}