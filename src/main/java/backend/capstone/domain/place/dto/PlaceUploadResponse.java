package backend.capstone.domain.place.dto;

import lombok.Builder;

@Builder
public record PlaceUploadResponse(
    Long placeId,
    String placeName,
    String roadAddress,
    int orderIndex
) {

}
