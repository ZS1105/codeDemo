package com.atguigu.aclservice.controller;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.aclservice.entity.Permission;
import com.atguigu.aclservice.helper.MemuHelper;
import com.atguigu.aclservice.service.PermissionService;
import com.atguigu.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("queryAllMenu")
    public R queryAllMenu() {
        List<Permission> list =  permissionService.queryAllMenu();
        List<JSONObject> jsonObjects = MemuHelper.bulid(list);
        return R.ok().data("allMenu",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("removeMenu/{id}")
    public R removeMenu(@PathVariable String id) {
        permissionService.removeChildById(id);
        return R.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/saveRolePermission")
    public R saveRolePermission(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShip(roleId,permissionId);
        return R.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("getMenusByRoleId/{roleId}")
    public R getMenusByRoleId(@PathVariable String roleId) {
        List<Permission> list = permissionService.getMenusByRoleId(roleId);
        return R.ok().data("menus", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("addMenu")
    public R addMenu(@RequestBody Permission permission) {
        permissionService.save(permission);
        return R.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("updateMenu")
    public R updateMenu(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return R.ok();
    }

}

