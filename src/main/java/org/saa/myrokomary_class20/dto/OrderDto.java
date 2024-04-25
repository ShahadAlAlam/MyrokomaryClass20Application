package org.saa.myrokomary_class20.dto;

import lombok.*;
import org.saa.myrokomary_class20.entity.OrderEntity;
import org.saa.myrokomary_class20.entity.OrderItemsEntity;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private OrderEntity order;

    private List<OrderItemsEntity> orderItems;

}
