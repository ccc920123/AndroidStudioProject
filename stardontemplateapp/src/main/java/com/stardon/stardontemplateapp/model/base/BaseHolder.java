package com.stardon.stardontemplateapp.model.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @类名: BaseHolder
 * @功能描述: RecyclerView的holder，自定义holder必须继承BaseHolder 重写里面的方法
 * @作者:chepan
 * @时间: 2016/12/5
 * @版权申明:陈攀
 * @最后修改者:
 * @最后修改内容:
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    protected View mView;
    protected T mData;
    protected Context mContext;

    public View getView() {
        return mView;
    }

    public BaseHolder(View view) {
        super(view);
        this.mView = view;
        mContext = this.mView.getContext();
        findview();
        init();
    }

    protected abstract void findview();

    /**
     * @方法名称: init
     * @方法详述: 初始化
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void init() {

    }

    /**
     * @方法名称: setData
     * @方法详述: 设置view的数据
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void setData(T mData) {
        this.mData = mData;
    }
}