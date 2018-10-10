package io.chrisdavenport.ember.example 

import scala.concurrent.duration._
import fs2._
import cats.effect._
import cats.implicits._
import org.http4s._

import _root_.io.chrisdavenport.ember.client.EmberClient
import scala.concurrent.ExecutionContext.global

object ClientExample extends IOApp{

  def run(args: List[String]) : IO[ExitCode] = {
    val req = Request[IO](Method.GET, Uri.unsafeFromString("https://christopherdavenport.github.io/"))
    EmberClient.simple[IO](global)
      .use(
        _.expect[String](req)
        .flatMap(body => 
          Sync[IO].delay(println(s"Response - $body"))
        )
      )
  }.as(ExitCode.Success)

}