package com.vogon101.utils.requests

import java.net.URI
import java.util

import com.vogon101.utils.{HTTPRequestConfig, HTTPResponse, HTTPRequest}
import org.apache.http.impl.cookie.BasicClientCookie
import org.apache.http.{Consts, NameValuePair}
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.{HttpPost, CloseableHttpResponse}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{BasicCookieStore, DefaultHttpClient}

import scala.collection.JavaConversions

/**
  * Created by Freddie on 05/03/2016.
  */
class HTTPFormPostRequest[R <: HTTPResponse]
(
  url: String,
  responseFactory: (CloseableHttpResponse) => R = HTTPResponse.defaultHTTPResponseFactory[R](_),
  params: List[NameValuePair],
  config: HTTPRequestConfig = new HTTPRequestConfig(),
  cookies: Map[String, String] = Map()
) extends HTTPRequest {

  def execute() = {
    val post = new HttpPost(url)
    post.setConfig(config.build)
    post.setHeader("Content-type", "application/x-www-form-urlencoded")
    post.setEntity(new UrlEncodedFormEntity(JavaConversions.asJavaCollection(params), Consts.UTF_8))

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
