package com.gravetii.augure.extractor;

import com.gravetii.augure.pojo.LinkPreview;
import com.gravetii.augure.pojo.UriDocument;
import org.apache.commons.lang3.StringUtils;

public class UriMetaExtractor implements IUriExtractor {

  @Override
  public LinkPreview getMetaInfo(UriDocument document) {
    if (document == null) {
      return null;
    }

    LinkPreview preview = new LinkPreview(document.getUrl());
    String title = document.fetchTitle();
    if (StringUtils.isEmpty(title)) {
      title = document.getMetaInfo("meta[name=title]", "content");
    }
    String thumbnailUrl = document.getMetaInfo("meta[name=image]", "content");
    String description = document.getMetaInfo("meta[name=description]", "content");
    preview.setTitle(title)
        .setThumbnailUrl(thumbnailUrl)
        .setDescription(description);

    return preview;
  }
}
