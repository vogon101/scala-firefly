package com.vogon101.utils.requests

import java.net.URI

import com.vogon101.utils.{HTTPRequest, HTTPRequestConfig, HTTPResponse}
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}
import org.apache.http.impl.client.{BasicCookieStore, DefaultHttpClient}
import org.apache.http.impl.cookie.BasicClientCookie

/**
  * Created by Freddie on 05/03/2016.
  */
class HTTPGetRequest[R <: HTTPResponse]
  (
    url: String,
    responseFactory: (CloseableHttpResponse) => R = HTTPResponse.defaultHTTPResponseFactory[R](_),
    config: HTTPRequestConfig = new HTTPRequestConfig(),
    cookies: Map[String, String] = Map()
  ) extends HTTPRequest {


  def execute():R = {
    val get = new HttpGet(url)
    get.setConfig(config.build)
    val cookieStore = new BasicCookieStore()
    if (cookies.nonEmpty){
      cookies.foreach {
        case (n,v) =>
          //println(s"$n=$v")
          val c = new BasicClientCookie(n, v)
          c.setDomain(new URI(url).getHost)
          cookieStore.addCookie(c)
      }
    }
    //println(get.getAllHeaders.foreach(X =>println(X.getName, X.getValue)))
    val client = new DefaultHttpClient()
    client.setCookieStore(cookieStore)
    responseFactory(client.execute(get))
  }


}
