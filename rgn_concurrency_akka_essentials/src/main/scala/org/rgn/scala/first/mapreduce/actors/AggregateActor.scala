package org.rgn.scala.first.mapreduce.actors

import akka.actor.Actor
import scala.collection.mutable.HashMap
import org.rgn.scala.first.mapreduce.ReduceData
import org.rgn.scala.first.mapreduce.Result

class AggregateActor extends Actor {

  val finalReduceMap = new HashMap[String, Int]

  def receive: Receive = {
    case ReduceData(reduceDataMap) =>
      aggregateInMemoryReduce(reduceDataMap)
    case Result =>
      sender ! finalReduceMap.toString
  }

  def aggregateInMemoryReduce(reduceList: Map[String, Int]): Unit = {
    for ((key, value) <- reduceList) {
      if (finalReduceMap contains key)
        finalReduceMap(key) = (value + finalReduceMap.get(key).get)
      else
        finalReduceMap += (key -> value)
    }
  }
}