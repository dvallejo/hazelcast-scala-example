package hazelcast

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

/**
  * Initiates two spray-akka systems:
  * - A hazelcast server in 0.0.0.0:5000/list/mom: the mother
  * - A hazelcast client in 0.0.0.0:5000/list/child: the child
  *
  */

object Main extends App {

  implicit val system = ActorSystem("Shopping-List")

  val api = system.actorOf(ApiActor.props)

  IO(Http) ! Http.Bind(api, interface = "0.0.0.0", port = 5000)

}