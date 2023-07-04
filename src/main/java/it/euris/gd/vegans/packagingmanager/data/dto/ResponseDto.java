package it.euris.gd.vegans.packagingmanager.data.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The response dto, with the itemsIds.
 *
 * @author Dario Gualtieri
 * @since 6/30/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
  List<Long> itemsIds;
}
