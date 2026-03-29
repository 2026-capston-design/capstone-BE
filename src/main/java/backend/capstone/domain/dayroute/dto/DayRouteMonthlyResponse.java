package backend.capstone.domain.dayroute.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record DayRouteMonthlyResponse(
    List<DayRouteItem> dayRoutes
) {

    @Builder
    public record DayRouteItem(
        LocalDate date,
        boolean hasGpsPoints,
        boolean hasManualData,
        boolean isBookmarked
    ) {

    }
}
