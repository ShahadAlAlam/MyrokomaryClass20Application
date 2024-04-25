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
import java.util.List;


@Entity
@DynamicUpdate(value = true)
@Table(name="account")
@Getter
@Setter
public class AccountEntity{
    @Id
    @Column(updatable = false)
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

    public AccountEntity(){

    }
    public AccountEntity(Long accountId) {
        this.accountId=accountId;
    }
}
