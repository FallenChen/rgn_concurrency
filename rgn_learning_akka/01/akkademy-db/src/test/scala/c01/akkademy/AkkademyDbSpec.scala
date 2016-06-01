package c01.akkademy

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import c01.akkademy.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, Matchers, FunSpecLike}

/**
  * Created by ragnarokkrr on 5/30/16.
  */
class AkkademyDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach{
  implicit val system = ActorSystem()


  describe("akkademyDb") {

    describe ("given SetRequest"){

      it ("should place key/value into map"){

        val actorRef = TestActorRef(new AkkademyDb)

        actorRef ! SetRequest("keyTst", "valueTst")

        val akkademyDb = actorRef.underlyingActor

        akkademyDb.map.get("keyTst") should equal (Some("valueTst"))
      }
    }
  }
}
