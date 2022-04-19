package com.guli.service_edu.service.impl;

import com.guli.service_edu.service.OrderClient;
import org.springframework.stereotype.Component;

/**
 * @author 刘佳俊
 */
@Component
public class OrderFile implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}
