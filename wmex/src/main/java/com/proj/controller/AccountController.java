package com.proj.controller;

import com.proj.common.CommonResult;
import com.proj.dto.AccountInfoDTO;
import com.proj.dto.TransferDTO;
import com.proj.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author JerryLiu
 * @Date 2022/9/2 14:50
 * @PackageName:com.proj.controller
 * @ClassName: AccountContrroller
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/account/information")
    @ResponseBody
    public CommonResult getAccount(@RequestBody AccountInfoDTO accountInfoDTO) {
        return accountService.getAccount(accountInfoDTO.getCid());
    }

    @PostMapping("/account/createChecking")
    @ResponseBody
    public CommonResult createChecking(@RequestBody AccountInfoDTO accountInfoDTO) {
        return accountService.createChecking(accountInfoDTO.getCid());
    }

    @PostMapping("/account/createInvest")
    @ResponseBody
    public CommonResult createInvest(@RequestBody AccountInfoDTO accountInfoDTO) {
        return accountService.createInvest(accountInfoDTO.getCid());
    }

    @PostMapping("/account/balanceOpt")
    @ResponseBody
    public CommonResult balanceOpt(@RequestBody AccountInfoDTO accountInfoDTO) {
        return accountService.balanceOpt(accountInfoDTO.getCash(),accountInfoDTO.getAid());
    }

    @PostMapping("/account/transfer")
    @ResponseBody
    public CommonResult transfer(@RequestBody TransferDTO transferDTO) {
        return accountService.transfer(transferDTO.getCash(),transferDTO.getSourceAccountId(),transferDTO.getDestinationAccountId());
    }
}
