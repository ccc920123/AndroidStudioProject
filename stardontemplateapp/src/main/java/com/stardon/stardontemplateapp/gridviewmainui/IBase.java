package com.stardon.stardontemplateapp.gridviewmainui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

/**类名: IBase
 * <br/>功能描述: 接口，用于处理子类逻辑入口
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/5
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */


public interface IBase {
    /**方法名称: bindView
    * <br/>方法详述: 处理的逻辑入口
    * <br/>参数: Bundle savedInstanceState
    * <br/>返回值:
    * <br/>异常抛出 Exception:
    * <br/>异常抛出 NullPointerException:
    */

    public void bindView(Bundle savedInstanceState);
    /**方法名称: onItemClickListener
    * <br/>方法详述: gridview 的item点击事件
    * <br/>参数: parent,view,position,id前4个为GridView 的item默认事件参数,mDatas:整个数据List集合（也将是我们显示的list-->title,imageid）
    * <br/>返回值:
    * <br/>异常抛出 Exception:
    * <br/>异常抛出 NullPointerException:
    */

    public void gridViewOnItemClickListener(AdapterView<?> parent, View view, int position, long id,List<Model> mDatas);
}
