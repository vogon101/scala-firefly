package com.vogon101.firefly.response

import com.vogon101.firefly.data.FireflyTask
import org.apache.http.client.methods.CloseableHttpResponse

/**
  * Created by Freddie on 06/03/2016.
  */
class FireflyTasksResponse(r: CloseableHttpResponse, preReadText:Option[String] = None) extends FireflyResponse(r, preReadText) {

  def tasks = (bodyAsXML \ "pageplugins" \ "output" \ "tasks" \ "notice")
    .map(T => FireflyTask(
      T.attributes("from").text,
      T.attributes("to").text,
      (T \ "message" ).text,
      T.attributes("duedate").text,
      T.attributes("id").text.toInt
    )).toList

}
