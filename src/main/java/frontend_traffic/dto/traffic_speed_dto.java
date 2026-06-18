package frontend_traffic.dto;

import java.util.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class traffic_speed_dto {
    // ID
    private UUID trafficSpeedId;

    // Nhóm đường
    @NotBlank
    private String frc;

    // Tốc độ hiện tại
    @NotBlank
    private Double currentSpeed;

    // Tốc độ lý tưởng khi đường không đông
    @NotBlank
    private Double freeFlowSpeed;

    // Thời gian đi hết đoạn đường
    @NotBlank
    private Double currentTravelTime;

    // Nếu đường thông thoáng
    @NotBlank
    private Double freeFlowTravelTime;

    // Độ tin cậy
    @NotBlank
    private Double confidence;

    // Đường bị đóng không (true: bị đóng / false: không đóng)
    @NotBlank
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
