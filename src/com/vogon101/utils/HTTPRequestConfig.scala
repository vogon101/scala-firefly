package com.vogon101.utils

import org.apache.http.client.config.RequestConfig

/**
  * Created by Freddie on 05/03/2016.
  */
class HTTPRequestConfig (timeout:Int = 1000){

  def build = RequestConfig.custom()
    .setSocketTimeout(timeout)
    .setConnectTimeout(timeout)
    .setConnectionRequestTimeout(timeout)
    .build()

}
