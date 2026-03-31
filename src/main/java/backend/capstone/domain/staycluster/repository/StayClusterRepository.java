package backend.capstone.domain.staycluster.repository;

import backend.capstone.domain.dayroute.entity.DayRoute;
import backend.capstone.domain.staycluster.entity.StayCluster;
import backend.capstone.domain.staycluster.entity.StayClusterStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StayClusterRepository extends JpaRepository<StayCluster, Long> {

    Optional<StayCluster> findByDayRouteAndStatus(DayRoute dayRoute, StayClusterStatus status);
}
