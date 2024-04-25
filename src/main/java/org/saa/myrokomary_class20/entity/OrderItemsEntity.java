package org.saa.myrokomary_class20.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;
import java.util.List;


@Entity
@DynamicUpdate(value = true)
@Table(name="order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemsEntity {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemsId;

    @Column
    private Double unitRate;

    @Column
    private Double quantity;

    @Column(length = 10)
    private String status;

    @Transient
    private Double totalAmt;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderId", nullable=false, referencedColumnName = "orderId")
    @JsonIgnore
    private OrderEntity order;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="booksId", nullable=false,referencedColumnName = "books_id")
    private BooksEntity books;

    @PostLoad
    public void totalValue(){
        this.totalAmt = this.quantity*this.unitRate;
    }

}
