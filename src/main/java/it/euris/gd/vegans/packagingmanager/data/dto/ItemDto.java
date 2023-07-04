package it.euris.gd.vegans.packagingmanager.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class for modelling ItemDto.
 *
 * @author Dario Gualtieri
 * @since 6/30/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements Comparable<ItemDto>  {

  private Long id;
  private Double weight;
  private Double price;

  @Override
  public String toString() {
    return "\n| " + this.id + " | " + this.weight + " | " + this.price + " |";
  }

  @Override
  public int compareTo(ItemDto itemDto) {
    if(this.getPrice().equals(itemDto.getPrice())){
      // reversed order
      return itemDto.getWeight().compareTo(this.getWeight());
    }else{
      // normal order
      return this.getPrice().compareTo(itemDto.getPrice());
    }
  }
}
