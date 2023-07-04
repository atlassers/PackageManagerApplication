package it.euris.gd.vegans.packagingmanager.controller;

import it.euris.gd.vegans.packagingmanager.data.dto.RequestDto;
import it.euris.gd.vegans.packagingmanager.data.dto.ResponseDto;
import it.euris.gd.vegans.packagingmanager.services.PackagingManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PackagingManagerController.
 *
 * This controller exposes the method necessary to calculate the most valuable package, based on
 * a list of Items (all of them admittable in accordance with the test constraints).
 *  *
 * @author Gualtieri Dario
 * @since 2023-06-30
 * @version v1
 */

@RestController(value = "PackagingManagerController")
@RequestMapping("/v1/package-manager")
@RequiredArgsConstructor
public class PackagingManagerController {
  private final PackagingManagerService packagingManagerService;

  /**
   * This method:
   * <li> verifies if all the DTO constraints are violeted
   * <li> calculates the most valuable package
   * <li> returns a ResponseDto with a {@code List<Integer>} containing the items ID
   * @param requestDto
   * @return a ResponseDto with a {@code List<Integer>} containing the items ID
   */
  @PostMapping(path = "calculate-most-valuable")
  public ResponseEntity<ResponseDto> calculateMostValuable(@RequestBody final RequestDto requestDto) {
    ResponseDto responseDto = packagingManagerService.calculateMostValuablePackage(requestDto);
    return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
  }

}
