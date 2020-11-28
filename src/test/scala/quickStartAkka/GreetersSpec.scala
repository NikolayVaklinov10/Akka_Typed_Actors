package quickStartAkka

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import org.scalatest.wordspec.AnyWordSpecLike
import quickStartAkka.Greeter.{Greet, Greeted}

class GreetersSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {

 "A Greeter" must {
   "reply to greeted" in {
     val replyProbe = createTestProbe[Greeted]()
     val underTest = spawn(Greeter())
     underTest ! Greet("Santa", replyProbe.ref)
     replyProbe.expectMessage(Greeted("Santa", underTest.ref))
   }
 }
}
