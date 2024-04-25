package org.saa.myrokomary_class20.repos;

import org.saa.myrokomary_class20.entity.AccountEntity;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEntityRepo extends JpaRepository<OrderEntity,Long> {

    @Query(value=" Select coalesce(Max(order_id),0)+1  from order_mst ",nativeQuery = true)
    public Long findMaxId();

    public List<OrderEntity> findAllByAccountAccountId (Long accountId);
}
