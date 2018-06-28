package com.gravetii.augure.main;

import com.gravetii.augure.extractor.service.UriExtractorService;
import com.gravetii.augure.pojo.LinkPreview;
import com.gravetii.augure.pojo.UriDocument;
import com.gravetii.augure.util.Utils;

public class Augure {
  private UriExtractorService service;

  public Augure() {
    this.service = new UriExtractorService();
  }

  public LinkPreview get(String url) throws Exception {
    url = Utils.getFullUrl(url);
    UriDocument document = new UriDocument(url);
    return service.get(document);
  }

  public static void main(String[] args) throws Exception {
    String url = "http://reddit.com";
    Augure augure = new Augure();
    LinkPreview preview = augure.get(url);
    System.out.println(preview);
  }
}
