package frontend_traffic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class account_dto {
    @NotBlank
    private String account;
    @NotBlank
    private String password;
}
