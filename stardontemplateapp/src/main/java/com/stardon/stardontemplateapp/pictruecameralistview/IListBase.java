package com.stardon.stardontemplateapp.pictruecameralistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

/**类名: IListBase
 * <br/>功能描述:接口
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/6
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */



public interface IListBase {
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
     * <br/>参数: parent,view,position,id前4个为GridView 的item默认事件参数,mDatas:整个数据List集合（也将是我们显示的list-->name,chenck,还有拍照的code）
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public void listViewOnItemClickListener(AdapterView<?> parent, View view, int position, long id, List<RecyclerItemBean> itemData);

    /**方法名称: otherPictrueDialogOKButtonClick
    * <br/>方法详述: 其他照片弹出dialog后拍照按钮的点击事件
    * <br/>参数: view  按钮，itemData 数据
    * <br/>返回值:
    * <br/>异常抛出 Exception:
    * <br/>异常抛出 NullPointerException:
    */

    public  void  otherPictrueDialogOKButtonClick(View v,List<RecyclerItemBean> itemData);
}
