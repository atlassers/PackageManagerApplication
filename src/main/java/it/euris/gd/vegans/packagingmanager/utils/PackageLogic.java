package it.euris.gd.vegans.packagingmanager.utils;

import it.euris.gd.vegans.packagingmanager.data.constants.PackagingConstants;
import it.euris.gd.vegans.packagingmanager.data.dto.ItemDto;
import it.euris.gd.vegans.packagingmanager.data.dto.RequestDto;
import it.euris.gd.vegans.packagingmanager.data.dto.ResponseDto;
import it.euris.gd.vegans.packagingmanager.exceptions.AllItemsExceedTheMaxPriceException;
import it.euris.gd.vegans.packagingmanager.exceptions.AllItemsExceedTheMaxWeightException;
import it.euris.gd.vegans.packagingmanager.exceptions.MaxWeightAdmittedException;
import it.euris.gd.vegans.packagingmanager.exceptions.NumberOfItemsExceededException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for the packaging logic.
 *
 * @author Dario Gualtieri
 * @since 6/30/23
 */
@Slf4j
public class PackageLogic {

  /**
   * This method checks if the constraints are observed. If not, launch the corresponding exception
   *
   * @param requestDto the dto to check.
   */
  public static void checkGlobalConstraints(RequestDto requestDto) {
    // Verify if the maximum weight received is bigger than the maximum possible admitted
    if (requestDto.getMaximumWeight() > PackagingConstants.MAX_WEIGHT_ADMITTED) {
      // If is so -> MaxWeightAdmittedException
      throw new MaxWeightAdmittedException(PackagingConstants.MAX_WEIGHT_ADMITTED,
          requestDto.getMaximumWeight());
    }

    // Verify if the maximum number of items received is bigger than the maximum possible admitted
    if (requestDto.getItems().size() > PackagingConstants.MAX_NUMBER_OF_ITEMS_ADMITTED) {
      // If is so -> NumberOfItemsExceededException
      throw new NumberOfItemsExceededException(PackagingConstants.MAX_NUMBER_OF_ITEMS_ADMITTED,
          requestDto.getItems().size());
    }

    // Verify if all the items weights received are over the maximum admittable.
    boolean allItemsExceedTheMaxWeight = requestDto.getItems().stream()
        .allMatch(itemDto -> itemDto.getWeight() > requestDto.getMaximumWeight());
    if (allItemsExceedTheMaxWeight) {
      // If is so -> AllItemsExceedTheMaxWeightException
      throw new AllItemsExceedTheMaxWeightException(requestDto.getMaximumWeight());
    }

    // Verify if all the items prices received are over the maximum admittable.
    boolean allItemsExceedTheMaxPrice = requestDto.getItems().stream()
        .allMatch(itemDto -> itemDto.getPrice() > PackagingConstants.MAX_PRICE_ADMITTED);
    if (allItemsExceedTheMaxPrice) {
      // If is so -> AllItemsExceedTheMaxPriceException
      throw new AllItemsExceedTheMaxPriceException(PackagingConstants.MAX_PRICE_ADMITTED);
    }
  }

  /**
   * This method calculates the most valuable packaging, according to the constraints specified in
   * the requisites.
   *
   * @param requestDto the starting data.
   * @return a {@code ResponseDto} containing the IDs list
   */
  public static ResponseDto calculateMostValuablePackage(RequestDto requestDto) {
    log.info("Starting dto: \n\n{}", requestDto);

    // First filter the items that are over the constraints, and sort them DESC by the price.
    // In this way, the item I need are in the first position, ordered by price
    List<ItemDto> sortedItemDtos = requestDto.getItems().stream().filter(
            itemDto -> itemDto.getWeight() <= requestDto.getMaximumWeight()
                && itemDto.getPrice() <= PackagingConstants.MAX_PRICE_ADMITTED)
//        .sorted(Comparator.comparingDouble(ItemDto::getPrice).reversed())
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
    log.info("\nSorted items: \n\n{}", Utils.printItemDtoList(sortedItemDtos));
//
//    List<ItemDto> toRemove = new ArrayList<>();
//    ItemDto cur = null;
//    for (ItemDto itemDto : sortedItemDtos) {
//      if (cur == null) {
//        cur = itemDto;
//      } else if (cur.getPrice().equals(itemDto.getPrice())) {
//        if (cur.getWeight() > itemDto.getWeight()) {
//          toRemove.add(cur);
//          cur = itemDto;
//        } else {
//          toRemove.add(itemDto);
//        }
//      }
//    }
//    sortedItemDtos.removeAll(toRemove);
//    log.info("\nSorted items: \n\n{}", Utils.printItemDtoList(sortedItemDtos));

    Double price = 0D;
    Double weight = 0D;
    List<Long> ids = new ArrayList<>();
    for (ItemDto dto : sortedItemDtos) {
      // for the new dto, if its weight plus the global weight is over the max admittable, exit the cycle
      if (weight + dto.getWeight() > requestDto.getMaximumWeight()) {
        continue;
      }
      weight += dto.getWeight();
      price += dto.getPrice();
      ids.add(dto.getId());
    }
    log.info("\nFinal items: \n\n{}", Utils.print(ids));

    return new ResponseDto(ids);
  }

}
