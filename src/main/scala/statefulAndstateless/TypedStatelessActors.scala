package statefulAndstateless

import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors

object TypedStatelessActors {

  // the trait plus the possible message an actor can accept
  trait SimpleThing
  case object EatChocolate extends SimpleThing
  case object WashDishes extends SimpleThing
  case object LearnAkka extends SimpleThing

  val emotionalMutableActor: Behavior[SimpleThing] = Behaviors.setup{ context =>
    // spin up the actor state
    var happiness = 0


    // behavior of the actor
    Behaviors.receiveMessage{
      case EatChocolate =>
        context.log.info(s"($happiness) Eating chocolate, getting a shot of dopamine")
        happiness += 1
        Behaviors.same
      case WashDishes =>
        context.log.info(s"($happiness) Doing a chore, womp, womp...")
        happiness -= 2
        Behaviors.same
      case LearnAkka =>
        context.log.info(s"($happiness) Learning Akka, this is cool!")
        happiness += 100
        Behaviors.same
      case _ =>
        context.log.info(s"($happiness) Received something I don't know")
        Behaviors.same

    }
  }



  def main(args: Array[String]): Unit = {
    val emotionalActorSystem = ActorSystem(emotionalMutableActor, "EmotionalSystem")

    emotionalActorSystem ! EatChocolate
    emotionalActorSystem ! EatChocolate
    emotionalActorSystem ! EatChocolate
    emotionalActorSystem ! WashDishes
    emotionalActorSystem ! LearnAkka

    Thread.sleep(1000)
    emotionalActorSystem.terminate()

  }

}
