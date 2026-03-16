package backend.capstone.domain.place.dto;

import java.util.List;

public record PlaceReorderRequest(
    List<Long> placeIds
) {

}
