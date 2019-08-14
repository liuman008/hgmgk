/**
 * 
 */
package com.lingfeng.pets.WeChatPay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 谷春
 *
 */
@RestController("")
@RequestMapping("/api")
@CrossOrigin
public class PayController {
    
    
    @PostMapping("/pay")
    public Object pay(HttpServletRequest request) {
        
        return null;
    }
    
}
