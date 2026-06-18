package frontend_traffic.models;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;
import frontend_traffic.models.common_type_entity.Traffic_level;
@Entity
@Getter
@Setter
public class traffic_speed_entity extends timestamp_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "traffic_speed_id")
    private UUID trafficSpeedId;

    // Tốc độ hiện tại
    @Column(name = "current_speed")
    private Double currentSpeed;

    // Mật độ giao thông
    @Enumerated(EnumType.STRING)
    @Column(name = "traffic_level")
    private Traffic_level trafficLevel;

    // Tốc độ lý tưởng khi đường không đông
    @Column(name = "free_flow_speed")
    private Double freeFlowSpeed;

    // Thời gian đi hết đoạn đường
    @Column(name = "current_travel_time")
    private Double currentTravelTime;

    // Nếu đường thông thoáng
    @Column(name = "free_flow_travel_time")
    private Double freeFlowTravelTime;

    // Độ tin cậy
    @Column(name = "confidence")
    private Double confidence;

    // nhóm đường
    @Column(name = "frc")
    private String frc;

    // Đường bị đóng không
    @Column(name = "road_closure")
    private Boolean roadClosure;

    @ManyToOne()
    @JoinColumn(name = "traffic_id")
    private traffic_info_entity trafficSpeeds;
}
