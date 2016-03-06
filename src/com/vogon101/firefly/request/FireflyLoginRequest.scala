package com.vogon101.firefly.request

import com.vogon101.firefly.data.{FireflyLogin, FireflyUser}
import com.vogon101.firefly.response.{FireflyResponse, FireflyUserResponse}
import com.vogon101.utils.requests.HTTPFormPostRequest
import com.vogon101.utils.responses.XMLResponse
import org.apache.http.message.BasicNameValuePair

/**
  * Created by Freddie on 05/03/2016.
  */
class FireflyLoginRequest (user: FireflyLogin) extends FireflyRequest (
  "login/login.aspx",
  user.site,
  forceXML = false
) {

  override def execute (): FireflyUserResponse = {
    val res = new HTTPFormPostRequest[FireflyResponse](
      site + "/login/login.aspx?view=xml",
      FireflyResponse.fireflyResponseFactory,
      List(
        new BasicNameValuePair("username", user.username),
        new BasicNameValuePair("password", user.password)
      )
    ).execute()

    FireflyUserResponse(res.response, FireflyUser(
      user.username,
      res.cookies
    ))
  }

}
