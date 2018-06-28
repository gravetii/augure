package com.gravetii.augure.pojo;

import com.google.common.net.InternetDomainName;
import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by sandeepd on 28/06/18.
 */
public class UriDocument {
  private static final String USER_AGENT = "Mozilla/5.0";
  private static final String REFERRER = "http://www.google.com";

  private String url;
  private Document document;

  public UriDocument(String url) throws Exception {
    this.url = url;
    this.document = Jsoup
        .connect(url)
        .userAgent(USER_AGENT)
        .referrer(REFERRER)
        .timeout(12000)
        .followRedirects(true)
        .get();
  }

  public String getUrl() {
    return url;
  }

  public String fetchLocation() {
    return document.location();
  }

  public String fetchTitle() {
    return document.title();
  }

  public String fetchProviderName() {
    String url = document.location();
    String providerName = url;

    try {
      URI uri = new URI(url);
      String domain = uri.getHost();
      providerName = domain.startsWith("www.") ? domain.substring(4) : domain;
    }
    catch (Exception e) {
      try {
        if (url.endsWith("/")) {
          url = url.substring(0, url.length() - 1);
        }
        if (url.startsWith("http://")) {
          url = url.substring(7, url.length());
        }
        if (url.startsWith("https://")) {
          url = url.substring(8, url.length());
        }

        providerName = InternetDomainName.from(url).topPrivateDomain().toString();
      } catch (Exception e1) {
        System.err.println("Error in URI decoding - " + url);
      }

      System.err.println("Error in url " + url);
    }

    if (providerName != null && providerName.endsWith(".sg")) {
      providerName = providerName.substring(0, providerName.length() - 3);
    }

    if (providerName != null && providerName.startsWith("sg.")) {
      providerName = providerName.substring(3, providerName.length());
    }

    return providerName;
  }

  public String getMetaInfo(String tag, String attr) {
    return getMetaInfo(Collections.singletonList(tag), attr);
  }

  public String getMetaInfo(List<String> tags, String attr) {
    Elements elements = null;
    Iterator<String> itr = tags.iterator();

    while (itr.hasNext()) {
      if (elements == null) {
        elements = document.head().select(itr.next());
      }
      else {
        elements = elements.select(itr.next());
      }
    }

    return getAttribute(elements, attr);
  }

  private String getAttribute(Elements elements, String attr) {
    String content = null;

    try {
      content = elements.attr(attr);
    }
    catch (Exception e) {
    }

    if (StringUtils.isEmpty(content)) {
      content = null;
    }

    return content;
  }

  @Override
  public String toString() {
    return "UriDocument{" +
        ", url='" + url + '\'' +
        ", document=" + document +
        '}';
  }
}
