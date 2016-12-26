package com.stardon.stardontemplateapp.gridviewmainui;

/**类名: Model
 * <br/>功能描述:用于存放title 和图片id的类
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/5
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class Model {
    public String name;
    public int iconRes;

    public Model(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}