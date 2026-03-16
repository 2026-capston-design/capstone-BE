package backend.capstone.domain.place.service;

import backend.capstone.domain.dayroute.entity.DayRoute;
import backend.capstone.domain.place.dto.PlaceAddRequest;
import backend.capstone.domain.place.dto.PlaceAddResponse;
import backend.capstone.domain.place.dto.PlaceReorderRequest;
import backend.capstone.domain.place.dto.PlaceUpdateRequest;
import backend.capstone.domain.place.dto.PlaceUpdateResponse;
import backend.capstone.domain.place.entity.Place;
import backend.capstone.domain.place.exception.PlaceErrorCode;
import backend.capstone.domain.place.mapper.PlaceMapper;
import backend.capstone.domain.place.repository.PlaceRepository;
import backend.capstone.global.exception.BusinessException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public PlaceAddResponse addPlace(DayRoute dayRoute, PlaceAddRequest request) {
        int maxOrder = placeRepository.findMaxOrderIdxByRoute(dayRoute);
        int newOrder = maxOrder + 1;

        Place savedPlace = placeRepository.save(PlaceMapper.toEntity(dayRoute, request, newOrder));
        return PlaceMapper.toPlaceUploadResponse(savedPlace);
    }

    @Transactional(readOnly = true)
    public List<Place> getPlacesByDayRoute(DayRoute dayRoute) {
        return placeRepository.findByDayRouteOrderByOrderIndex(dayRoute);
    }

    @Transactional
    public PlaceUpdateResponse updatePlace(DayRoute dayRoute, Long placeId,
        PlaceUpdateRequest request) {
        Place place = placeRepository.findByIdAndDayRoute(placeId, dayRoute)
            .orElseThrow(() -> new BusinessException(PlaceErrorCode.PLACE_NOT_FOUND));

        place.update(request.roadAddress(), request.placeName());

        return PlaceMapper.toPlaceUpdateResponse(place);
    }

    @Transactional
    public void deletePlace(DayRoute dayRoute, Long placeId) {
        Place place = placeRepository.findByIdAndDayRoute(placeId, dayRoute)
            .orElseThrow(() -> new BusinessException(PlaceErrorCode.PLACE_NOT_FOUND));

        int deletedOrderIndex = place.getOrderIndex();
        placeRepository.delete(place);
        placeRepository.decrementOrderIndexesGreaterThan(dayRoute, deletedOrderIndex);
    }

    @Transactional
    public void reorderPlaces(DayRoute dayRoute, PlaceReorderRequest request) {
        List<Place> places = placeRepository.findByDayRouteOrderByOrderIndex(dayRoute);
        List<Long> reorderedPlaceIds = request.placeIds();

        validateReorderRequest(places, reorderedPlaceIds);

        placeRepository.negateOrderIndexesByDayRoute(dayRoute);
        for (int i = 0; i < reorderedPlaceIds.size(); i++) {
            int updated = placeRepository.updateOrderIndexByIdAndDayRoute(
                reorderedPlaceIds.get(i), dayRoute, i + 1);
            if (updated == 0) {
                throw new BusinessException(PlaceErrorCode.PLACE_NOT_FOUND);
            }
        }
    }

    private void validateReorderRequest(List<Place> places, List<Long> reorderedPlaceIds) {
        if (reorderedPlaceIds == null || places.size() != reorderedPlaceIds.size()) {
            throw new BusinessException(PlaceErrorCode.INVALID_PLACE_REORDER_REQUEST);
        }

        Set<Long> existingIds = new HashSet<>(places.stream()
            .map(Place::getId)
            .toList());

        Set<Long> requestedIds = new HashSet<>(reorderedPlaceIds);
        if (requestedIds.size() != reorderedPlaceIds.size() || !existingIds.equals(requestedIds)) {
            throw new BusinessException(PlaceErrorCode.INVALID_PLACE_REORDER_REQUEST);
        }
    }

}
