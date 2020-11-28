package quickStartAkka

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}


// #greeter-actor
object Greeter {
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: String, from: ActorRef[Greet])

  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    context.log.info("Hello {}!", message.whom)
    //#greeter-send-messages
    message.replyTo ! Greeted(message.whom, context.self)
    //#greeter-send-messages
    Behaviors.same
  }
}

//#greeter-bot
object GreeterBot {

  def apply(max: Int): Behavior[Greeter.Greeted] = {
    bot(0, max)
  }

  private def bot(greetingCounter: Int, max: Int): Behavior[Greeter.Greeted] =
    Behaviors.receive { (context, message) =>
      val n = greetingCounter + 1
      context.log.info("Greeting {} for {}", n, message.whom)
      if (n == max) {
        Behaviors.stopped
      } else {
        message.from ! Greeter.Greet(message.whom, context.self)
        bot(n, max)
      }
    }
}

//#greeter-main
object GreeterMain {

  final case class SyHello(name: String)

  def apply(): Behavior[SyHello] =
    Behaviors.setup { context =>
      //#create-actors
      val greeter = context.spawn(Greeter(), "greeter")

      Behaviors.receiveMessage { message =>
        //#create-actors
        val replyTo = context.spawn(GreeterBot(max=3), message.name)
        greeter ! Greeter.Greet(message.name, replyTo)
        Behaviors.same
      }
    }
}





object Greeters extends App {




}
