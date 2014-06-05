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
  * Child can get the complete list with this API
  */

class ChildApiActor extends HttpService with Actor {

	val child = context.actorOf(ChildActor.props)

	def actorRefFactory = context

	implicit val timeout = Timeout(3 seconds)

	def receive = runRoute(
			pathPrefix("list") {
	      get {
	      	complete(
	      		(child ? GetItems).mapTo[String]
      		)
		  	}
			}
		)

}

object ChildApiActor {
	def props = Props[ChildApiActor]
}
