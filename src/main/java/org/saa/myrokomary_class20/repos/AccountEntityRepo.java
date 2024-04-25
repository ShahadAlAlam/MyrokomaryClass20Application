package org.saa.myrokomary_class20.repos;

import org.saa.myrokomary_class20.entity.AccountEntity;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountEntityRepo extends JpaRepository<AccountEntity,Long> {

    @Query(value=" Select coalesce(Max(account_id),0)+1  from account ",nativeQuery = true)
    public Long findMaxId();

    public AccountEntity findByUserNameAndPassword( String userName, String password);
}
