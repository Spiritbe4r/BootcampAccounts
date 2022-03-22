package com.bootcamp.bankaccount.util;

import com.bootcamp.bankaccount.models.bean.Account;
import com.bootcamp.bankaccount.models.dto.AccountDto;
import org.springframework.beans.BeanUtils;

public class AppUtils {

  public static AccountDto entityToDto(Account account) {
    AccountDto accDto = new AccountDto();
    BeanUtils.copyProperties(account, accDto);
    return accDto;
  }

  public static Account dtoToEntity(AccountDto accDto) {
    Account account = new Account();
    BeanUtils.copyProperties(accDto, account);
    return account;
  }
}
