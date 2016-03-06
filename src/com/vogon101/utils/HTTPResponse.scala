package com.vogon101.utils

import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.util.EntityUtils

/**
  * Created by Freddie on 05/03/2016.
  */
class HTTPResponse(val response: CloseableHttpResponse, preReadText:Option[String] = None) {

  lazy val bodyAsString = if (preReadText.isDefined) preReadText.get else EntityUtils.toString(response.getEntity)

  def cookies:Map[String,String] = {
    var c = Map[String,String]()
    response.getAllHeaders.toList.foreach(H => {
      if (H.getName == "Set-Cookie") {
        //println(H.getValue)
        c += H.getValue.split("=")(0) -> H.getValue.split("=")(1).split(";")(0)
      }
    })
    c
  }


}

object HTTPResponse {

  def defaultHTTPResponseFactory[R <: HTTPResponse](r: CloseableHttpResponse) = new HTTPResponse(r).asInstanceOf[R]

}