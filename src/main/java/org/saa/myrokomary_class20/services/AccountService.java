package org.saa.myrokomary_class20.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.saa.myrokomary_class20.entity.AccountEntity;
import org.saa.myrokomary_class20.entity.OrderEntity;
import org.saa.myrokomary_class20.repos.AccountEntityRepo;
import org.saa.myrokomary_class20.repos.OrderEntityRepo;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService {
    private AccountEntityRepo accountEntityRepo;
    private OrderEntity orderEntity;

    AccountService(AccountEntityRepo accountEntityRepo) {
        this.accountEntityRepo = accountEntityRepo;
    }

    public ApiResponse addAccunt(AccountEntity accountEntity){
        return ApiResponse.build(HttpStatus.OK).body(accountEntityRepo.save(accountEntity)).details("account saved");
    }

    public AccountEntity loadUserByUsername(String userName,String password) throws UsernameNotFoundException {
        /*Here in your case would call your WebService and check if the result is Y/N and return the UserDetails object with all roles, etc
        If the user is not valid you could throw an exception
        */
//        AccountEntity accountEntity = new AccountEntity();
//        accountEntity.setUserName(username);
//        accountEntity.setPassword(password);
        try {
            AccountEntity accountEntityData = accountEntityRepo.findByUserNameAndPassword(userName,password);
            return accountEntityData;
        } catch(Exception e){
            return  null;
        }

    }
}
