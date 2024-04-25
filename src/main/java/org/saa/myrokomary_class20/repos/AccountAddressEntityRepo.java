package org.saa.myrokomary_class20.repos;

import org.saa.myrokomary_class20.entity.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountAddressEntityRepo extends JpaRepository<AccountAddress,Long> {

    @Query(value=" Select coalesce(Max(account_id),0)+1  from AccountAddress ",nativeQuery = true)
    public Long findMaxId();
}
