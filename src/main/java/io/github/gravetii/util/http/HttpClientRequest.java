package io.github.gravetii.util.http;

import java.util.Map;

public class HttpClientRequest
{
  public static class Builder {
    private String url;
    private String body;
    private String username;
    private String password;
    private boolean ssl = false;
    private String requestMethod;
    private int timeout = 10000;
    private Map<String, String> headers;
    private String contentType;

    public Builder setUrl(String url) {
      this.url = url;
      return this;
    }

    public Builder setBody(String body) {
      this.body = body;
      return this;
    }

    public Builder setUsername(String username) {
      this.username = username;
      return this;
    }

    public Builder setPassword(String password) {
      this.password = password;
      return this;
    }

    public Builder setSsl(boolean ssl) {
      this.ssl = ssl;
      return this;
    }

    public Builder setRequestMethod(String requestMethod) {
      this.requestMethod = requestMethod;
      return this;
    }

    public Builder setTimeout(int timeout) {
      this.timeout = timeout;
      return this;
    }

    public Builder setHeaders(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public Builder setContentType(String contentType) {
      this.contentType = contentType;
      return this;
    }

    @Override
    public String toString() {
      return "Builder{" +
          "url='" + url + '\'' +
          ", body='" + body + '\'' +
          ", username='" + username + '\'' +
          ", password='" + password + '\'' +
          ", ssl=" + ssl +
          ", requestMethod='" + requestMethod + '\'' +
          ", timeout=" + timeout +
          ", headers=" + headers +
          ", contentType='" + contentType + '\'' +
          '}';
    }

    public HttpClientRequest build() {
      return new HttpClientRequest(this);
    }

  }

  private String url;

  private String body;

  private String username;

  private String password;

  private boolean ssl;

  private String requestMethod;

  private int timeout;

  private Map<String, String> headers;

  private String contentType;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getBody() {
    return body;
  }

  public void setBody() {
    this.body = body;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isSsl() {
    return ssl;
  }

  public void setSsl(boolean ssl) {
    this.ssl = ssl;
  }

  public String getRequestMethod() {
    return requestMethod;
  }

  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public HttpClientRequest() {

  }

  public HttpClientRequest(Builder builder) {
    this.url = builder.url;
    this.body = builder.body;
    this.username = builder.username;
    this.password = builder.password;
    this.ssl = builder.ssl;
    this.requestMethod = builder.requestMethod;
    this.timeout = builder.timeout;
    this.headers = builder.headers;
    this.contentType = builder.contentType;
  }
}