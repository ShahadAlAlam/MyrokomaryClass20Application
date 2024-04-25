package org.saa.myrokomary_class20.services;

import org.saa.myrokomary_class20.entity.AccountAddress;
import org.saa.myrokomary_class20.entity.AccountEntity;
import org.saa.myrokomary_class20.entity.OrderEntity;
import org.saa.myrokomary_class20.repos.AccountAddressEntityRepo;
import org.saa.myrokomary_class20.repos.AccountEntityRepo;
import org.saa.myrokomary_class20.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountAddressService {
    private AccountAddressEntityRepo accountAddressEntityRepo;

    AccountAddressService(AccountAddressEntityRepo accountAddressEntityRepo) {
        this.accountAddressEntityRepo = accountAddressEntityRepo;
    }

    public ApiResponse addAccuntAddress(AccountAddress accountAddress){
        return ApiResponse.build(HttpStatus.OK).body(accountAddressEntityRepo.save(accountAddress)).details("account saved");
    }
}
