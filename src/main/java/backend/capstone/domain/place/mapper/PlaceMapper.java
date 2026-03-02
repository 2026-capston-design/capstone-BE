package backend.capstone.domain.place.mapper;

import backend.capstone.domain.dayroute.entity.DayRoute;
import backend.capstone.domain.place.dto.PlaceUploadRequest;
import backend.capstone.domain.place.dto.PlaceUploadResponse;
import backend.capstone.domain.place.entity.Place;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PlaceMapper {

    public static Place toEntity(DayRoute dayRoute, PlaceUploadRequest request, int orderIndex) {
        return Place.builder()
            .dayRoute(dayRoute)
            .roadAddress(request.roadAddress())
            .name(request.placeName())
            .orderIndex(orderIndex)
            .build();
    }

    public static PlaceUploadResponse toPlaceUploadResponse(Place place) {
        return PlaceUploadResponse.builder()
            .placeId(place.getId())
            .placeName(place.getName())
            .roadAddress(place.getRoadAddress())
            .orderIndex(place.getOrderIndex())
            .build();
    }

}
