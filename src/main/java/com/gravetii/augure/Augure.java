package com.gravetii.augure;

import com.google.common.base.Preconditions;
import com.gravetii.augure.service.UriExtractorService;
import com.gravetii.augure.pojo.LinkPreview;
import com.gravetii.augure.pojo.UriDocument;
import com.gravetii.augure.util.Utils;
import com.gravetii.augure.util.exception.AugureException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Augure {
  private static final Logger logger = LoggerFactory.getLogger(Augure.class.getCanonicalName());

  private UriExtractorService service;

  public Augure() {
    this.service = new UriExtractorService();
    logger.info("Successfully initialised Augure");
  }

  public LinkPreview get(String url) throws Exception {
    try {
      Preconditions.checkArgument(StringUtils.isNotEmpty(url),
          "Input url cannot be null or empty");
      url = Utils.getFullUrl(url);
      UriDocument document = new UriDocument(url);
      return service.get(document);
    }
    catch (Exception e) {
      logger.error("Error while getting link preview for url {}", url, e);
      throw new AugureException(e);
    }
  }

  public static void main(String[] args) throws Exception {
    String url = "http://reddit.com";
    Augure augure = new Augure();
    LinkPreview preview = augure.get(url);
    System.out.println(preview);
  }
}
