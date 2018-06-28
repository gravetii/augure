package com.gravetii.augure.util;

import org.apache.commons.lang3.StringUtils;

public class Utils {
  public static String getFullUrl(String url) {
    url = url.trim();
    url = deCapitalize(url);
    int index = url.indexOf(" ");

    if (index > 0)
      url = url.substring(0, index);

    if (!(url.startsWith("http://") || (url.startsWith("https://")))) {
      url = "http://" + url;
    }

    return url;
  }

  public static String deCapitalize(String str) {
    if (StringUtils.isEmpty(str)) {
      return str;
    }

    char c[] = str.toCharArray();
    c[0] = Character.toLowerCase(c[0]);
    return new String(c);
  }
}
