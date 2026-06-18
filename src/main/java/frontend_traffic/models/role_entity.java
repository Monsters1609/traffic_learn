package frontend_traffic.models;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class role_entity extends timestamp_entity {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roleId;

    // role
    @Column(name = "role_name", nullable = false)
    private String role;

    // mã role
    @Column(name = "role_code", nullable = false)
    private int roleCode;

    // mô tả role
    @Column(name = "role_description", nullable = false)
    private String roleDescription;

    @OneToMany(mappedBy = "role")
    private List<multiple_account_roles_entity> accountRoles;
}
