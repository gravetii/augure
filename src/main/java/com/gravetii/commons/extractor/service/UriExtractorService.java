package com.gravetii.commons.extractor.service;

import com.gravetii.commons.extractor.IUriExtractor;
import com.gravetii.commons.pojo.LinkPreview;
import com.gravetii.commons.pojo.UriDocument;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by sandeepd on 28/06/18.
 */
public class UriExtractorService {
  private List<IUriExtractor> extractors;
  private LinkPreview preview;

  public UriExtractorService(String url) {
    this.preview = new LinkPreview(url);
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

  public LinkPreview getMetaInfo(UriDocument document) {
    if (document != null) {
      for (IUriExtractor extractor: extractors) {
        LinkPreview l = extractor.getMetaInfo(document);
        if (l != null) {
          preview = preview.merge(l);
        }
      }

      String thumbnailUrl = getThumbnailUrl(document, preview.getThumbnailUrl());
      this.preview.setThumbnailUrl(thumbnailUrl);
      this.preview.setProviderName(document.fetchProviderName());
    }

    return preview;
  }
}