import zio.{Has, ZLayer, URIO}
import zio.console.Console
import zio.random.Random

package object example {

  type Example = Has[Example.Service]

  object Example {
    trait Service {
      def foo1: String
      def foo2: String
      def foo3: String
      def foo4: String
      def foo5: String
      def bar: String
    }
  }

  val live: ZLayer[Console with Random, Nothing, Example] =
    ZLayer.fromServices[Console.Service, Random.Service, Example.Service] {
      (console, random) =>
        new Example.Service {
          override def foo1: String = ???
          override def foo2: String = ???
          override def foo3: String = ???
          override def foo4: String = ???
          override def foo5: String = ???
          override def bar: String = ???
        }
    }

  def foo1: URIO[Example, String] = URIO.access(_.get.foo1)
  def foo2: URIO[Example, String] = URIO.access(_.get.foo2)
  def foo3: URIO[Example, String] = URIO.access(_.get.foo3)
  def buzz: URIO[Example, String] = URIO.access(_.get.bar)
}
