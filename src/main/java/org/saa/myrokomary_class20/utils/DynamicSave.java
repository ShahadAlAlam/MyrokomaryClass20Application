package org.saa.myrokomary_class20.utils;

import jakarta.persistence.Id;
import org.saa.myrokomary_class20.config.constents.DatabaseTransectionState;
import org.saa.myrokomary_class20.config.security.basic.BasicAuthConfig;
import org.saa.myrokomary_class20.config.constents.DbCommonModel;
import org.saa.myrokomary_class20.services.AccountService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class DynamicSave{

//    @Autowired
//    private BasicAuthConfig basicAuthConfig;

    @Autowired
    private AccountService accountService;
    public <T extends JpaRepository<X,Long>,X extends DbCommonModel> X update(X entityClass , T repo, Long id, HashMap<String,Object> data){
        Optional<X> entityClassOptional = repo.findById(id);
        BeanWrapper wrapper = new BeanWrapperImpl(entityClassOptional.get());
            data.keySet().forEach(k -> {
                try {
//                wrapper.getPropertyDescriptor(k.toString()).getWriteMethod();
////                wrapper.getPropertyDescriptor(k.toString())
                    if (wrapper.isWritableProperty(k.toString())) {
                        wrapper.setPropertyValue(k.toString(), data.get(k.toString()));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
//            Long modId = basicAuthConfig.getUserDetailsService().getAccountEntityData().getAccountId();
            Long modId = accountService.getAccountEntityData().getAccountId();
            entityClassOptional.get().setModifiedBy(modId);

            entityClassOptional.get().setModifiedOn(new Timestamp(System.currentTimeMillis()));
            repo.save(entityClassOptional.get());
            return entityClassOptional.get();
    }

    public <T extends JpaRepository<X,Long>, X extends DbCommonModel,M extends DbCommonModel> List<X> updateDetails(X entityClass , T repo, M masterData , List<HashMap<String,Object>> data){
        List<X> returnData = new ArrayList<>();
        String idColumnName = getIdColumnName(entityClass.getClass());
        String masterIdColumnName = getIdColumnName(masterData.getClass());
        BeanWrapper wrapperMst = new BeanWrapperImpl(masterData);
        Long masterId = (Long) wrapperMst.getPropertyValue(masterIdColumnName);

        for(HashMap<String,Object> singleData: data) {

            if (DatabaseTransectionState.valueOf(singleData.get("DB_STATE").toString()) == DatabaseTransectionState.DB_UPDATE) {
                Optional<X> entityClassOptional = repo.findById(Long.parseLong(singleData.get(idColumnName).toString()));
                BeanWrapper wrapper = new BeanWrapperImpl(entityClassOptional.get());
                singleData.keySet().forEach(k -> {
                    try {
                        if (wrapper.isWritableProperty(k.toString())) {
                            wrapper.setPropertyValue(k.toString(), singleData.get(k.toString()));
                            if (k.toString().equals(masterIdColumnName)) {
                                wrapper.setPropertyValue(k.toString(), masterId);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
//                Long modId = basicAuthConfig.getUserDetailsService().getAccountEntityData().getAccountId();
                Long modId = accountService.getAccountEntityData().getAccountId();
                entityClassOptional.get().setModifiedBy(modId);
                entityClassOptional.get().setModifiedOn(new Timestamp(System.currentTimeMillis()));
                repo.save(entityClassOptional.get());
                returnData.add(entityClassOptional.get());
            } else if (DatabaseTransectionState.valueOf(singleData.get("DB_STATE").toString())  == DatabaseTransectionState.DB_INSERT) {
                Optional<X> entityClassOptional = Optional.of(entityClass);
                BeanWrapper wrapper = new BeanWrapperImpl(entityClassOptional.get());
                singleData.keySet().forEach(k -> {
                    try {
                        if (wrapper.isWritableProperty(k.toString())) {
                            wrapper.setPropertyValue(k.toString(), singleData.get(k.toString()));
                            if (k.toString().equals(masterIdColumnName)) {
                                wrapper.setPropertyValue(k.toString(), masterId);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
//                Long creId = basicAuthConfig.getUserDetailsService().getAccountEntityData().getAccountId();
                Long creId = accountService.getAccountEntityData().getAccountId();
                entityClassOptional.get().setCreatedBy(creId);
                entityClassOptional.get().setCreatedOn(new Timestamp(System.currentTimeMillis()));
                repo.save(entityClassOptional.get());
                returnData.add(entityClassOptional.get());
            } else if (DatabaseTransectionState.valueOf(singleData.get("DB_STATE").toString()) == DatabaseTransectionState.DB_DELETE) {
                repo.deleteById(Long.parseLong(singleData.get(idColumnName).toString()));

            } else {
                Optional<X> entityClassOptional = Optional.of(entityClass);
                BeanWrapper wrapper = new BeanWrapperImpl(entityClassOptional.get());
                singleData.keySet().forEach(k -> {
                    try {
                        if (wrapper.isWritableProperty(k.toString())) {
                            wrapper.setPropertyValue(k.toString(), singleData.get(k.toString()));
                            if (k.toString().equals(masterIdColumnName)) {
                                wrapper.setPropertyValue(k.toString(), masterId);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
                returnData.add(entityClassOptional.get());
            }
        }
        return returnData;
    }

    public <T> String getIdColumnName(Class<T> entityClass) {

        for (Field field : entityClass.getDeclaredFields()) {
            // Check if the field is annotated with @Id
            if (field.isAnnotationPresent(Id.class)) {
                // Return the name of the field
                return field.getName();
            }
        }

        // If the ID property is not found, return null or throw an exception
        return null;
    }
}
