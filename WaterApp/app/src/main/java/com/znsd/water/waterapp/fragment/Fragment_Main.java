package com.znsd.water.waterapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.znsd.water.waterapp.R;
import com.znsd.water.waterapp.adapter.ImagePagerAdapter;
import com.znsd.water.waterapp.bannerview.CircleFlowIndicator;
import com.znsd.water.waterapp.bannerview.ViewFlow;

import java.util.ArrayList;


public class Fragment_Main extends Fragment {


    private ViewFlow mViewFlow;
    private CircleFlowIndicator mFlowIndicator;
    private ArrayList<String> imageUrlList = new ArrayList<String>();


    private static Fragment_Main main;

    public static Fragment_Main getInstance()

    {
        if (main == null) {
            main = new Fragment_Main();
        }
        return main;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
     * android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // isStop = false;
        View view = inflater.inflate(R.layout.fragment_mian, container, false);
        bindView(view);
        return view;
    }


    private void bindView(View view) {
        ///广告的加载
        mViewFlow = (ViewFlow) view.findViewById(R.id.viewflow);
        mFlowIndicator = (CircleFlowIndicator) view.findViewById(R.id.viewflowindic);

        /////界面初始化



        init();


    }

    /**
     * 先初始化图片地址
     * 在来做界面的加载
     *
     */
    private void init() {
        imageUrlList
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
        imageUrlList
                .add("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg");
        initBanner(imageUrlList);

    }

    /**
     * 加载广告图片
     * @param imageUrlList
     */
    private void initBanner(ArrayList<String> imageUrlList) {

        mViewFlow.setAdapter(new ImagePagerAdapter(getActivity(), imageUrlList).setInfiniteLoop(true));
        mViewFlow.setmSideBuffer(imageUrlList.size()); // 实际图片张数，
        // 我的ImageAdapter实际图片张数为3
        mViewFlow.setFlowIndicator(mFlowIndicator);
        mViewFlow.setTimeSpan(4500);
        mViewFlow.setSelection(imageUrlList.size() * 1000); // 设置初始位置
        mViewFlow.startAutoFlowTimer(); // 启动自动播放


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        imageUrlList.clear();
    }
}