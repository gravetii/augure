package io.github.gravetii;

import com.google.common.base.Preconditions;
import io.github.gravetii.service.UriExtractorService;
import io.github.gravetii.pojo.LinkPreview;
import io.github.gravetii.pojo.UriDocument;
import io.github.gravetii.util.Utils;
import io.github.gravetii.util.exception.AugureException;
import javax.annotation.concurrent.ThreadSafe;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ThreadSafe
public class Augure {
  private static final Logger logger = LoggerFactory.getLogger(Augure.class.getCanonicalName());

  private UriExtractorService service;

  public Augure() {
    this.service = new UriExtractorService();
    logger.info("Successfully initialised Augure");
  }

  /**
   * This method is used to get the {@link LinkPreview} for a given URL.
   * @param url the URL for which to get the preview.
   * @return The {@link LinkPreview} of the supplied URL.
   * @throws Exception
   */
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
