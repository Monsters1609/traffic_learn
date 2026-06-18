package frontend_traffic.models;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;
import frontend_traffic.models.common_type_entity.UserGender;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class profile_entity {
    @Id
    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Column(name = "phone_number", unique = true, nullable = false)
    private int phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "avatar_id")
    private String avatarId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private account_entity accountID;
}
