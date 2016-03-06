package com.vogon101.firefly

import com.vogon101.firefly.data.FireflyUser
import com.vogon101.utils.HTTPRequest
import com.vogon101.utils.requests.HTTPGetRequest
import com.vogon101.utils.responses.XMLResponse


/**
  * Created by Freddie on 05/03/2016.
  */
class FireflyRequest
  (
    page: String,
    site:String,
    login: Boolean = false,
    username: String = "",
    password:String = "",
    private val _u:Option[FireflyUser] = None,
    private var _cookies:Map[String, String] = Map()
  ) extends HTTPRequest {

  def cookies = _cookies

  private var _user: FireflyUser = None.asInstanceOf[FireflyUser]
  def user = _user

  override def execute(): XMLResponse = {

    if (login) {
      _user = new FireflyLoginRequest(site, username, password).execute()
      if (!user.loggedIn)
        return None.asInstanceOf[XMLResponse]
      else
        _cookies ++= user.cookies
    }
    else if (_u.isDefined){
      _user = _u.get
      _cookies ++= user.cookies
    }

    //println(cookies)
    new HTTPGetRequest[XMLResponse](
      site + "/" + page + "?view=xml",
      XMLResponse.XMLResponseFactory,
      cookies = cookies
    ).execute()

  }

}
