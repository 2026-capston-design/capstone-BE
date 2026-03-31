package backend.capstone.domain.dayroute.repository;

import backend.capstone.domain.dayroute.entity.AnalysisStatus;
import backend.capstone.domain.dayroute.entity.DayRoute;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DayRouteRepository extends JpaRepository<DayRoute, Long> {

    @Query("""
            select dr
            from DayRoute dr
            where dr.user.id = :userId
              and dr.date = :date
        """)
    Optional<DayRoute> findByUserIdAndDate(@Param("userId") Long userId,
        @Param("date") LocalDate date);

    @Query("""
            select dr
            from DayRoute dr
            where dr.user.id = :userId
              and dr.date between :startDate and :endDate
            order by dr.date asc
        """)
    List<DayRoute> findByUserIdAndDateBetweenOrderByDate(@Param("userId") Long userId,
        @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("""
        select dr.id
        from DayRoute dr
        where dr.analysisNeeded = true
          and dr.analysisStatus = :status
          and (
                dr.lastAnalyzedAt is null
                or dr.lastAnalyzedAt <= :threshold
              )
        """)
    List<DayRoute> findStayAnalysisTargets(@Param("status") AnalysisStatus status,
        @Param("threshold") LocalDateTime threshold);
}
