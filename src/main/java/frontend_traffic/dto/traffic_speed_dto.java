package frontend_traffic.dto;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class traffic_speed_dto {
    // ID
    private UUID trafficSpeedId;

    // Nhóm đường
    @NotBlank
    private String frc;

    // Tốc độ hiện tại
    @NotNull
    private Double currentSpeed;

    // Tốc độ lý tưởng khi đường không đông
    @NotNull
    private Double freeFlowSpeed;

    // Thời gian đi hết đoạn đường
    @NotNull
    private Double currentTravelTime;

    // Nếu đường thông thoáng
    @NotNull
    private Double freeFlowTravelTime;

    // Độ tin cậy
    @NotNull
    private Double confidence;

    // Đường bị đóng không (true: bị đóng / false: không đóng)
    @NotNull
    private boolean roadClosure;

    // ---------- GET ------- (Thủ công)
    public boolean getRoadClosure() {
        return roadClosure;
    }

    // ---------- SET ------- (Thủ công)
    public void setRoadClosure(boolean roadClosure) {
        this.roadClosure = roadClosure;
    }
    // note: trong trường hợp thư viện lombok bị lỗi thì cần phải GET/SET thủ công
}
