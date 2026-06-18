package frontend_traffic.models;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class traffic_aqi_entity extends timestamp_entity {
    @Id
    @Column(name = "traffic_aqi_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID trafficAqiId;

    // chất gây ảnh hưởng tới chất lượng không khí nhiều nhất hiện tại
    @Column(name = "dominentpol")
    private String dominentpol;

    // Điểm sương
    @Column(name = "dew")
    private int dew;

    // độ ẩm
    @Column(name = "h")
    private int h;

    // Ozone tầng mặt đất
    @Column(name = "o3")
    private int o3;

    // áp suất khí quyển
    @Column(name = "p")
    private float p;

    // Bụi ≤ 10μm
    @Column(name = "pm10")
    private int pm10;

    // Bụi mịn ≤ 2.5μm, nguy hiểm nhất với sức khỏe
    @Column(name = "pm25")
    private int pm25;

    // nhiệt độ
    @Column(name = "t")
    private int t;

    // tốc độ gió trung bình
    @Column(name = "w")
    private int w;

    // tốc độ gió giật mạnh nhất
    @Column(name = "wg")
    private int wg;

    @ManyToOne()
    @JoinColumn(name = "traffic_id")
    private traffic_info_entity trafficAqis;
}
