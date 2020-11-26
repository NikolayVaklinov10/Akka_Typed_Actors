package shhopingCard

import akka.actor.typed.ActorSystem

object AkkaTypedIncentives {

  // 1 -typed messages & actors
  trait ShoppingCardMessage
  case class AddItem(item: String) extends ShoppingCardMessage
  case class RemoveItem(item: String) extends ShoppingCardMessage
  case class ValidateCard(item: String) extends ShoppingCardMessage

  // 2 - mutable state

  // 3 - hierarchy


  val actor = ActorSystem

}
