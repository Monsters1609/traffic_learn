package frontend_traffic.dto;

import java.time.Instant;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
public class traffic_chart_dto {
    private String address;
    private List<TrafficPoint> data;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrafficPoint {
        private Double currentSpeed;
        private Instant createdAt;
    }
}