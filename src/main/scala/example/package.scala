import zio.{Has, ZLayer}
import zio.console.Console
import zio.random.Random

package object example {

  type Example = Has[Example.Service]

  object Example {

    trait Service

  }

  val live: ZLayer[Console with Random, Nothing, Example] =
    ZLayer.fromServices[Console.Service, Random.Service, Example.Service] { (console, random) =>
      new Example.Service {}
    }

}
