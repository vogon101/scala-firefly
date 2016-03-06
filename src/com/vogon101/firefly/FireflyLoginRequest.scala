package com.vogon101.firefly

import com.vogon101.firefly.data.FireflyUser
import com.vogon101.utils.requests.HTTPFormPostRequest
import com.vogon101.utils.responses.XMLResponse
import org.apache.http.message.BasicNameValuePair

/**
  * Created by Freddie on 05/03/2016.
  */
class FireflyLoginRequest (url: String, username: String, password: String) {

  def execute (): FireflyUser = {

    val req = new HTTPFormPostRequest[XMLResponse](
      url + "/login/login.aspx?view=xml",
      XMLResponse.XMLResponseFactory,
      List(
        new BasicNameValuePair("username", username),
        new BasicNameValuePair("password", password)
      )
    )

    new FireflyUser(req.execute(), username)

  }


}
