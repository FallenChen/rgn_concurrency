package org.ragna.study.ibmactor

import scala.actors._, Actor._

object Actor2Loop {
  case class Speak(line: String)
  case class Gesture(bodyPart: String, action: String)
  case class NegotiateNewContract
  case class ThatsAWrap

  def main(args: Array[String]): Unit = {
    val badActor =
      actor {
        //infinite loop to receive all messages until ThatsAWrap
        var done = false
        while (!done) {
          receive {
            case NegotiateNewContract =>
              println("I Won't do it for less than $1 million!")
            case Speak(line) =>
              println(line)
            case Gesture(bodyPart, action) =>
              println("(" + action + "s " + bodyPart + ")")
            case ThatsAWrap =>
              println("Great cast party, everybody! See ya!")
              done = true
            //case _ =>
            case anything =>
              println("Huh? I'll be in my trailer. => " + anything)

          }
        }
      } //actor

    badActor ! NegotiateNewContract
    badActor ! Speak("Do ya feel lucky, punk?")
    badActor ! Gesture("face", "grimaces")
    badActor ! Speak("well, do ya?")
    badActor ! "Damn You!"
    badActor ! ThatsAWrap
  }

}