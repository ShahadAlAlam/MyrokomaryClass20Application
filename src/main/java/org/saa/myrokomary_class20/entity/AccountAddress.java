package org.saa.myrokomary_class20.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Collection;
import java.util.List;

@Entity
@DynamicUpdate(value = true)
@Table(name="accountAddress")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountAddress {
    @Id
    @Column(updatable = false)
    private Long accountAddressId;

    @Column
    private String address;

    @ManyToOne
    @JoinColumn(name="accountId", nullable=false,referencedColumnName = "accountId")
    @JsonIgnore
    private AccountEntity account;

    @OneToMany(mappedBy = "accountAddress",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderEntity> orderEntity;
}
