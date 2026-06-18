package frontend_traffic.models;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class account_entity extends timestamp_entity {
    // uuid account
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    // tài khoản (duy nhất)
    @Column(name = "account", unique = true, nullable = false)
    private String account;

    // password
    @Column(name = "password", nullable = false)
    private String password;

    // relationship / association
    @OneToMany(mappedBy = "accountId")
    private List<multiple_account_roles_entity> accountRoles;

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<session_token_entity> accessTokens;

    @OneToOne(mappedBy = "accountID", cascade = CascadeType.ALL, orphanRemoval = true)
    private profile_entity profile;
}
// region fetch
/*
 * VD:
 * 
 * @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval=
 * true, fetch = FetchType.LAZY)
 * private List<session_token_entity> accessTokens;
 * trong fetch có 2 kiểu là :
 * - FetchType.LAZY : chưa lấy dữ liệu ngay
 * - FetchType.EAGER : lấy luôn dữ liệu
 */
// endregion

// region cascade
/*
 * VD:
 * 
 * @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval=
 * true, fetch = FetchType.LAZY)
 * private List<session_token_entity> accessTokens;
 * 
 * tác dụng:
 * - nó sẽ báo Hibernate: khi thao tác với entity cha thì tự động thao tác tương
 * ứng với con.
 * - còn ALL ở đây thực chất là tập hợp của:
 * CascadeType.PERSIST: tự lưu luôn
 * CascadeType.MERGE
 * CascadeType.REMOVE: tự xóa luôn
 * CascadeType.REFRESH
 * CascadeType.DETACH
 */
// endregion

// region orphanRemoval
/*
 * VD:
 * 
 * @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval=
 * true, fetch = FetchType.LAZY)
 * private List<session_token_entity> accessTokens;
 * 
 * đây là phần nhiều người nhầm với cascade
 * - nó có nghĩa là nếu entity con bị "mất cha" thì tự động xóa khỏi database
 */
// endregion