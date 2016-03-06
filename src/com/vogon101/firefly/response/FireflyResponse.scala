package com.vogon101.firefly.response

import com.vogon101.utils.responses.XMLResponse
import org.apache.http.client.methods.CloseableHttpResponse

/**
  * Created by Freddie on 06/03/2016.
  */
class FireflyResponse(r:CloseableHttpResponse, preReadText:Option[String] = None) extends XMLResponse(r, preReadText) {

}

object FireflyResponse {

  def fireflyResponseFactory(r:CloseableHttpResponse): FireflyResponse = new FireflyResponse(r)

}
