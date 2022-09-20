package com.proj.mapper;

import com.proj.po.AccountInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author JerryLiu
 * @Date 2022/9/2 14:49
 * @PackageName:com.proj.mapper
 * @InterfaceName: AccountMapper
 * @Description: TODO
 * @Version 1.0
 */
@Repository
public interface AccountMapper {
    List<Integer> getAccount(@Param("cid") Integer cid);
    Integer getCustomer(@Param("aid") Integer aid);
    boolean createChecking();
    boolean createInvest();
    boolean balanceOpt(@Param("balance") BigDecimal balance, @Param("aid") Integer aid);
    BigDecimal getBalance(@Param("aid") Integer aid);
    String checkType(@Param("aid") Integer aid);
    Integer newestId();
    void buildRelation(@Param("cid") Integer cid, @Param("aid") Integer aid);
}
