package raut.shubham.electroroute.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name = "users")
@Builder
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Schema(hidden = true)
    private String userId;



    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    private String name;

    @Schema(hidden = true)
    private String address;

    @Schema(hidden = true)
    private Long latitude;
    @Schema(hidden = true)
    private Long longitude;


    @OneToOne
    @Schema(hidden = true)
    private EV vehicle;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles" ,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    @Schema(hidden = true)
    private Set<UserRoles> roles = new HashSet<>();






}
