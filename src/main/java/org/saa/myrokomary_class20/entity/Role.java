package org.saa.myrokomary_class20.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@DynamicUpdate(value = true)
@Table(name="role")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column
    private String roleName;

    @Column
    private String description;

    public Role(){

    }
    public Role(Long roleId){
        this.roleId=roleId;
    }

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    @Override
    public boolean equals(Object o){
        if(o==this){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return this.roleName;
    }

    //    @ManyToMany(mappedBy = "roles")
//    private List<AccountEntity> accountEntities;
//
//    public List<AccountEntity> getAccountEntities() {
//        return accountEntities;
//    }
//
//    public void setAccountEntities(Collection<AccountEntity> accountEntities) {
//        this.accountEntities = accountEntities;
//    }
}
