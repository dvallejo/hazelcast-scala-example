package hazelcast

import akka.actor.{ Actor, Props }

import com.hazelcast.core.Hazelcast
import com.hazelcast.config.Config
import Messages._

/**
  * Mom is a hazelcast server. 
  * Declares a map. It is possible to add 
  * and remove map items.
  */

class MomActor extends Actor {

  val hazelcastInstance = Hazelcast.newHazelcastInstance(new Config())
  val shoppingList = hazelcastInstance.getMap[String, Int]("shoppingList")

  def receive = {
    
    case AddItem(item, amount) =>
      sender ! shoppingList.put(item, amount)
    case RemoveItem(item) =>
      sender ! shoppingList.remove(item)
      
  }

}

object MomActor {
  def props = Props[MomActor]
}

