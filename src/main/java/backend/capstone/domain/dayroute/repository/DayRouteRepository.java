package backend.capstone.domain.dayroute.repository;

import backend.capstone.domain.dayroute.entity.DayRoute;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRouteRepository extends JpaRepository<DayRoute, Long> {

    Optional<DayRoute> findByUserIdAndDate(Long userId, LocalDate date);
}
