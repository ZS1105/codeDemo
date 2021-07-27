package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-07-18
 */
@RestController
@RequestMapping("/cmsservice/banneradmin")
// @CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("/pageBanner/{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {

        Page<CrmBanner> pageParam = new Page<>(page, limit);
        bannerService.pageBanner(pageParam, null);
        return R.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("getBanner/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getBannerById(id);
        return R.ok().data("item", banner);
    }
    @ApiOperation(value = "新增Banner")
    @PostMapping("addBanner")
    public R save(@RequestBody CrmBanner banner) {
        bannerService.addBanner(banner);
        return R.ok();
    }
    @ApiOperation(value = "修改Banner")
    @PutMapping("updateBanner")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateBannerById(banner);
        return R.ok();
    }
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("deleteBanner/{id}")
    public R remove(@PathVariable String id) {
        bannerService.deleteBannerById(id);
        return R.ok();
    }
}

