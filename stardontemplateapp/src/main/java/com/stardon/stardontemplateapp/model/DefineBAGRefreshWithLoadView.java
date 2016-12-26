package com.stardon.stardontemplateapp.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stardon.stardontemplateapp.R;

import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**@类名: DefineBAGRefreshWithLoadView
* @功能描述: 自定义上拉加载下拉刷新
* @作者:chepan
* @时间: 2016/12/5
* @版权申明:陈攀
* @最后修改者:
* @最后修改内容:
*/
public class DefineBAGRefreshWithLoadView extends BGARefreshViewHolder {
    private TextView mHeaderStatusTv;
    private ImageView mHeaderArrowIv;
    private ImageView mHeaderChrysanthemumIv;
    private AnimationDrawable mHeaderChrysanthemumAd;
    private String mPullDownRefreshText = "下拉刷新!!";
    private String mReleaseRefreshText = "释放更新!!";
    private String mRefreshingText = "加载中!!...";
    private boolean isRefreshEnabled = true;
    /** 判断是否使用上拉加载 */

    private boolean mIsLoadingMoreEnabled = true;
    public DefineBAGRefreshWithLoadView(Context context, boolean isLoadingMoreEnabled, boolean isRefreshEnabled) {
        super(context, isLoadingMoreEnabled);
        this.mIsLoadingMoreEnabled = isLoadingMoreEnabled;
        this.isRefreshEnabled = isRefreshEnabled;
    }
    /**@方法名称: setPullDownRefreshText
    * @方法详述: 设置下拉显示文字
    * @参数:要设置的文字
    * @返回值:
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public void setPullDownRefreshText(String pullDownRefreshText) {
        this.mPullDownRefreshText = pullDownRefreshText;
    }
    /**@方法名称: setReleaseRefreshText
    * @方法详述: 设置释放显示文字
    * @参数:
    * @返回值:
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public void setReleaseRefreshText(String releaseRefreshText) {
        this.mReleaseRefreshText = releaseRefreshText;
    }
    /**@方法名称: setRefreshingText
    * @方法详述: 设置正在刷新显示文字
    * @参数:
    * @返回值:
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public void setRefreshingText(String refreshingText) {
        this.mRefreshingText = refreshingText;
    }
    /**@方法名称: getRefreshHeaderView
    * @方法详述: 自定义定义刷新布局
    * @参数:
    * @返回值:自定义的布局样式
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public View getRefreshHeaderView() {
        if(this.mRefreshHeaderView == null) {
            this.mRefreshHeaderView = View.inflate(this.mContext, R.layout.header_bga_dodo, (ViewGroup)null);
            this.mRefreshHeaderView.setBackgroundColor(0);
            if(this.mRefreshViewBackgroundColorRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundColorRes);
            }
            if(this.mRefreshViewBackgroundDrawableRes != -1) {
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundDrawableRes);
            }
            this.mHeaderStatusTv = (TextView)this.mRefreshHeaderView.findViewById(R.id.tv_normal_refresh_header_status);
            this.mHeaderArrowIv = (ImageView)this.mRefreshHeaderView.findViewById(R.id.iv_normal_refresh_header_arrow);
            this.mHeaderChrysanthemumIv = (ImageView)this.mRefreshHeaderView.findViewById(R.id.iv_normal_refresh_header_chrysanthemum);
            this.mHeaderChrysanthemumAd = (AnimationDrawable)this.mHeaderChrysanthemumIv.getDrawable();
            this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
        }
        //刷新不可用
        if(!isRefreshEnabled){
            return null;
        }
        return this.mRefreshHeaderView;
    }
    /**@方法名称: changeToRefreshing
    * @方法详述: 已经开始刷新
    * @参数:
    * @返回值:
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public void changeToRefreshing() {
        this.mHeaderStatusTv.setText(this.mRefreshingText);
        this.mHeaderArrowIv.clearAnimation();
        this.mHeaderArrowIv.setVisibility(View.GONE);
        this.mHeaderChrysanthemumIv.setVisibility(View.VISIBLE);
        this.mHeaderChrysanthemumAd.start();
    }
    /**@方法名称: changeToPullDown
    * @方法详述: 开始下拉
    * @参数:
    * @返回值:
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public void changeToPullDown() {
        this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
        this.mHeaderChrysanthemumIv.setVisibility(View.GONE);
        this.mHeaderChrysanthemumAd.stop();
        this.mHeaderArrowIv.setVisibility(View.VISIBLE);
    }

   /**@方法名称: changeToReleaseRefresh
   * @方法详述: 下拉到一定程度，可以刷新
   * @参数:
   * @返回值:
   * @异常抛出 Exception:
   * @异常抛出 NullPointerException:
   */
    public void changeToReleaseRefresh() {
        this.mHeaderStatusTv.setText(this.mReleaseRefreshText);
        this.mHeaderChrysanthemumIv.setVisibility(View.GONE);
        this.mHeaderChrysanthemumAd.stop();
        this.mHeaderArrowIv.setVisibility(View.VISIBLE);
    }

   /**@方法名称: onEndRefreshing
   * @方法详述: 结束刷新
   * @参数:
   * @返回值:
   * @异常抛出 Exception:
   * @异常抛出 NullPointerException:
   */
    public void onEndRefreshing() {
        this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
        this.mHeaderChrysanthemumIv.setVisibility(View.GONE);
        this.mHeaderChrysanthemumAd.stop();
        this.mHeaderArrowIv.setVisibility(View.VISIBLE);
    }
    @Override
    public void handleScale(float scale, int moveYDistance) {
    }

    @Override
    public void changeToIdle() {
    }


    /**
     * 设置加载文字
     * @param text
     */
    public void updateLoadingMoreText(String text){
        this.mFooterStatusTv.setText(text);
    }

    /**@方法名称: hideLoadingMoreImg
    * @方法详述:   隐藏加载更多图片
    * @参数:
    * @返回值:
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public void hideLoadingMoreImg(){
        this.mFooterChrysanthemumIv.setVisibility(View.GONE);
    }
    /**@方法名称: showLoadingMoreImg
    * @方法详述: 显示加载更多图片
    * @参数:
    * @返回值:
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public void showLoadingMoreImg(){
        this.mFooterChrysanthemumIv.setVisibility(View.VISIBLE);
    }
    /**@方法名称: getLoadMoreFooterView
    * @方法详述: 自定义加载更多底部
    * @参数:view 自定义view
    * @返回值:
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    @Override
    public View getLoadMoreFooterView() {
        if (!this.mIsLoadingMoreEnabled) {
            return null;
        }
        Log.i("TAG", "啦啦啦啦");
        if (this.mLoadMoreFooterView == null) {
            this.mLoadMoreFooterView = View.inflate(this.mContext, R.layout.footer_bga_dodo, null);
            this.mLoadMoreFooterView.setBackgroundColor(Color.TRANSPARENT);
            this.mFooterStatusTv = (TextView) this.mLoadMoreFooterView.findViewById(R.id.tv_normal_refresh_footer_status);
            this.mFooterChrysanthemumIv = (ImageView) this.mLoadMoreFooterView.findViewById(R.id.iv_normal_refresh_footer_chrysanthemum);
            this. mFooterChrysanthemumAd = (AnimationDrawable) this.mFooterChrysanthemumIv.getDrawable();
            this.mFooterStatusTv.setText(this.mLodingMoreText);
        }
        return mLoadMoreFooterView;
    }

}