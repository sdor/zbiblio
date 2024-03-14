package com.biosystechnologies.pubmed.utils

import zio.*
trait MaxId:
   def get: ZIO[Any, Any, Int]


object MaxId:
  def get: ZIO[MaxId, Any, Int] = ZIO.serviceWithZIO[MaxId](_.get)
