package com.proj.mapper;

import com.proj.po.TradeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 16:09
 * @PackageName:com.proj.mapper
 * @InterfaceName: TradeMapper
 * @Description: TODO
 * @Version 1.0
 */
@Repository
public interface TradeMapper {
    void createDeal(@Param("aid") Integer aid, @Param("pid") Integer pid, @Param("amount") Integer amount, @Param("price")BigDecimal price, @Param("createtime") Date createtime);
    Integer getAmount(@Param("aid") Integer aid, @Param("pid") Integer pid);
    List<Integer> getPid(@Param("aid") Integer aid);
    List<Integer> getDelivery();
    TradeInfo getInfoById(@Param("id") Integer id);
}
