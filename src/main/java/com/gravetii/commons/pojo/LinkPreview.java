package com.gravetii.commons.pojo;

/**
 * Created by sandeepd on 28/06/18.
 */
public class LinkPreview {
  private String title;
  private String providerName;
  private String url;
  private String thumbnailUrl;
  private String description;

  public LinkPreview(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public String getProviderName() {
    return providerName;
  }

  public String getUrl() {
    return url;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "LinkPreview{" +
        "title='" + title + '\'' +
        ", providerName='" + providerName + '\'' +
        ", url='" + url + '\'' +
        ", thumbnailUrl='" + thumbnailUrl + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
