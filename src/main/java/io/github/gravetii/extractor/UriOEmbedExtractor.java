package io.github.gravetii.extractor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.gravetii.pojo.LinkPreview;
import io.github.gravetii.pojo.OEmbedResponse;
import io.github.gravetii.pojo.UriDocument;
import io.github.gravetii.util.http.HttpClient;
import io.github.gravetii.util.http.HttpClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UriOEmbedExtractor implements IUriExtractor
{
  private static final Logger logger = LoggerFactory
      .getLogger(UriOEmbedExtractor.class.getCanonicalName());

  private static final ObjectMapper mapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Override
  public LinkPreview getMetaInfo(UriDocument document) {
    String oEmbedUrl = document.getMetaInfo("link[type=application/json+oembed]", "href");
    if (oEmbedUrl == null) {
      return null;
    }

    try {
      String data = fetchOEmbedData(oEmbedUrl);
      OEmbedResponse response = mapper.readValue(data, OEmbedResponse.class);
      LinkPreview preview = new LinkPreview(document.getUrl());
      preview.setTitle(response.getTitle())
          .setThumbnailUrl(response.getThumbnailUrl());
      return preview;
    }
    catch (Exception e) {
      logger.warn("Error while reading oEmbed data for url {}", oEmbedUrl, e);
    }

    return null;
  }

  private String fetchOEmbedData(String oEmbedUrl) throws Exception {
    HttpClientRequest.Builder builder = HttpClientRequest.builder().setUrl(oEmbedUrl)
        .setSsl(false).setContentType("application/json").setTimeout(5000);
    return HttpClient.getData(builder.build());
  }
}