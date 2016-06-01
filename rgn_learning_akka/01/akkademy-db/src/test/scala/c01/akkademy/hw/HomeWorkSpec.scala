package c01.akkademy.hw

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.{BeforeAndAfterEach, Matchers, FunSpecLike}
import c01.akkademy.hw.messages.SetString

/**
  * Created by ragnarokkrr on 5/30/16.
  */
class HomeWorkSpec extends FunSpecLike with Matchers with BeforeAndAfterEach{
  implicit val system = ActorSystem()

  describe("homeWork") {
    describe("given SetString") {
      it ("should receive one message correctly") {
        val actorRef = TestActorRef(new HomeWork)

        actorRef ! SetString("One")

        val homeWork = actorRef.underlyingActor

        homeWork.stringStack.top should equal ("One")
      }

      it("should receive two messages correctly") {
        val actorRef = TestActorRef(new HomeWork)

        actorRef ! SetString("One")
        actorRef ! SetString("Two")


        val homeWork = actorRef.underlyingActor

        homeWork.stringStack.top should equal ("Two")

      }
    }
  }

}
