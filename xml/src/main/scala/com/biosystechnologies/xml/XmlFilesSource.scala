package com.biosystechnologies.xml

import zio.nio.file.Path
import zio.stream.ZStream

trait XmlFilesSource:
  def files: ZStream[Any,Throwable,Path]
