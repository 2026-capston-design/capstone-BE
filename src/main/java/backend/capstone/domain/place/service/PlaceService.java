package backend.capstone.domain.place.service;

import backend.capstone.domain.dayroute.entity.DayRoute;
import backend.capstone.domain.place.dto.PlaceUploadRequest;
import backend.capstone.domain.place.dto.PlaceUploadResponse;
import backend.capstone.domain.place.entity.Place;
import backend.capstone.domain.place.mapper.PlaceMapper;
import backend.capstone.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public PlaceUploadResponse uploadPlace(DayRoute dayRoute, PlaceUploadRequest request) {
        int maxOrder = placeRepository.findMaxOrderIdxByRoute(dayRoute);
        int newOrder = maxOrder + 1;

        Place savedPlace = placeRepository.save(PlaceMapper.toEntity(dayRoute, request, newOrder));
        return PlaceMapper.toPlaceUploadResponse(savedPlace);
    }

}
