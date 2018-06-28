package com.gravetii.commons.extractor.service;

import com.gravetii.commons.extractor.IUriExtractor;
import com.gravetii.commons.pojo.LinkPreview;
import java.util.ArrayList;
import java.util.List;

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
}
