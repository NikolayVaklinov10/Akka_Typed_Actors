package shhopingCard

import akka.actor.typed.{ActorSystem, Behavior}
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
  val shoppingRootActorMutable = ActorSystem(
    Behaviors.setup[ShoppingCardMessage]{ ctx =>
      // local state = mutable
      var items: Set[String] = Set()

      Behaviors.receiveMessage[ShoppingCardMessage]{ message: ShoppingCardMessage =>
        message match {
          case AddItem(item) =>
            println(s"Adding $item to card")
            // mutating a variable
            items += item
          case RemoveItem(item) =>
            println(s"Removing $item from card")
            items -= item
          case ValidateCard =>
            println("The card is good.")
        }
        Behaviors.same
      }
    },
    "ShoppingActorMutable"
  )

  def shoppingBehavior(items: Set[String]): Behavior[ShoppingCardMessage] =
    Behaviors.receiveMessage[ShoppingCardMessage] {
      case AddItem(item) =>
        println(s"Adding $item to card")
        // mutating a variable
        shoppingBehavior(items + item)
      case RemoveItem(item) =>
        println(s"Removing $item from card")
        shoppingBehavior(items - item)
      case ValidateCard =>
        println("The card is good.")
        Behaviors.same
    }

  // 3 - hierarchy



}
