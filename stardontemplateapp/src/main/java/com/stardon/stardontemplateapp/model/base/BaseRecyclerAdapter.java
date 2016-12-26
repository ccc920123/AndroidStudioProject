package com.stardon.stardontemplateapp.model.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stardon.stardontemplateapp.model.base.BaseHolder;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

/**
 * @类名: BaseRecyclerAdapter
 * @功能描述: recycler的通用适配器
 * @作者:chepan
 * @时间: 2016/12/5
 * @版权申明:陈攀
 * @最后修改者:
 * @最后修改内容:
 */
public class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {

    private List<T> mDatas;
    private int mResLayout;
    private Class<? extends BaseHolder<T>> mClazz;

    /**
     * @方法名称: setmDatas
     * @方法详述: 设置要添加的list
     * @参数:list
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    /**
     * @方法名称: addAll
     * @方法详述: 添加一个list
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void addAll(List<T> mDatas) {
        this.mDatas.addAll(mDatas);
        notifyItemRangeInserted(this.mDatas.size() - mDatas.size(), this.mDatas.size());
    }

    /**
     * @方法名称: add
     * @方法详述: 添加一条数据
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void add(T data) {
        this.mDatas.add(data);
        notifyItemInserted(this.mDatas.size() - 1);
    }

    public void add(int index, T data) {
        this.mDatas.add(index, data);
        notifyItemInserted(index);
    }

    /**
     * @方法名称: getItem
     * @方法详述: 得到某条数据
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public T getItem(int position) {
        try {
            return this.mDatas.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * @方法名称:
     * @方法详述: BaseRecyclerAdapter的构造方法
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public BaseRecyclerAdapter(List<T> mDatas, int mResLayout, Class<? extends BaseHolder<T>> mClazz) {
        if (mClazz == null) {
            throw new RuntimeException("clazz is null,please check your params !");
        }
        if (mResLayout == 0) {
            throw new RuntimeException("res is null,please check your params !");
        }
        this.mDatas = mDatas;
        this.mResLayout = mResLayout;
        this.mClazz = mClazz;
    }

    public HashMap<Integer, Object> tags = new HashMap<>();

    public BaseRecyclerAdapter setTag(int tag, Object mObject) {
        if (mObject != null) {
            tags.put(tag, mObject);
        }
        return this;
    }

    @Override
    public BaseHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(mResLayout, null);
        if (tags != null && tags.size() > 0) {
            for (int tag : tags.keySet()) {
                view.setTag(tag, tags.get(tag));
            }
        }
        try {
            Constructor<? extends BaseHolder<T>> mClazzConstructor = mClazz.getConstructor(View.class);
            if (mClazzConstructor != null) {
                return mClazzConstructor.newInstance(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final BaseHolder<T> holder, int position) {
        holder.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
