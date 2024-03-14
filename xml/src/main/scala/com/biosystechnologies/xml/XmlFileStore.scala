package com.biosystechnologies.xml
import zio.*
import zio.stream.*
import zio.stream.ZPipeline.gunzipAuto

import java.nio.file.Path

trait XmlFileStore extends XmlBytesStore {
   def path: Path

   override def bytes: ZStream[Any, Any, Byte] = {
      val bufferSize: Int = 64 * 1024
      ZStream.fromPath(path,chunkSize = 1024).via(gunzipAuto(bufferSize))
   }

}
