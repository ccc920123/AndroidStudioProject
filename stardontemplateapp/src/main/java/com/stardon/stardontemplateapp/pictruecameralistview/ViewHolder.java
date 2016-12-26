package com.stardon.stardontemplateapp.pictruecameralistview;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 类名: ViewHolder
 * <br/>功能描述: 在使用listView gridView时，我往往会在这2个控件中定义适配器，适配器加载布局，
 * <br/>那么布局中出现控件最多的就是TextView，和ImageView，这儿就是帮助我们快速向TextView、ImageView
 * <br/>注入值、图片，在实际项目中还可以注入更多的View
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/5
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 方法名称: get
     * <br/>方法详述: 拿到一个ViewHolder对象
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

   /**方法名称: getView
   * <br/>方法详述:  通过控件的Id获取对于的控件，如果没有则加入views
   * <br/>参数:
   * <br/>返回值:
   * <br/>异常抛出 Exception:
   * <br/>异常抛出 NullPointerException:
   */


    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**方法名称:  为TextView设置字符串
    * <br/>方法详述: 
    * <br/>参数: 
    * <br/>返回值: 
    * <br/>异常抛出 Exception:
    * <br/>异常抛出 NullPointerException:
    */ 
    
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

  /**方法名称:
  * <br/>方法详述:  为ImageView设置图片
  * <br/>参数:viewId 需要设置图片的ImageView控件,drawableId drawable下的图片
  * <br/>返回值:
  * <br/>异常抛出 Exception:
  * <br/>异常抛出 NullPointerException:
  */

    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**方法名称:
    * <br/>方法详述:  * 为ImageView设置图片
    * <br/>参数: viewId 需要设置图片的ImageView控件,bm bitmap类型
    * <br/>返回值:
    * <br/>异常抛出 Exception:
    * <br/>异常抛出 NullPointerException:
    */

    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }
    /**方法名称:setCheck
     * <br/>方法详述:  * 设置CheckBox是否勾选
     * <br/>参数: viewId 需要设置图片的CheckBox控件,chenck boolean类型
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public ViewHolder setCheck(int viewId, boolean chenck) {
        CheckBox view = getView(viewId);
        view.setChecked(chenck);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

}

