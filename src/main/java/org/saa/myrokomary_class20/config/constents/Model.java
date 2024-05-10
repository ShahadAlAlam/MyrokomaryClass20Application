package org.saa.myrokomary_class20.config.constents;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;
import java.sql.Date;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Model implements Cloneable{
    @Column
    private Long cidId;

    @Column
    private Long createdBy;

    @Column
    private java.sql.Timestamp createdOn;

    @Column
    private Long modifiedBy;

    @Column
    private java.sql.Timestamp modifiedOn;

    @Transient
    private DatabaseTransectionState DB_STATE = DatabaseTransectionState.DB_NONE;

    // no need for getter setter because used Lombok Getter Setter
//    if we do not use Lombok Getter and Setter then below dynamic value set will not work
    public <T extends Field> void setValue(String field, Object value)
    {
        try {
            T f= (T) this.getClass().getField(field);
            f.set(this,(T)value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
