package com.vogon101.firefly.data

import com.vogon101.utils.HTTPResponse

/**
  * Created by Freddie on 06/03/2016.
  */
class FireflyUser(r: HTTPResponse, username: String){

  lazy val cookies = r.cookies
  lazy val loggedIn = cookies("FireflyNETLoggedIn")=="yes"

}
