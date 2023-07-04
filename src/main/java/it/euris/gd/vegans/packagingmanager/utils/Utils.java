package it.euris.gd.vegans.packagingmanager.utils;

import it.euris.gd.vegans.packagingmanager.data.dto.ItemDto;
import java.util.List;

/**
 * Common Utils for the application.
 *
 * @author Dario Gualtieri
 * @since 6/30/23
 */
public class Utils {

  public static String printItemDtoList(List<ItemDto> itemDtoList) {
    StringBuilder sb = new StringBuilder();
    itemDtoList.stream().forEach(itemDto -> sb.append(itemDto.toString()));
    return sb.toString();
  }

  public static String print(List<?> items) {
    StringBuilder sb = new StringBuilder();
    sb.append("| ");
    items.stream().forEach(value -> sb.append(value + " | "));
    return sb.toString();
  }
}
