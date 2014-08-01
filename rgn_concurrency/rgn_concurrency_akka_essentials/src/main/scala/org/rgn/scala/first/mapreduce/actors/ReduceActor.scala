package org.rgn.scala.first.mapreduce.actors

import akka.actor.Actor
import org.rgn.scala.first.mapreduce.MapData
import org.rgn.scala.first.mapreduce.WordCount
import org.rgn.scala.first.mapreduce.ReduceData

class ReduceActor extends Actor {

  def receive: Receive = {
    case MapData(dataList) =>
      sender ! reduce(dataList)
  }
  def reduce(words: IndexedSeq[WordCount]): ReduceData = ReduceData {
    words.foldLeft(Map.empty[String, Int]) {
      (index, words) =>
        if (index contains words.word)
          index + (words.word -> (index.get(words.word).get + 1))
        else
          index + (words.word -> 1)
    }
  }

}