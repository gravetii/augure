package com.gravetii.commons.extractor;

import com.gravetii.commons.pojo.LinkPreview;
import com.gravetii.commons.pojo.UriDocument;

/**
 * Created by sandeepd on 28/06/18.
 */
public class UriTwitterExtractor implements IUriExtractor {

  @Override
  public LinkPreview getMetaInfo(UriDocument document) {
    if (document == null) {
      return null;
    }

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
