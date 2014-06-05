package hazelcast

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

object Main extends App {

  implicit val system = ActorSystem("Shopping-List")

  val momApi = system.actorOf(MomApiActor.props)

  IO(Http) ! Http.Bind(momApi, interface = "0.0.0.0", port = 8888)

  val childApi = system.actorOf(ChildApiActor.props)

  IO(Http) ! Http.Bind(childApi, interface = "0.0.0.0", port = 9999)

}