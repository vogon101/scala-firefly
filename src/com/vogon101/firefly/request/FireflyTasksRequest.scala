package com.vogon101.firefly.request

import com.vogon101.firefly.data.{FireflyTask, FireflyUser}
import com.vogon101.firefly.response.FireflyTasksResponse
import com.vogon101.utils.requests.HTTPGetRequest
import com.vogon101.utils.responses.XMLResponse

/**
  * Created by Freddie on 06/03/2016.
  */
class FireflyTasksRequest
  (
    site:String,
    user:FireflyUser,
    private var _cookies:Map[String, String] = Map()
  )
extends FireflyRequest("tasks", site, Option(user), _cookies)
{

  override def execute(): FireflyTasksResponse = new FireflyTasksResponse(super.execute().response)

}
