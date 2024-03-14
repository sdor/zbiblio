package com.biosystechnologies.pubmed.utils

import com.biosystechnologies.pubmed.{PubmedDirectory, PubmedFile}
import zio.ZIO
import zio.stream.ZSink

case class PubmedDirectoryMaxId (directory: PubmedDirectory) extends MaxId:
  override def get: ZIO[Any, Any, Int] = {
    for {
      files <- directory.files.run(ZSink.collectAll)
      path = files.maxBy(_.filename.toString)
      file = PubmedFile(path.toFile.toPath)
      ids <- file.ids.run(ZSink.collectAll)
    } yield ids.max
  }

object PubmedDirectoryMaxId:
  val layer: ZIO[PubmedDirectory, Nothing, PubmedDirectoryMaxId] = for {
    dir <- ZIO.service[PubmedDirectory]
  } yield PubmedDirectoryMaxId(dir)