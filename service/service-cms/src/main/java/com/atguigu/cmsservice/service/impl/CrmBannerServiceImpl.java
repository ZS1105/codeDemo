package com.atguigu.cmsservice.service.impl;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.mapper.CrmBannerMapper;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-07-18
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public void deleteBannerById(String id) {
        int lines = baseMapper.deleteById(id);
        if (lines < 1){
            throw new GuliException(20001,"删除banner出错");
        }
    }

    @Override
    public void updateBannerById(CrmBanner banner) {
        int lines = baseMapper.update(banner, null);
        if (lines < 1){
            throw new GuliException(20001,"更新banner出错");
        }
    }

    @Override
    public void addBanner(CrmBanner banner) {
        int lines = baseMapper.insert(banner);
        if (lines < 1){
            throw new GuliException(20001,"添加banner出错");
        }
    }

    @Override
    public CrmBanner getBannerById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void pageBanner(Page<CrmBanner> pageParam, Wrapper o) {
       baseMapper.selectPage(pageParam, o);
    }

    @Override
    public List<CrmBanner> getAllBanner() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 3");
        return baseMapper.selectList(wrapper);
    }
}
