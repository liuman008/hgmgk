/**
 * 
 */
package com.lingfeng.pets.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * @author 谷春
 *
 */
@RestController("OrderController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/orderController", description = "订单表的接口")
public class OrderController {
}
