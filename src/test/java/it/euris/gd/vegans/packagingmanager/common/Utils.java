package it.euris.gd.vegans.packagingmanager.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.euris.gd.vegans.packagingmanager.data.constants.PackagingConstants;
import it.euris.gd.vegans.packagingmanager.data.dto.ItemDto;
import it.euris.gd.vegans.packagingmanager.data.dto.RequestDto;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;

public class Utils {

  public static String convertAsString(Object gameStatusDto) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(gameStatusDto);
  }


  public static RequestDto buildRequestDto(Double maximumWeight, Long itemId, Double itemWeight,
      Double itemPrice) {
    List<ItemDto> items = new ArrayList<>();
    items.add(new ItemDto(itemId, itemWeight, itemPrice));

    return RequestDto.builder().maximumWeight(maximumWeight).items(items).build();
  }

  public static RequestDto buildRequestDtoOverMaximumNumberOfItems() {
    List<ItemDto> items = new ArrayList<>();

    for (Long i = 0L; i <= PackagingConstants.MAX_NUMBER_OF_ITEMS_ADMITTED; i++) {
      items.add(new ItemDto(i, RandomUtils.nextDouble(0, 100), RandomUtils.nextDouble(0, 100)));
    }

    return RequestDto.builder().maximumWeight(PackagingConstants.MAX_WEIGHT_ADMITTED).items(items)
        .build();
  }


  public static RequestDto buildRequestDtoWIthAllItemsOverMaximumPrices() {
    List<ItemDto> items = new ArrayList<>();

    // We create an array with the maximum dimension possible minus one, for testing other cases later
    // without duplicating this code
    for (Long i = 0L; i < PackagingConstants.MAX_NUMBER_OF_ITEMS_ADMITTED - 1; i++) {
      items.add(new ItemDto(i, RandomUtils.nextDouble(0, PackagingConstants.MAX_WEIGHT_ADMITTED),
          RandomUtils.nextDouble(PackagingConstants.MAX_PRICE_ADMITTED + 1,
              PackagingConstants.MAX_PRICE_ADMITTED + 100)));
    }

    return RequestDto.builder().maximumWeight(PackagingConstants.MAX_WEIGHT_ADMITTED).items(items)
        .build();
  }

  public static RequestDto buildRequestDtoWIthAllItemsOverMaximumWeight() {
    List<ItemDto> items = new ArrayList<>();

    // We create an array with the maximum dimension possible minus one, for testing other cases later
    // without duplicating this code
    for (Long i = 0L; i < PackagingConstants.MAX_NUMBER_OF_ITEMS_ADMITTED - 1; i++) {
      items.add(new ItemDto(i, RandomUtils.nextDouble(PackagingConstants.MAX_WEIGHT_ADMITTED + 1,
          PackagingConstants.MAX_WEIGHT_ADMITTED + 100),
          RandomUtils.nextDouble(0, PackagingConstants.MAX_PRICE_ADMITTED)));
    }

    return RequestDto.builder().maximumWeight(PackagingConstants.MAX_WEIGHT_ADMITTED).items(items)
        .build();
  }


  public static RequestDto buildRequestDtoUC1() {
    List<ItemDto> items = new ArrayList<>();
    items.add(new ItemDto(1l, 85.31d, 29d));
    items.add(new ItemDto(2l, 14.55d, 74d));
    items.add(new ItemDto(3l, 3.98d, 16d));
    items.add(new ItemDto(4l, 26.24d, 55d));
    items.add(new ItemDto(5l, 63.69d, 52d));
    items.add(new ItemDto(6l, 76.25d, 75d));
    items.add(new ItemDto(7l, 60.02d, 74d));
    items.add(new ItemDto(8l, 93.18d, 35d));
    items.add(new ItemDto(9l, 89.95d, 78d));

    return RequestDto.builder().maximumWeight(75d).items(items).build();
  }

  public static RequestDto buildRequestDtoUC2() {
    List<ItemDto> items = new ArrayList<>();
    items.add(new ItemDto(1l, 53.38d, 45d));
    items.add(new ItemDto(2l, 88.62d, 98d));
    items.add(new ItemDto(3l, 78.48d, 3d));
    items.add(new ItemDto(4l, 72.30d, 76d));
    items.add(new ItemDto(5l, 30.18d, 9d));
    items.add(new ItemDto(6l, 46.34d, 48d));

    return RequestDto.builder().maximumWeight(80d).items(items).build();
  }

  public static RequestDto buildRequestDtoUC3() {
    List<ItemDto> items = new ArrayList<>();
    items.add(new ItemDto(1l, 15.30d, 34d));

    return RequestDto.builder().maximumWeight(8d).items(items).build();
  }

  public static RequestDto buildRequestDtoUC4() {
    List<ItemDto> items = new ArrayList<>();
    items.add(new ItemDto(1l, 90.72d, 13d));
    items.add(new ItemDto(2l, 33.80d, 40d));
    items.add(new ItemDto(3l, 43.15d, 10d));
    items.add(new ItemDto(4l, 37.97d, 16d));
    items.add(new ItemDto(5l, 46.81d, 36d));
    items.add(new ItemDto(6l, 48.77d, 79d));
    items.add(new ItemDto(7l, 81.80d, 45d));
    items.add(new ItemDto(8l, 19.36d, 79d));
    items.add(new ItemDto(9l, 6.76d, 64d));

    return RequestDto.builder().maximumWeight(56d).items(items).build();
  }

  public static RequestDto buildRequestDto(Double maximumWeight, List<ItemDto> items) {
    return RequestDto.builder().maximumWeight(maximumWeight).items(items).build();
  }

}
