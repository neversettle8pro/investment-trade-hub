package com.proj.controller;

import com.proj.common.CommonResult;
import com.proj.dto.TradeDTO;
import com.proj.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 16:09
 * @PackageName:com.proj.controller
 * @ClassName: TradeController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class TradeController {
    @Autowired
    TradeService tradeService;

    @PostMapping("/trade/buy")
    @ResponseBody
    public CommonResult buy(@RequestBody TradeDTO tradeDTO) {
        return tradeService.buy(tradeDTO.getCid(),tradeDTO.getAid(),tradeDTO.getPid(),tradeDTO.getAmount());
    }

    @PostMapping("/trade/sell")
    @ResponseBody
    public CommonResult sell(@RequestBody TradeDTO tradeDTO) {
        return tradeService.sell(tradeDTO.getCid(),tradeDTO.getAid(), tradeDTO.getPid(),tradeDTO.getAmount());
    }

    @GetMapping("/trade/count")
    @ResponseBody
    public CommonResult count(@RequestBody TradeDTO tradeDTO) {
        return tradeService.count(tradeDTO.getCid());
    }

    @PostMapping("/trade/sellDelivery")
    @ResponseBody
    public CommonResult sellDelivery() {
        return tradeService.sellDelivery();
    }
}
