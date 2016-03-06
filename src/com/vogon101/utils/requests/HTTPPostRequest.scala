package com.vogon101.utils.requests

import java.net.URI

import com.vogon101.utils.{HTTPRequest, HTTPRequestConfig, HTTPResponse}
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.cookie.ClientCookie
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{BasicCookieStore, DefaultHttpClient}
import org.apache.http.impl.cookie.BasicClientCookie

/**
  * Created by Freddie on 05/03/2016.
  */
class HTTPPostRequest[R <: HTTPResponse]
(
  url: String,
  responseFactory: (CloseableHttpResponse) => R = HTTPResponse.defaultHTTPResponseFactory[R](_),
  config: HTTPRequestConfig = new HTTPRequestConfig(),
  body: String = "",
  bodyMIMEType: String = "application/x-www-form-urlencoded",
  cookies: Map[String, String] = Map()
) extends HTTPRequest {


  def execute():R = {
    val post = new HttpPost(url)
    post.setConfig(config.build)
    post.setHeader("Content-type", bodyMIMEType)
    post.setEntity(new StringEntity(body))

    val cookieStore = new BasicCookieStore()
    if (cookies.nonEmpty){
      cookies.foreach {
        case (n,v) => {
          //println(s"$n=$v")
          val c = new BasicClientCookie(n, v)
          c.setDomain(new URI(url).getHost)
          cookieStore.addCookie(c)
        }
      }
    }
    val client = new DefaultHttpClient()
    client.setCookieStore(cookieStore)
    responseFactory(client.execute(post))
  }


}
