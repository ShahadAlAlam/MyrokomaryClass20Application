package org.saa.myrokomary_class20.repos;

import org.saa.myrokomary_class20.entity.AccountAddress;
import org.saa.myrokomary_class20.entity.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsEntityRepo extends JpaRepository<OrderItemsEntity,Long> {

}
