package com.stardon.stardontemplateapp.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.stardon.stardontemplateapp.R;
import com.stardon.stardontemplateapp.model.base.BaseRecyclerAdapter;
import com.stardon.stardontemplateapp.model.base.CommonBaseActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * @类名: ModelListViewActivity
 * @功能描述: 自定义listview界面
 * @作者:chepan
 * @时间: 2016/12/5
 * @版权申明:陈攀
 * @最后修改者:
 * @最后修改内容:
 */
public abstract class ModelListViewActivity extends CommonBaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {
    // Content View Elements

    private LinearLayout mAddview;
    private BGARefreshLayout mDefineRefreshWithLoad;
    private RecyclerView mRecyclerView;

    /**
     * 数据填充adapter
     */
    private BaseRecyclerAdapter mRecyclerViewAdapter = null;
    /**
     * 设置刷新和加载
     */
    private DefineBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView = null;

    // End Of Content View Elements

    /**
     * 进入页面首次加载数据
     */
    @Override
    protected void onStart() {
        super.onStart();
//        mDefineRefreshWithLoad.beginRefreshing();
//        onBGARefreshLayoutBeginRefreshing(mDefineRefreshWithLoad);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_model_list_view;
    }

    @Override
    public void initView() {
        mAddview = (LinearLayout) findViewById(R.id.addview);
        mDefineRefreshWithLoad = (BGARefreshLayout) findViewById(R.id.define_bga_refresh_with_load);
        mRecyclerView = (RecyclerView) findViewById(R.id.define_bga_refresh_with_load_recycler);
        //设置刷新和加载监听
        mDefineRefreshWithLoad.setDelegate(this);
        setBgaRefreshLayout(getIsReflash(), getIsLoad());
        setRecyclerView(getRecyclerViewManger());

    }

    /**
     * @方法名称: getRecyclerViewManger
     * @方法详述: 得到RecyclerView的布局方式
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    protected abstract RecyclerView.LayoutManager getRecyclerViewManger();

    /**
     * @方法名称:getIsLoad
     * @方法详述:是否支持上拉加载
     * @参数:
     * @返回值: boolean 是否可支持上拉刷新
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    protected abstract boolean getIsLoad();

    /**
     * @方法名称:getIsReflash
     * @方法详述:是否支持上拉刷新
     * @参数:
     * @返回值: boolean 是否可支持上拉刷新
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    protected abstract boolean getIsReflash();


    /**
     * @方法名称: setBgaRefreshLayout
     * @方法详述: 设置 BGARefreshLayout刷新和加载
     * @参数:1:是否设置下拉刷新 2：是否设置上拉加载
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    private void setBgaRefreshLayout(boolean isreflash, boolean islaod) {
        mDefineBAGRefreshWithLoadView = new DefineBAGRefreshWithLoadView(this, isreflash, islaod);
        //设置刷新样式
        mDefineRefreshWithLoad.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
        mDefineBAGRefreshWithLoadView.updateLoadingMoreText("自定义加载更多");
    }

    /**
     * @方法名称: setRecyclerView
     * @方法详述: 设置RecyclerView的布局方式
     * @参数:layoutManager 要设置的流式布局样式
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void setRecyclerView(RecyclerView.LayoutManager layoutManager) {
        //垂直listview显示方式
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * @方法名称: setRecyclerCommadapter
     * @方法详述: 为mRecyclerView设置adapter
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void setRecyclerCommadapter(BaseRecyclerAdapter adapter) {
        mRecyclerViewAdapter = adapter;
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    /**
     * @方法名称: addView
     * @方法详述: 为布局添加自定义view
     * @参数:view 要添加的view
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void addView(View view) {
        mAddview.addView(view);
    }





}
