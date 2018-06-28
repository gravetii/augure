package com.gravetii.commons.pojo;

import org.apache.commons.lang3.StringUtils;

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

  public LinkPreview setTitle(String title) {
    this.title = title;
    return this;
  }

  public LinkPreview setProviderName(String providerName) {
    this.providerName = providerName;
    return this;
  }

  public LinkPreview setUrl(String url) {
    this.url = url;
    return this;
  }

  public LinkPreview setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
    return this;
  }

  public LinkPreview setDescription(String description) {
    this.description = description;
    return this;
  }

  public LinkPreview merge(LinkPreview other) {
    if (other == null) {
      return this;
    }

    LinkPreview preview = new LinkPreview(url);
    preview.setTitle(StringUtils.isNotEmpty(other.getTitle())
        ? other.getTitle(): title);
    preview.setThumbnailUrl(StringUtils.isNotEmpty(other.getThumbnailUrl())
        ? other.getThumbnailUrl() : thumbnailUrl);
    preview.setDescription(StringUtils.isNotEmpty(other.getDescription())
        ? other.getDescription() : description);

    return preview;
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
