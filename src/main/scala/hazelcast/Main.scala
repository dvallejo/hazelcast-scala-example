package hazelcast

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

/**
  * Initiates two spray-akka systems:
  * - A hazelcast server in 0.0.0.0:5000: the mother
  * - A hazelcast client in 0.0.0.0:6000: the child
  *
  */

object Main extends App {

  implicit val system = ActorSystem("Shopping-List")

  val momApi = system.actorOf(MomApiActor.props)

  IO(Http) ! Http.Bind(momApi, interface = "0.0.0.0", port = 5000)

  val childApi = system.actorOf(ChildApiActor.props)

  IO(Http) ! Http.Bind(childApi, interface = "0.0.0.0", port = 6000)

}