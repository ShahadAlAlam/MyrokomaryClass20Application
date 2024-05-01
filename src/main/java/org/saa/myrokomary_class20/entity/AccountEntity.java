package org.saa.myrokomary_class20.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@DynamicUpdate(value = true)
@AllArgsConstructor
@Table(name="account")
@Getter
@Setter
public class AccountEntity{
    @Id
    @Column(updatable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @Column
    private String fullName;
    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String mobile;

    @Column
    private String email;

    @Column
    private Date dob;

    @Column
    private String gender;

    @Column
    private boolean accountNonExpired;

    @Column
    private boolean accountNonLocked;

    @Column
    private boolean credentialsNonExpired;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy="account", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderEntity> orders;

    @OneToMany(mappedBy="account",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AccountAddress> accountAddresses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
    joinColumns = @JoinColumn(name="accountId"),
    inverseJoinColumns = @JoinColumn(name="roleId"))
    private Set<Role> roles = new HashSet<>();

    public AccountEntity(){

    }
    public AccountEntity(Long accountId) {
        this.accountId=accountId;
    }

    public AccountEntity(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }
}
