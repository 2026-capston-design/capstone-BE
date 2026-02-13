package backend.capstone.domain.dayroute.gpspoint.repository;

import backend.capstone.domain.dayroute.gpspoint.entity.GpsPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpsPointRepository extends JpaRepository<GpsPoint, Long> {

}
