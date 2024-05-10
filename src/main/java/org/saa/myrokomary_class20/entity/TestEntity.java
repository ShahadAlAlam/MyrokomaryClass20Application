package org.saa.myrokomary_class20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.saa.myrokomary_class20.config.constents.Model;

import java.util.List;


@Entity
@DynamicUpdate(value = true)
@Table(name="test_mst")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestEntity extends Model {

    @Id()
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long testId;

    @Column
    private String testName;

    @Column
    private Double testNumData;

    @Column
    private String testDesc;

    @OneToMany(mappedBy = "testEntity")
    private List<TestDtlEntity> testDtlEntityList;
}
