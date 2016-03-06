package com.vogon101.firefly.test

import com.vogon101.firefly.data.{FireflyUser, FireflyLogin}
import com.vogon101.firefly.request.{FireflyLoginRequest, FireflyTasksRequest}

/**
  * Created by Freddie on 05/03/2016.
  */
object Test extends App{

  val SITE = "https://firefly.westminster.org.uk"

  //val a = new FireflyRequest("/tasks", "https://firefly.westminster.org.uk", username="", password="", login = true)
  //val r = a.execute()
  val user:FireflyUser = new FireflyLoginRequest(
    FireflyLogin(
      SITE,
      readLine("Username >").stripLineEnd,
      readLine("Password >").stripLineEnd
    )
  ).execute()
    .user

  println("Logged In: " + user.loggedIn)
  println(user.cookies)
  println("")

  val tasks = new FireflyTasksRequest(
    SITE,
    user
  ).execute()

  tasks.tasks.foreach(println)

}
