package com.vogon101.utils.responses

import com.vogon101.utils.HTTPResponse
import org.apache.http.client.methods.CloseableHttpResponse

import scala.xml.{Elem, XML}

/**
  * Created by Freddie on 05/03/2016.
  */
class XMLResponse(r: CloseableHttpResponse, preReadText:Option[String] = None) extends HTTPResponse(r, preReadText){

  lazy val bodyAsXML:Elem = XML.loadString(bodyAsString)

}

object XMLResponse {

  def XMLResponseFactory(r: CloseableHttpResponse) = new XMLResponse(r)

}
