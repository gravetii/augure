package com.gravetii.commons.extractor;

import com.gravetii.commons.pojo.LinkPreview;
import com.gravetii.commons.pojo.UriDocument;

/**
 * Created by sandeepd on 28/06/18.
 */
public interface IUriExtractor {
  LinkPreview getMetaInfo(UriDocument document);
}
