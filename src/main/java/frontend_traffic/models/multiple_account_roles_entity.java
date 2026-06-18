package frontend_traffic.models;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class multiple_account_roles_entity {
    @Id
    @Column(name = "multiple_account_roles_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID multipleAccountRolesId;

    // relationship / association
    @ManyToOne
    @JoinColumn(name = "account_id")
    private account_entity accountId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private role_entity role;
}
