package shhopingCard

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

object AkkaTypedIncentives {

  // 1 -typed messages & actors
  sealed trait ShoppingCardMessage
  case class AddItem(item: String) extends ShoppingCardMessage
  case class RemoveItem(item: String) extends ShoppingCardMessage
  case object ValidateCard extends ShoppingCardMessage

  val shoppingRootActor = ActorSystem(
    Behaviors.receiveMessage[ShoppingCardMessage]{ message: ShoppingCardMessage =>
      message match {
        case AddItem(item) => println(s"Adding $item to card")
        case RemoveItem(item) => println(s"Removing $item from card")
        case ValidateCard => println("The card is good.")
      }
      Behaviors.same
    },
    "ShoppingActor"
  )
  shoppingRootActor ! ValidateCard



  // 2 - mutable state

  // 3 - hierarchy




}
