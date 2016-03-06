package com.vogon101.firefly.test

import com.vogon101.firefly.{FireflyTasksRequest, FireflyRequest, FireflyLoginRequest}

/**
  * Created by Freddie on 05/03/2016.
  */
object Test extends App{

  //val a = new FireflyRequest("/tasks", "https://firefly.westminster.org.uk", username="", password="", login = true)
  //val r = a.execute()
  val tasks = new FireflyTasksRequest("https://firefly.westminster.org.uk", true, readLine("Username >"), readLine("Password >")).execute()

  tasks.foreach(println)


}
