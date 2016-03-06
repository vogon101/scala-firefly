package com.vogon101.firefly

import com.vogon101.firefly.data.{FireflyUser, FireflyTask}
import com.vogon101.utils.HTTPRequest
import com.vogon101.utils.requests.HTTPGetRequest
import com.vogon101.utils.responses.XMLResponse

/**
  * Created by Freddie on 06/03/2016.
  */
class FireflyTasksRequest
  (
    site:String,
    login: Boolean = false,
    username: String = "",
    password:String = "",
    user:Option[FireflyUser] = None,
    private var _cookies:Map[String, String] = Map()
  )
{

  def cookies = _cookies

  def execute(): List[FireflyTask] = {
    if (login) {
      _cookies ++= new FireflyLoginRequest(site, username, password).execute().cookies
    }
    else if (user.isDefined) _cookies ++= user.get.cookies

    val res = new HTTPGetRequest[XMLResponse](
      site + "/tasks?view=xml",
      XMLResponse.XMLResponseFactory,
      cookies = cookies
    ).execute()

    val tasksXML = res.bodyAsXML \ "pageplugins" \ "output" \ "tasks" \ "notice"

    tasksXML.map(T => FireflyTask(
      T.attributes("from").text,
      T.attributes("to").text,
      (T \ "message" ).text,
      T.attributes("duedate").text
    )).toList

  }

}
