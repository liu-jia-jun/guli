package com.guli.service_cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.service_cms.entity.CrmBanner;
import com.guli.service_cms.mapper.CrmBannerMapper;
import com.guli.service_cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author 刘佳俊
 * @since 2022-04-04
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public List<CrmBanner> selectIndexList() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 3");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void saveBanner(CrmBanner banner) {
        baseMapper.insert(banner);
    }

    @Override
    public void updateBannerById(CrmBanner banner) {
        baseMapper.updateById(banner);
    }

    @Override
    public void removeBannerById(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public CrmBanner getBannerById(String id) {
        return baseMapper.selectById(id);
    }


}
