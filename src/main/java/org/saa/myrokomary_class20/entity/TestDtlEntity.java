package org.saa.myrokomary_class20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.saa.myrokomary_class20.config.constents.DbCommonModel;


@Entity
@DynamicUpdate(value = true)
@Table(name="test_dtl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestDtlEntity extends DbCommonModel {
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long testDtlId;

    @Column
    private String testDtlName;

    @ManyToOne
    @JoinColumn(name = "testId")
    private TestEntity testEntity;
}
