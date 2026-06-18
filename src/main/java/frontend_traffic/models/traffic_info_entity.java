package frontend_traffic.models;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class traffic_info_entity extends timestamp_entity {
    @Id
    @Column(name = "traffic_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID trafficId;

    @Column(name = "latitude", columnDefinition = "DOUBLE PRECISION", nullable = true, unique = true)
    private Double lat;
    // columnDefinition(định nghĩa cột): dùng để ép kiểu cột

    @Column(name = "longitude", columnDefinition = "DOUBLE PRECISION", nullable = true, unique = true)
    private Double lng;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "trafficSpeeds", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<traffic_speed_entity> traffic_speed;

    @OneToMany(mappedBy = "trafficAqis", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<traffic_aqi_entity> traffic_aqi;
}
