package it.euris.gd.vegans.packagingmanager.service;

import it.euris.gd.vegans.packagingmanager.common.Utils;
import it.euris.gd.vegans.packagingmanager.data.dto.ItemDto;
import it.euris.gd.vegans.packagingmanager.data.dto.RequestDto;
import it.euris.gd.vegans.packagingmanager.data.dto.ResponseDto;
import it.euris.gd.vegans.packagingmanager.exceptions.AllItemsExceedTheMaxPriceException;
import it.euris.gd.vegans.packagingmanager.exceptions.AllItemsExceedTheMaxWeightException;
import it.euris.gd.vegans.packagingmanager.exceptions.MaxWeightAdmittedException;
import it.euris.gd.vegans.packagingmanager.exceptions.NumberOfItemsExceededException;
import it.euris.gd.vegans.packagingmanager.services.PackagingManagerService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class GameServiceImplTest {

  @Autowired
  private PackagingManagerService packagingManagerService;

  // ============================================
  // Exceptions tests
  // ============================================
  @Test
  @DisplayName(
      "PM.SVC.Ex1: Given an acceptable payload, we discover that maximumWeight is over "
          + " the maximum admittable and we expect a MaxWeightAdmittedException")
  void calculateMostValuablePackage_triggers_MaxWeightAdmittedException() {
    RequestDto requestDto = Utils.buildRequestDto(200d, 1L, 150d, 150d);
    Assertions.assertThrows(MaxWeightAdmittedException.class
        , () -> packagingManagerService.calculateMostValuablePackage(requestDto)
    );
  }

  @Test
  @DisplayName(
      "PM.SVC.Ex2: Given an acceptable payload, we discover that number of items is over "
          + " the maximum  admittable and we expect a NumberOfItemsExceededException")
  void calculateMostValuablePackage_triggers_NumberOfItemsExceededException() {
    RequestDto requestDto = Utils.buildRequestDtoOverMaximumNumberOfItems();

    Assertions.assertThrows(NumberOfItemsExceededException.class
        , () -> packagingManagerService.calculateMostValuablePackage(requestDto)
    );
  }

  @Test
  @DisplayName(
      "PM.SVC.Ex3: Given an acceptable payload, we discover that all the items weights exceed "
          + " the maximum admittable and we expect an AllItemsExceedTheMaxWeightException")
  void calculateMostValuablePackage_triggers_AllItemsExceedTheMaxWeightException() {
    RequestDto requestDto = Utils.buildRequestDtoWIthAllItemsOverMaximumWeight();

    Assertions.assertThrows(AllItemsExceedTheMaxWeightException.class
        , () -> packagingManagerService.calculateMostValuablePackage(requestDto)
    );
  }


  @Test
  @DisplayName(
      "PM.SVC.Ex4: Given an acceptable payload, we discover that some of the items prices exceed "
          + " the maximum admittable and we expect the correct answer and no exceptions")
  void calculateMostValuablePackage_not_triggers_AllItemsExceedTheMaxPriceException() {
    RequestDto requestDto = Utils.buildRequestDtoWIthAllItemsOverMaximumPrices();

    Assertions.assertThrows(AllItemsExceedTheMaxPriceException.class
        , () -> packagingManagerService.calculateMostValuablePackage(requestDto)
    );
  }


  @Test
  @DisplayName(
      "PM.SVC.Ex5: Given an acceptable payload, we discover that some of the items weights exceed "
          + " the maximum admittable and we expect a correct answer and no exceptions")
  void calculateMostValuablePackage_not_triggers_AllItemsExceedTheMaxWeightException() {
    RequestDto requestDto = Utils.buildRequestDtoWIthAllItemsOverMaximumWeight();
    requestDto.getItems().add(new ItemDto(1L, 10d, 10d));

    Assertions.assertDoesNotThrow(() -> packagingManagerService.calculateMostValuablePackage(requestDto));
  }


  @Test
  @DisplayName(
      "PM.SVC.Ex6: Given an acceptable payload, we discover that all the items prices exceed "
          + " the maximum admittable and we expect a correct answer and no exceptions")
  void calculateMostValuablePackage_triggers_AllItemsExceedTheMaxPriceException() {
    RequestDto requestDto = Utils.buildRequestDtoWIthAllItemsOverMaximumPrices();
    requestDto.getItems().add(new ItemDto(1L, 10d, 10d));

    Assertions.assertDoesNotThrow( () -> packagingManagerService.calculateMostValuablePackage(requestDto)
    );
  }

  // ============================================
  // Use Cases tests
  // ============================================

  @Test
  @DisplayName(
      "PM.SVC.UC1: Given a specific payload for this use case,"
          + " we expect to get back the itemIds [2,7]")
  void calculateMostValuablePackage_UC1() {
    RequestDto requestDto = Utils.buildRequestDtoUC1();
    ResponseDto responseDto = packagingManagerService.calculateMostValuablePackage(requestDto);
    assertThat(List.of(2l, 7l)).isEqualTo(responseDto.getItemsIds());
  }

  @Test
  @DisplayName(
      "PM.SVC.UC2: Given a specific payload for this use case,"
          + " we expect to get back the itemIds [4]")
  void calculateMostValuablePackage_UC2() {
    RequestDto requestDto = Utils.buildRequestDtoUC2();
    ResponseDto responseDto = packagingManagerService.calculateMostValuablePackage(requestDto);
    assertThat(List.of(4l)).isEqualTo(responseDto.getItemsIds());
  }

  @Test
  @DisplayName(
      "PM.SVC.UC3: Given a specific payload for this use case,"
          + " we expect to get back an exception")
  void calculateMostValuablePackage_UC3() {
    RequestDto requestDto = Utils.buildRequestDtoUC3();

    Assertions.assertThrows(AllItemsExceedTheMaxWeightException.class
        , () -> packagingManagerService.calculateMostValuablePackage(requestDto)
    );
  }

  @Test
  @DisplayName(
      "PM.SVC.UC4: Given a specific payload for this use case,"
          + " we expect to get back the itemIds [8,9]")
  void calculateMostValuablePackage_UC4() {
    RequestDto requestDto = Utils.buildRequestDtoUC4();
    ResponseDto responseDto = packagingManagerService.calculateMostValuablePackage(requestDto);
    assertThat(List.of(8l, 9l)).isEqualTo(responseDto.getItemsIds());
  }
}