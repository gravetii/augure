package com.gravetii.augure.extractor;

import com.gravetii.augure.pojo.LinkPreview;
import com.gravetii.augure.pojo.UriDocument;

/**
 * Created by sandeepd on 28/06/18.
 */
public interface IUriExtractor {
  LinkPreview getMetaInfo(UriDocument document);
}
