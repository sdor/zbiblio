package com.biosystechnologies.pubmed.utils

import com.biosystechnologies.pubmed.{PubmedFile, PubmedStore}
import zio.stream.ZSink
import zio.{IO, UIO, ZIO, ZLayer}

case class PubmedFileMaxId(pubmedFile: PubmedFile) extends MaxId:

  override def get: ZIO[Any, Any, Int] = for {
    ids <- pubmedFile.ids.run(ZSink.collectAll)
  } yield ids.max


object PubmedFileMaxId:
  def layer: ZLayer[PubmedFile, Nothing, PubmedFileMaxId] = ZLayer {
    for {
      file <- ZIO.service[PubmedFile]
    } yield PubmedFileMaxId(file)
  }
