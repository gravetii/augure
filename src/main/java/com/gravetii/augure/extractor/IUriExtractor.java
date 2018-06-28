package com.gravetii.augure.extractor;

import com.gravetii.augure.pojo.LinkPreview;
import com.gravetii.augure.pojo.UriDocument;

public interface IUriExtractor {
  LinkPreview getMetaInfo(UriDocument document);
}
