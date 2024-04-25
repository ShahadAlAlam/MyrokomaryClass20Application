package org.saa.myrokomary_class20.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@DynamicUpdate(value = true)
@Table(name="order_mst")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @Column(insertable = true,updatable = false)
    private Long OrderId;

    @Column(unique = true, nullable = false, length = 50)
    private String orderNo;
    @Column
    private Date orderDate;

    @Column
    private Date expectedDaliveryDate;

    @Column(length = 10)
    private String orderStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="accountId", nullable=false, referencedColumnName = "accountId")
    @JsonIgnore
    private AccountEntity account;

    @OneToMany(mappedBy="order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItemsEntity> orderItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="accountAddressId", nullable=false)
    private AccountAddress accountAddress;

    public void addOrderItem(OrderItemsEntity orderItemsEntity){
        if(this.orderItems!=null){
            this.orderItems.add(orderItemsEntity);
        } else{
            this.orderItems = new ArrayList<>();
            this.orderItems.add(orderItemsEntity);
        }
    }
//
//    public OrderEntity(){
//
//    }
//    public OrderEntity(Long orderId, String orderNo, Date orderDate, Date expectedDaliveryDate, String orderStatus, AccountEntity account, List<OrderItemsEntity> orderItems, AccountAddress accountAddress) {
//        OrderId = orderId;
//        this.orderNo = orderNo;
//        this.orderDate = orderDate;
//        this.expectedDaliveryDate = expectedDaliveryDate;
//        this.orderStatus = orderStatus;
//        this.account = account;
//        this.orderItems = orderItems;
//        this.accountAddress = accountAddress;
//    }


//
//    @ManyToMany
//    @JoinTable(
//            name = "orderItems",
//            joinColumns = @JoinColumn(name = "orderId"),
//            inverseJoinColumns = @JoinColumn(name = "booksId"))
//    List<BooksEntity> orderItems;

}
