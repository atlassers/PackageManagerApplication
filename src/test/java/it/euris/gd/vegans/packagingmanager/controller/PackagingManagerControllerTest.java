package it.euris.gd.vegans.packagingmanager.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import it.euris.gd.vegans.packagingmanager.common.Utils;
import it.euris.gd.vegans.packagingmanager.data.dto.RequestDto;
import it.euris.gd.vegans.packagingmanager.data.dto.ResponseDto;
import it.euris.gd.vegans.packagingmanager.services.PackagingManagerService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PackagingManagerController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PackagingManagerControllerTest {

  @Autowired
  private MockMvc client;

  @MockBean
  private PackagingManagerService packagingManagerService;

  @Test
  @DisplayName("Test PM.CTRL.1 - I got 200 OK with correct data, with result")
  public void got_200_with_correct_data() throws Exception {
    RequestDto requestDto = Utils.buildRequestDto(100d, 1L, 100d, 100d);
    ResponseDto responseDto = ResponseDto.builder().itemsIds(List.of(1L)).build();
    Mockito.when(packagingManagerService.calculateMostValuablePackage(requestDto)).thenReturn(
        responseDto);

    client.perform(MockMvcRequestBuilders
            .post("/v1/package-manager/calculate-most-valuable")
            .contentType(MediaType.APPLICATION_JSON)
            .content(Utils.convertAsString(requestDto)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.itemsIds[0]").value(1L)) ;
  }


  @Test
  @DisplayName("Test PM.CTRL.2 - I got 200 OK with correct data, but no results")
  public void got_200_with_correct_data_but_no_results() throws Exception {
    RequestDto requestDto = Utils.buildRequestDto(50d, 1L, 100d, 100d);
    ResponseDto responseDto = ResponseDto.builder().itemsIds(List.of( )).build();
    Mockito.when(packagingManagerService.calculateMostValuablePackage(requestDto)).thenReturn(
        responseDto);

    client.perform(MockMvcRequestBuilders
            .post("/v1/package-manager/calculate-most-valuable")
            .contentType(MediaType.APPLICATION_JSON)
            .content(Utils.convertAsString(requestDto)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.itemsIds").isEmpty()) ;
  }
}
