package com.guli.service_msm.service;

import java.util.Map;

public interface MsmService {
    boolean send(String phone, Map<String, String> param);
}
