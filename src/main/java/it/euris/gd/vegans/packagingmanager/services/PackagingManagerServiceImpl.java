package it.euris.gd.vegans.packagingmanager.services;

import it.euris.gd.vegans.packagingmanager.data.constants.PackagingConstants;
import it.euris.gd.vegans.packagingmanager.data.dto.RequestDto;
import it.euris.gd.vegans.packagingmanager.data.dto.ResponseDto;
import it.euris.gd.vegans.packagingmanager.utils.PackageLogic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This class implements the {@code PackagingManagerService} methods, completing the
 * packaging business logic.
 *
 * @author Dario Gualtieri
 * @since 6/30/23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PackagingManagerServiceImpl implements PackagingManagerService {

  /**
   * This method (implementation) calculate the most valuable package, returning the combination having the maximum
   * price possible requested from the {@code RequestDto}
   *
   * @param requestDto the dto having the maximumWeight and the {@code List<Items>} among to choose.
   * @return the  with the most valuable IDs {@code List<Long>}
   */
  @Override
  public ResponseDto calculateMostValuablePackage(RequestDto requestDto){
    // First of all, check if some of the conditions are not observed.
    PackageLogic.checkGlobalConstraints(requestDto);

    // Then analyze the request and elaborate the response, if exists.
    return PackageLogic.calculateMostValuablePackage(requestDto);
  }
}
