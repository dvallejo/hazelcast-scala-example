package hazelcast

import akka.actor.{ Actor, Props }

import spray.routing._
import spray.routing.Directives._

import Messages._

class MomApiActor extends HttpService with Actor {

	val mom = context.actorOf(MomActor.props)

	def actorRefFactory = context

	def receive = runRoute(
			pathPrefix("list") { 
			  path("(.+)".r) { item =>
		     	parameter("n") { amount =>
			     	post {
			     			mom ! AddItem(item, amount.toInt)
			     			complete("OK, mum")
		     		}
		     	} ~
	     		delete {
	     				mom ! RemoveItem(item)
	     				complete("OK, mum")
	     		}
			  }
			}
		)

}

object MomApiActor {
	def props = Props[MomApiActor]
}
