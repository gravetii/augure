package io.github.gravetii.service;

import io.github.gravetii.extractor.IUriExtractor;
import io.github.gravetii.extractor.UriMetaExtractor;
import io.github.gravetii.extractor.UriOEmbedExtractor;
import io.github.gravetii.extractor.UriOpenGraphExtractor;
import io.github.gravetii.extractor.UriTwitterExtractor;
import io.github.gravetii.pojo.LinkPreview;
import io.github.gravetii.pojo.UriDocument;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UriExtractorService {
  private static final Logger logger = LoggerFactory
      .getLogger(UriExtractorService.class.getCanonicalName());

  private List<IUriExtractor> extractors;

  public UriExtractorService() {
    this.extractors = new ArrayList<>();
    register(new UriOEmbedExtractor());
    register(new UriTwitterExtractor());
    register(new UriOpenGraphExtractor());
    register(new UriMetaExtractor());
    logger.info("Finished registering URI extractors");
  }

  private void register(IUriExtractor extractor) {
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
