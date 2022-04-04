package com.guli.service_cms.controller;

import com.commonutils.Result;
import com.guli.service_cms.entity.CrmBanner;
import com.guli.service_cms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘佳俊
 */


@RestController
@RequestMapping("/service_cms/crm_banner")
@Api(description = "网站首页Banner列表")
@CrossOrigin //跨域
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public Result index() {
        List<CrmBanner> list = bannerService.selectIndexList();
        return Result.ok().data("bannerList", list);
    }

}