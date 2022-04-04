package com.guli.service_cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.service_cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-04-04
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectIndexList();

    void saveBanner(CrmBanner banner);

    void updateBannerById(CrmBanner banner);

    void removeBannerById(String id);

    CrmBanner getBannerById(String id);


}
