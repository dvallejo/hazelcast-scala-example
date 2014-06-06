package hazelcast

/**
  * Messages between APIs and hazelcast actors
  */

object Messages {

  case object GetItems
  case class AddItem(item: String, amount: Int)
  case class RemoveItem(item: String)

}
