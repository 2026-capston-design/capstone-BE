package backend.capstone.domain.dayroute.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

//TODO: 장소, 메모 추가
@Builder
public record DayRouteDetailResponse(
    Long dayRouteId,
    LocalDate date,
    double totalDistance,
    boolean isBookmarked,
    List<GpsPointListResponse> gpsPoints
) {

    public record GpsPointListResponse(
        LocalDateTime recordedAt,
        double latitude,
        double longitude
    ) {

    }

}
