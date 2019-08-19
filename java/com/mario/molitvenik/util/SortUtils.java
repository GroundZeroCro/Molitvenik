package com.mario.molitvenik.util;

import com.mario.molitvenik.data.common.Prayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortUtils {

  public static List<Prayer> sortList(Sort sort, List<Prayer> unsortedPrayers) {
    List<Prayer> sortedPrayers = new ArrayList<>(unsortedPrayers);
    Collections.sort(sortedPrayers, sort == Sort.TITLE ? SortUtils::sortByTitle : SortUtils::sortByLength);
    return sortedPrayers;
  }

  private static int sortByLength(Prayer p1, Prayer p2) {
    return p1.getText().length() - p2.getText().length();
  }

  private static int sortByTitle(Prayer p1, Prayer p2) {
    return p1.getTitle().compareTo(p2.getTitle());
  }
}
