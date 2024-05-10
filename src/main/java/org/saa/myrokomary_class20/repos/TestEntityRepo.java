package org.saa.myrokomary_class20.repos;


import org.saa.myrokomary_class20.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestEntityRepo extends JpaRepository<TestEntity,Long> {

}
