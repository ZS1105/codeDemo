package com.atguigu.cmsservice.service;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author zs
 * @since 2021-07-18
 */
public interface CrmBannerService extends IService<CrmBanner> {

    void deleteBannerById(String id);

    void updateBannerById(CrmBanner banner);

    void addBanner(CrmBanner banner);

    CrmBanner getBannerById(String id);

    void pageBanner(Page<CrmBanner> pageParam, Wrapper o);

    List<CrmBanner> getAllBanner();
}
