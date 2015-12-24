package org.rgn.akka.ch02.scala.mapreduce.actors

import scala.collection.mutable.ArrayBuffer

import org.rgn.akka.ch02.scala.mapreduce.MapData
import org.rgn.akka.ch02.scala.mapreduce.WordCount

import akka.actor.Actor
import akka.actor.actorRef2Scala

class MapActor extends Actor {
  val STOP_WORDS_LIST = List("a", "am", "an", "and", "are", "as",
    "at", "be", "do", "go", "if", "in", "is", "it", "of", "on", "the",
    "to")

  def receive: Receive = {
    case message: String =>
      sender ! evaluateExpression(message)
  }

  def evaluateExpression(line: String): MapData = MapData {
    line.split("""\s+""").foldLeft(ArrayBuffer.empty[WordCount]) {
      (index, word) => if(!STOP_WORDS_LIST.contains(word.toLowerCase)) {
        index += WordCount(word.toLowerCase, 1)
      }else
        index
    }
  }
}