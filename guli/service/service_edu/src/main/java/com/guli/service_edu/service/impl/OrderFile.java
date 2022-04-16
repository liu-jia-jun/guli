package com.guli.service_edu.service.impl;

import com.guli.service_edu.service.OrderClient;

/**
 * @author 刘佳俊
 */
public class OrderFile implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}
