package com.nulijiushimeili.librarianwebui.beans.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/******************************************************
 * Program: project-management
 * Author: 努力就是魅力
 * QQ : 734131757
 * DateTime: 2020-04-07 14:09
 * Desc:  TODO
 ********************************************************/

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SystemMenuEntity<T> {

    /**
     * 这里一定要new，因为这个对象不是spring管理的。所以会报 空指针。
     */
    private Gson gson = new Gson();

    /**
     * id
     */
    @Excel(name = "id", width = 10, orderNum = "0")
    private Integer id;

    /**
     * 菜单顺序编号
     */
    @Excel(name="菜单顺序", orderNum = "1")
    private Integer orderId;

    /**
     * 系统名称
     */
    @Excel(name="系统名称", orderNum = "2")
    private String systemName;

    /**
     * 菜单名称
     */
    @Excel(name="菜单名称", orderNum = "3")
    private String menuName;

    /**
     * 菜单路径
     */
    @Excel(name="菜单地址", orderNum = "4")
    private String href;

    /**
     * 菜单字体
     */
    @Excel(name="菜单字体", orderNum = "5")
    private String fontFamily;

    /**
     * 菜单图标
     */
    @Excel(name="菜单图标", orderNum = "6")
    private String icon;

    /**
     * 菜单是否展开
     */
    @Excel(name="菜单是否展开", orderNum = "7")
    private Boolean spread;

    /**
     * 菜单是否为被选中状态
     */
    @Excel(name="菜单是否被选中", orderNum = "8")
    private Boolean isCheck;

    /**
     * 菜单的 父id
     */
    @Excel(name="菜单的父ID", orderNum = "9")
    private Integer parentId;

    /**
     * 菜单的描述信息
     */
    @Excel(name="菜单描述信息", orderNum = "10")
    private String menuDesc;

    /**
     * 菜单子项
     */
    private List<SystemMenuEntity<T>> children = new ArrayList<>();

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
