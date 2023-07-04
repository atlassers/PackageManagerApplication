package it.euris.gd.vegans.packagingmanager.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.gd.vegans.packagingmanager.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The request dto, with the maximumWeight and the items to analyze.
 *
 * @author Dario Gualtieri
 * @since 6/30/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

  private Double maximumWeight;
  private List<ItemDto> items;


  @Override
  public String toString() {
    return  "> maximum weigh: " + maximumWeight + "\n" + Utils.printItemDtoList(items);
  }
}
