package com.vogon101.firefly.request

import com.vogon101.firefly.data.FireflyUser
import com.vogon101.firefly.response.FireflyResponse
import com.vogon101.utils.HTTPRequest
import com.vogon101.utils.requests.HTTPGetRequest
import com.vogon101.utils.responses.XMLResponse


/**
  * Created by Freddie on 05/03/2016.
  */
class FireflyRequest
  (
    val page: String,
    val site:String,
    val user: Option[FireflyUser] = None,
    private var _cookies: Map[String,String] = Map(),
    val forceXML: Boolean = true
  ) extends HTTPRequest {

  def cookies = _cookies
  if (user.isDefined) {
    _cookies ++= user.get.cookies
  }

  override def execute(): FireflyResponse = {

    new HTTPGetRequest[FireflyResponse](
      site + "/" + page + (if (forceXML) "?view=xml" else ""),
      FireflyResponse.fireflyResponseFactory,
      cookies = cookies
    ).execute()

  }

}
