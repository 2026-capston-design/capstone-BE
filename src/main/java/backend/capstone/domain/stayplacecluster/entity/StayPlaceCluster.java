package backend.capstone.domain.stayplacecluster.entity;

import backend.capstone.domain.dayroute.entity.DayRoute;
import backend.capstone.domain.gpspoint.entity.GpsPoint;
import backend.capstone.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
public class StayPlaceCluster extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "stay_place_cluster_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_route_id")
    private DayRoute dayRoute;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_point_id")
    private GpsPoint startPoint;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_point_id")
    private GpsPoint endPoint;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private double centerLatitude;

    private double centerLongitude;

    private int pointCount;

    private StayPlaceClusterState state;

}
