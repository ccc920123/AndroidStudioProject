package com.stardon.stardontemplateapp.pictruecameralistview;

/**类名: RecyclerItemBean
 * <br/>功能描述:存放item 属性类
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/6
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */



public class RecyclerItemBean {
    /**
     * 图片
     */
    private  int itemImage;
    /**
     * 照片code
     */
    private  String itemCode;
    /**
     * 照片名称
     */
    private String itemName;
    /**
     * 是否拍照
     */
    private boolean itemStat;

    public RecyclerItemBean()
    {

    }

    public RecyclerItemBean(int itemImage, boolean itemStat, String itemName, String itemCode) {
        this.itemImage = itemImage;
        this.itemStat = itemStat;
        this.itemName = itemName;
        this.itemCode = itemCode;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isItemStat() {
        return itemStat;
    }

    public void setItemStat(boolean itemStat) {
        this.itemStat = itemStat;
    }
}
