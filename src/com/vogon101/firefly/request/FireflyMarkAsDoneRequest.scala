package com.vogon101.firefly.request

import com.vogon101.firefly.data.{FireflyUser, FireflyTask}
import com.vogon101.firefly.response.{FireflySuccessResponse, FireflyUserResponse, FireflyResponse}
import com.vogon101.utils.requests.HTTPFormPostRequest
import org.apache.http.message.BasicNameValuePair

/**
  * Created by Freddie on 06/03/2016.
  */
class FireflyMarkAsDoneRequest(site: String, task: FireflyTask, user:FireflyUser) extends FireflyRequest(
  "tasks",
  site,
  Option(user)
){

  override def execute(): FireflySuccessResponse = {

    val res = new HTTPFormPostRequest[FireflyResponse](
      site + "/tasks?view=xml",
      FireflyResponse.fireflyResponseFactory,
      List(
        new BasicNameValuePair("action", "done"),
        new BasicNameValuePair("notice_id", task.id.toString),
        new BasicNameValuePair("ajax", "on")
      ),
      cookies = user.cookies
    ).execute()

    val resBody = res.bodyAsString

    if (resBody == "") {
      FireflySuccessResponse(res.response, success = true, preReadText = Option(resBody))
    }
    else {
      FireflySuccessResponse(res.response, success = false, data = resBody, preReadText = Option(resBody))
    }
  }

}
