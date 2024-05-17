package org.saa.myrokomary_class20.services;

import jakarta.servlet.http.HttpServletRequest;
import org.saa.myrokomary_class20.config.security.basic.CustomEncodersDecoders;
import org.saa.myrokomary_class20.dto.UserPrinciple;
import org.saa.myrokomary_class20.entity.AccountEntity;
import org.saa.myrokomary_class20.entity.OrderEntity;
import org.saa.myrokomary_class20.repos.AccountEntityRepo;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService
{
    @Autowired
    private CustomEncodersDecoders passwordEncoder;
    private AccountEntityRepo accountEntityRepo;
    private OrderEntity orderEntity;

    public AccountEntity getAccountEntityData() {
        return this.accountEntityData;
    }

    private static AccountEntity accountEntityData;


    AccountService(AccountEntityRepo accountEntityRepo) {
        this.accountEntityRepo = accountEntityRepo;
    }

    public ApiResponse addAccunt(AccountEntity accountEntity){
        accountEntity.setPassword(passwordEncoder.passwordEncoder().encode(accountEntity.getPassword()));
        return ApiResponse.build(HttpStatus.OK).body(accountEntityRepo.save(accountEntity)).details("account saved");
    }

//    public AccountEntity loadUserByUsername(String userName,String password) throws UsernameNotFoundException {
//        /*Here in your case would call your WebService and check if the result is Y/N and return the UserDetails object with all roles, etc
//        If the user is not valid you could throw an exception
//        */
////        AccountEntity accountEntity = new AccountEntity();
////        accountEntity.setUserName(username);
////        accountEntity.setPassword(password);
//        try {
//            AccountEntity accountEntityData = accountEntityRepo.findByUserNameAndPassword(userName,password);
//            return accountEntityData;
//        } catch(Exception e){
//            return  null;
//        }
//
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountEntityRepo.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("User not Found "+username));
        UserPrinciple userPrinciple = new UserPrinciple(accountEntity);
        this.accountEntityData = accountEntity;
        return userPrinciple;
//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(accountEntity.getUserName())
////                .password("{noop}"+accountEntity.getPassword()) //JWT
//                .password(accountEntity.getPassword())
//                .roles(accountEntity.getRoles().stream().map(role->role.getRoleName()).toArray(String[]::new))
//                .build();
    }

//    private PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }



}
