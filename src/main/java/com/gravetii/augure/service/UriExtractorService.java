package com.gravetii.augure.service;

import com.gravetii.augure.extractor.IUriExtractor;
import com.gravetii.augure.extractor.UriMetaExtractor;
import com.gravetii.augure.extractor.UriOpenGraphExtractor;
import com.gravetii.augure.extractor.UriTwitterExtractor;
import com.gravetii.augure.pojo.LinkPreview;
import com.gravetii.augure.pojo.UriDocument;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class UriExtractorService {
  private List<IUriExtractor> extractors;

  public UriExtractorService() {
    register(new UriTwitterExtractor());
    register(new UriOpenGraphExtractor());
    register(new UriMetaExtractor());
  }

  private void register(IUriExtractor extractor) {
    if (this.extractors == null) {
      this.extractors = new ArrayList<>();
    }
    extractors.add(extractor);
  }

  private String getThumbnailUrl(UriDocument document, String thumbnailUrl) {
    if (thumbnailUrl == null) {
      thumbnailUrl = document.getMetaInfo("meta[itemprop=image]", "content");
    }

    if (thumbnailUrl == null) {
      thumbnailUrl = document.getMetaInfo("link[href~=.*\\.(ico|png)]", "href");
    }

    if (thumbnailUrl == null) {
      thumbnailUrl = document.getMetaInfo("link[rel=shortcut icon]", "href");
    }

    if (StringUtils.isNotEmpty(thumbnailUrl) && thumbnailUrl.endsWith(".svg")) {
      return thumbnailUrl;
    }

    if (thumbnailUrl != null) {
      if (thumbnailUrl.startsWith("//")) {
        String[] arr = StringUtils.split(document.fetchLocation(), "/");
        thumbnailUrl = arr[0] + thumbnailUrl;
      } else if (thumbnailUrl.startsWith("/")) {
        String[] arr = StringUtils.split(document.fetchLocation(), "/");
        thumbnailUrl = arr[0] + "//" + arr[1] + thumbnailUrl;
      } else if (!thumbnailUrl.startsWith("http")) {
        String[] arr = StringUtils.split(document.fetchLocation(), "/");
        thumbnailUrl = arr[0] + "//" + arr[1] + "/" + thumbnailUrl;
      }
    }

    return thumbnailUrl;
  }

  public LinkPreview get(UriDocument document) {
    LinkPreview preview = new LinkPreview(document.getUrl());
    for (IUriExtractor extractor: extractors) {
      LinkPreview l = extractor.getMetaInfo(document);
      if (l != null) {
        preview = preview.merge(l);
      }
    }

    String thumbnailUrl = getThumbnailUrl(document, preview.getThumbnailUrl());
    preview.setThumbnailUrl(thumbnailUrl);
    preview.setProviderName(document.fetchProviderName());
    return preview;
  }
}
