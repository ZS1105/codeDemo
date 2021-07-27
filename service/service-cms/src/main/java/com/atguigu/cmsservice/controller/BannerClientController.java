package com.atguigu.cmsservice.controller;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: shuai
 * Date: 2021/7/18
 * desc:
 */
@RestController
@RequestMapping("/cmsservice/bannerclient")
@Api(description = "网站首页Banner列表")
// @CrossOrigin //跨域
public class BannerClientController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getBanners")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.getAllBanner();
        return R.ok().data("banners", list);
    }
}
