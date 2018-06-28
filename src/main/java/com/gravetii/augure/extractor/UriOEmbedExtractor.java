package com.gravetii.augure.extractor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gravetii.augure.pojo.LinkPreview;
import com.gravetii.augure.pojo.OEmbedResponse;
import com.gravetii.augure.pojo.UriDocument;
import com.gravetii.augure.util.http.HttpClient;
import com.gravetii.augure.util.http.HttpClientRequest;

public class UriOEmbedExtractor implements IUriExtractor
{
  private static final ObjectMapper mapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Override
  public LinkPreview getMetaInfo(UriDocument document) {
    if (document == null) {
      return null;
    }

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
      return null;
    }

  }

  private String fetchOEmbedData(String oEmbedUrl) throws Exception {
    if (oEmbedUrl == null) {
      return null;
    }

    HttpClientRequest.Builder builder = HttpClientRequest.builder().setUrl(oEmbedUrl)
        .setSsl(false).setContentType("application/json").setTimeout(5000);
    String data = HttpClient.getData(builder.build());
    return data;
  }
}