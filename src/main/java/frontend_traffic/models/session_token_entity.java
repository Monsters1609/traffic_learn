package frontend_traffic.models;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class session_token_entity extends timestamp_entity {
    @Id
    @Column(name = "token_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID token_id;

    // refresh token
    @Column(name = "refresh_token")
    private String refreshToken;

    // lưu thời điểm refresh token hết hạn
    @Column(name = "expires_at")
    private Date expiresAt;

    // relationship / association
    @ManyToOne()
    @JoinColumn(name = "account_id")
    private account_entity accountId;
}
