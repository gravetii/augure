package io.github.gravetii.extractor;

import io.github.gravetii.pojo.LinkPreview;
import io.github.gravetii.pojo.UriDocument;

public interface IUriExtractor {
  LinkPreview getMetaInfo(UriDocument document);
}
