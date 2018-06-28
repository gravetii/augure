package com.gravetii.augure.extractor;

import com.gravetii.augure.pojo.LinkPreview;
import com.gravetii.augure.pojo.UriDocument;

public class UriTwitterExtractor implements IUriExtractor {

  @Override
  public LinkPreview getMetaInfo(UriDocument document) {
    LinkPreview preview = new LinkPreview(document.getUrl());
    String title = getMetaContent(document, "title");
    String description = getMetaContent(document, "description");
    String thumbnailUrl = getMetaContent(document, "image");
    if (thumbnailUrl == null) {
      thumbnailUrl = getMetaContent(document, "image:src");
    }

    preview.setTitle(title)
        .setDescription(description)
        .setThumbnailUrl(thumbnailUrl);

    return preview;
  }

  private String getMetaContent(UriDocument document, String tag) {
    String sb = "meta[name=twitter:" + tag + "]";
    String data = document.getMetaInfo(sb, "content");
    if (data == null) {
      sb = "meta[property=twitter:" + tag + "]";
      data = document.getMetaInfo(sb, "content");
    }
    return data;
  }
}
