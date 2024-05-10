package org.saa.myrokomary_class20.repos;


import org.saa.myrokomary_class20.entity.TestDtlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDtlEntityRepo extends JpaRepository<TestDtlEntity,Long> {

}
