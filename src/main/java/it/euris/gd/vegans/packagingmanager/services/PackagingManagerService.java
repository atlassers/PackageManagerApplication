package it.euris.gd.vegans.packagingmanager.services;

import it.euris.gd.vegans.packagingmanager.data.dto.RequestDto;
import it.euris.gd.vegans.packagingmanager.data.dto.ResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This interface manages the method for managing the packaging business logic.
 *
 * @author Dario Gualtieri
 * @since 6/30/23
 */
public interface PackagingManagerService {

  /**
   * This method (interface) calculate the most valuable package, returning the combination having the maximum
   * price possible requested from the {@code RequestDto}
   *
   * @param requestDto the dto having the maximumWeight and the {@code List<Items>} among to choose.
   * @return the  with the most valuable IDs {@code List<Long>}
   */
  public ResponseDto calculateMostValuablePackage(RequestDto requestDto);
}
