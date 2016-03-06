package com.vogon101.firefly.data

import java.util.NoSuchElementException

/**
  * Created by Freddie on 06/03/2016.
  */
case class FireflyUser(username: String, cookies: Map[String, String] = Map()){

  lazy val loggedIn = try {
    cookies("FireflyNETLoggedIn")=="yes"
  }
  catch{
    case nse: NoSuchElementException => false
  }

}
