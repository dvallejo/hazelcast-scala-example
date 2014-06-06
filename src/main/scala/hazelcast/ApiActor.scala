package hazelcast

import akka.actor.{ Actor, Props }
import akka.pattern.ask
import akka.util.Timeout

import reflect.ClassTag
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import spray.routing._
import spray.routing.Directives._

import Messages._

/**
  * Mom can add and remove items with this API
  * Child can get the complete list with this API
  */

class ApiActor extends HttpService with Actor {

	val mom = context.actorOf(MomActor.props)
	val child = context.actorOf(ChildActor.props)

	def actorRefFactory = context

	implicit val timeout = Timeout(3 seconds)

	def receive = runRoute(
		  path("mom" / "list" / "(.+)".r) { item =>
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
		  } ~ path("child" / "list") {
				  	get {
			      	complete(
			      		(child ? GetItems).mapTo[String]
			    		)
				  	}
				  }
		)

}

object ApiActor {
	def props = Props[ApiActor]
}
