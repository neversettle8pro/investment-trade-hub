package com.proj.service;

import com.proj.common.CommonResult;


/**
 * @Author JerryLiu
 * @Date 2022/9/5 16:09
 * @PackageName:com.proj.service
 * @InterfaceName: TradeService
 * @Description: TODO
 * @Version 1.0
 */
public interface TradeService {
    CommonResult buy(Integer cid, Integer aid, Integer pid, Integer amount);
    CommonResult sell(Integer cid, Integer aid, Integer pid, Integer amount);
    CommonResult count(Integer cid);
    CommonResult sellDelivery();
}
