package hazelcast

import akka.actor.{ Actor, Props }

import com.hazelcast.client.HazelcastClient
import com.hazelcast.client.config.ClientConfig
import Messages._

import scala.collection.JavaConversions._

class ChildActor extends Actor {

  val hazelcastClient = HazelcastClient.newHazelcastClient(new ClientConfig())
  val shoppingList = hazelcastClient.getMap[String, Int]("shoppingList")

  def receive = {
    
    case GetItems =>
      val items = (shoppingList.keys.toList zip shoppingList.values.toList).mkString("\n")
      sender ! items

  }
}

object ChildActor {

	def props = Props[ChildActor]
}

