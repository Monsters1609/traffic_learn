package frontend_traffic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class traffic_info_dto {
    @NotNull
    private Double lat;
    @NotNull
    private Double lng;
    @NotBlank
    private String address;
}
