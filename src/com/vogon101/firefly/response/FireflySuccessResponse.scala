package com.vogon101.firefly.response

import org.apache.http.client.methods.CloseableHttpResponse

/**
  * Created by Freddie on 06/03/2016.
  */
case class FireflySuccessResponse(r: CloseableHttpResponse, success:Boolean = true, data: String = "", preReadText:Option[String] = None) extends FireflyResponse(r, preReadText){



}
