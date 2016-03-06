package com.vogon101.firefly.response

import com.vogon101.firefly.data.FireflyUser
import org.apache.http.client.methods.CloseableHttpResponse

/**
  * Created by Freddie on 06/03/2016.
  */
case class FireflyUserResponse(r: CloseableHttpResponse, user:FireflyUser) extends FireflyResponse(r) {



}
