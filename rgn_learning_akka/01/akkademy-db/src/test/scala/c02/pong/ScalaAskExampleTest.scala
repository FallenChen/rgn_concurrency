package c02.pong

import akka.actor.{Props, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import org.scalatest.{Matchers, FunSpecLike}
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
/**
  * Created by ragnarokkrr on 5/31/16.
  */
class ScalaAskExampleTest extends FunSpecLike with Matchers{
  val system = ActorSystem()

  implicit val timeout = Timeout(5 seconds)

  val pongActor = system.actorOf(Props(classOf[ScalaPongActor]))

  def askPong(message: String): Future[String] =
    (pongActor ? message).mapTo[String]


  describe("Pong Actor") {
    it ("should respond with Pong") {
      val future = pongActor ? "Ping"
      val result = Await.result(future.mapTo[String], 1 second)

      assert(result == "Pong")
    }


    it("should fail on unknown message") {

      val future = pongActor ? "unknown"
      intercept[Exception] {
        Await.result(future.mapTo[String], 1 second)
      }
    }
  }


  describe("FutureExamples") {
    import scala.concurrent.ExecutionContext.Implicits.global
    it ("should print to console") {
      (pongActor ? "Ping").onSuccess({
        case x: String => println("replied with " + x)
      })


      //transforming the result
      val x = askPong("Ping").map(x => x.charAt(0))

      println ("x " + Some(x.value))

      //transforming  the result asynchronously
      val futureFuture: Future[Future[String]] =
        askPong("Ping").map(x => {askPong(x)})

      val f : Future[String] = askPong("Ping").flatMap(x => {
        println ("x ping: " + x)
        ( askPong("Ping"))
      })


      //Executing code in the failure case
      askPong("causeErro").onFailure({
        case e: Exception => println("Got Exception")
      })

      //recovering from failure
      val ffail = askPong("causeError").recover({
        case t: Exception => "default"
      })


      //recovering from failure asynchronously
      askPong("causeError").recoverWith({
        case t : Exception => askPong("Ping")
      })

      //composing futures

      //chainng operations together

      val fff = askPong("Ping").
        flatMap(x => askPong("Ping" + x)).
        recover({ case e: Exception => "There was an error"})


      println("fff: " + fff )


      //conbining futures
      val f1 = Future{4}
      val f2 = Future{5}
      val futureAddition: Future[Int] =
        for (
          res1 <- f1;
          res2 <- f2
        ) yield res1 + res2


      //lists of futures
      val listOfFutures : List[Future[String]] = List("Pong", "Pong", "failed").
        map(x => askPong(x))

      val futureOfList: Future[List[String]] = Future.sequence(listOfFutures)


      Future.sequence(listOfFutures.map(future => future.recover ({
        case e: Exception => ""
      })))



      println("listOfFutures: " + listOfFutures)

      Thread.sleep(2 * 1000)

    }

  }

}
