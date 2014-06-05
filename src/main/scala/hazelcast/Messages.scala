package hazelcast

object Messages {

	case object GetItems
	case class AddItem(item: String, amount: Int)
	case class RemoveItem(item: String)

}
