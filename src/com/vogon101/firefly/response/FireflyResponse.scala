package com.vogon101.firefly.response

import com.vogon101.utils.responses.XMLResponse
import org.apache.http.client.methods.CloseableHttpResponse

/**
  * Created by Freddie on 06/03/2016.
  */
class FireflyResponse(r:CloseableHttpResponse) extends XMLResponse(r) {

}

object FireflyResponse {

  def fireflyResponseFactory(r:CloseableHttpResponse): FireflyResponse = new FireflyResponse(r)

}
