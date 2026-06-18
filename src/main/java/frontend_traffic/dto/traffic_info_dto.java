package frontend_traffic.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class traffic_info_dto {
    @NotBlank
    private Double lat;
    @NotBlank
    private Double lng;
    @NotBlank
    private String address;
}
