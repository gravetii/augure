package com.gravetii.augure.util.http;

import com.gravetii.augure.util.Utils;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;

public class HttpClient {
  public static HttpURLConnection getHttpConnection(HttpClientRequest request) throws Exception {
    URL url = new URL(request.getUrl());
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    connection.setRequestMethod(request.getRequestMethod());
    connection.setInstanceFollowRedirects(false);
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setConnectTimeout(request.getTimeout());
    connection.setReadTimeout(request.getTimeout());

    if (StringUtils.isNotEmpty(request.getUsername())) {
      String authString = request.getUsername() + ":" + request.getPassword();
      String encoding = new sun.misc.BASE64Encoder().encode(authString.getBytes());
      encoding = encoding.replaceAll("\\n", "");
      connection.setRequestProperty("Authorization", "Basic " + encoding);
    }

    return connection;
  }

  private static String getResponse(HttpURLConnection connection) throws Exception {
    int responseCode = connection.getResponseCode();

    if (responseCode >= 400) {
      InputStream errorStream = connection.getErrorStream();
      String errorMessage = Utils.readData(errorStream);
      throw new Exception("Error posting data. " + responseCode + "-" +
          connection.getResponseMessage() + ". Error : " + errorMessage);
    }

    return Utils.readData(connection.getInputStream());
  }

  public static String getData(HttpClientRequest request) throws Exception {
    try {
      request.setRequestMethod("GET");
      HttpURLConnection httpConnection = getHttpConnection(request);
      Map<String, String> headers = request.getHeaders();
      if (headers != null) {
        for (Entry<String, String> header : request.getHeaders().entrySet()) {
          httpConnection.setRequestProperty(header.getKey(), header.getValue());
        }
      }

      return getResponse(httpConnection);
    }
    catch (Exception e) {
      throw new Exception("Error getting data. Error : " + e.getMessage(), e);
    }
  }
}
