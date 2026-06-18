package frontend_traffic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class account_dto {
    @NotBlank
    private String account;
    @NotBlank
    private String password;
}
