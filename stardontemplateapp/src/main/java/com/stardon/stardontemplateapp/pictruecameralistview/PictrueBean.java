package com.stardon.stardontemplateapp.pictruecameralistview;
/**类名: PictrueBean
 * <br/>功能描述:照片类
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/6
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */



public class PictrueBean {
    /**
     * 照片编号
     */
    private  String code;
    /**
     * 照片名称
     */
    private  String name;

    public PictrueBean(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public PictrueBean() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
