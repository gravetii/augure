package com.gravetii.augure.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

  public static String readData(InputStream inputstream) throws IOException
  {
    try
    {
      InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
      BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
      StringBuilder builder = new StringBuilder();
      String response;
      while ((response = bufferedreader.readLine()) != null)
      {
        builder.append(response);
      }

      return builder.toString();
    }
    finally
    {
      inputstream.close();
    }
  }
}
