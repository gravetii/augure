package io.github.gravetii.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OEmbedResponse
{
  @JsonInclude(Include.NON_NULL)
  private String type;

  @JsonInclude(Include.NON_NULL)
  private String version;

  @JsonInclude(Include.NON_NULL)
  private String title;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("author_name")
  private String authorName;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("author_url")
  private String authorUrl;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("provider_name")
  private String providerName;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("provider_url")
  private String providerUrl;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("cache_age")
  private Long cacheAge;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("thumbnail_url")
  private String thumbnailUrl;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getAuthorUrl() {
    return authorUrl;
  }

  public void setAuthorUrl(String authorUrl) {
    this.authorUrl = authorUrl;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public String getProviderUrl() {
    return providerUrl;
  }

  public void setProviderUrl(String providerUrl) {
    this.providerUrl = providerUrl;
  }

  public Long getCacheAge() {
    return cacheAge;
  }

  public void setCacheAge(Long cacheAge) {
    this.cacheAge = cacheAge;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

  @Override
  public String toString() {
    return "OEmbedResponse{" +
        "type='" + type + '\'' +
        ", version='" + version + '\'' +
        ", title='" + title + '\'' +
        ", authorName='" + authorName + '\'' +
        ", authorUrl='" + authorUrl + '\'' +
        ", providerName='" + providerName + '\'' +
        ", providerUrl='" + providerUrl + '\'' +
        ", cacheAge=" + cacheAge +
        ", thumbnailUrl='" + thumbnailUrl + '\'' +
        '}';
  }
}