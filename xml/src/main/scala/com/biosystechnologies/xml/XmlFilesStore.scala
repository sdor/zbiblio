package com.biosystechnologies.xml
import zio.*
import zio.stream.*
import zio.nio.*
import zio.nio.file.*
trait XmlFilesStore:
  def files: ZIO[ XmlFilesSource,Throwable,List[Path]] = for {
    source <- ZIO.service[XmlFilesSource]
    _files <- source.files.run(ZSink.collectAll)
  }  yield _files.toList
