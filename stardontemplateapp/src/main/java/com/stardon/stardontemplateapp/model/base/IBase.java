package com.stardon.stardontemplateapp.model.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IBase {
  /**@方法名称: createView
  * @方法详述: 创建要加载的View
  * @参数: 
  * @返回值: 
  * @异常抛出 Exception:
  * @异常抛出 NullPointerException:
  */
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


   /**@方法名称: getContentLayout
   * @方法详述: 引用要加载的布局参数
   * @参数:
   * @返回值:
   * @异常抛出 Exception:
   * @异常抛出 NullPointerException:
   */
    int getContentLayout();
    /**@方法名称: initView
    * @方法详述: 初始化控件
    * @参数: 
    * @返回值: 
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
  void   initView();//初始化控件
    /**@方法名称: initData
    * @方法详述: 初始化数据
    * @参数: 
    * @返回值: 
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    void initData();//初始化数据
}