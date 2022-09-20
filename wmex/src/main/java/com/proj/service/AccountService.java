package com.proj.service;

import com.proj.common.CommonResult;
import java.math.*;


/**
 * @Author JerryLiu
 * @Date 2022/9/2 14:44
 * @PackageName:com.proj.service
 * @InterfaceName: AccountService
 * @Description: TODO
 * @Version 1.0
 */
public interface AccountService {
    CommonResult getAccount(Integer cid);
    CommonResult createChecking(Integer cid);
    CommonResult createInvest(Integer cid);
    CommonResult balanceOpt(BigDecimal cash, Integer aid);
    CommonResult transfer(BigDecimal cash, Integer aid1, Integer aid2);
}
