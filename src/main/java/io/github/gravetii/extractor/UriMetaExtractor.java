package io.github.gravetii.extractor;

import io.github.gravetii.pojo.LinkPreview;
import io.github.gravetii.pojo.UriDocument;
import org.apache.commons.lang3.StringUtils;

public class UriMetaExtractor implements IUriExtractor {

  @Override
  public LinkPreview getMetaInfo(UriDocument document) {
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
