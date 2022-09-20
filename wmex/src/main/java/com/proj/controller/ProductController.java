package com.proj.controller;

import com.proj.common.CommonResult;
import com.proj.dto.ProductInfoDTO;
import com.proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author JerryLiu
 * @Date 2022/9/5 13:28
 * @PackageName:com.proj.controller
 * @ClassName: ProductController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product/getProduct")
    @ResponseBody
    public CommonResult getProduct(@RequestBody ProductInfoDTO productInfoDTO){
        return productService.getProduct(productInfoDTO.getPid());
    }

    @PostMapping("/product/createProduct")
    @ResponseBody
    public CommonResult createProduct (@RequestBody ProductInfoDTO productInfoDTO){
        return productService.createProduct(productInfoDTO.getAmount(),productInfoDTO.getPrice(),productInfoDTO.getRate(),productInfoDTO.getDelivery(),productInfoDTO.getDate());
    }

    @PostMapping("/product/updateProduct")
    @ResponseBody
    public CommonResult updateProduct (@RequestBody ProductInfoDTO productInfoDTO){
        return productService.updateProduct(productInfoDTO.getPid(),productInfoDTO.getAmount(),productInfoDTO.getPrice(),productInfoDTO.getRate(),productInfoDTO.getDelivery(),productInfoDTO.getDate());
    }

    @GetMapping("/product/showProduct")
    @ResponseBody
    public CommonResult showProduct () {
        return productService.showProduct();
    }
}
