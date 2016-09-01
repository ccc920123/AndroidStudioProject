package com.znsd.water.waterapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.znsd.water.waterapp.R;
import com.znsd.water.waterapp.adapter.ImagePagerAdapter;
import com.znsd.water.waterapp.bannerview.CircleFlowIndicator;
import com.znsd.water.waterapp.bannerview.ViewFlow;

import java.util.ArrayList;


public class Fragment_Main extends Fragment {


    private ViewFlow mViewFlow;
    private CircleFlowIndicator mFlowIndicator;
    private ArrayList<String> imageUrlList = new ArrayList<String>();

    private ViewFlow mViewFlow2;
    private CircleFlowIndicator mFlowIndicator2;
    private ArrayList<String> imageUrlList2 = new ArrayList<String>();

    private static Fragment_Main main;


    private TextView mTextViewzhf;
    private TextView mTextViewsf;
    private TextView mTextViewdf;
    private TextView mTextViewrqf;
    private TextView mTextViewlqf;
    private TextView mTextViewwyf;
    private TextView mTextViewds;
    private TextView mTextViewsg;
    private TextView mTextViewgs;
    private TextView mTextViewxn;
    private TextView mTextViewxh;
    private TextView mTextViewmore;


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


        mViewFlow2 = (ViewFlow) view.findViewById(R.id.viewflow1);
        mFlowIndicator2 = (CircleFlowIndicator) view.findViewById(R.id.viewflowindic1);
        /////界面初始化
        mTextViewzhf = (TextView) view.findViewById(R.id.zhf);
        mTextViewsf = (TextView) view.findViewById(R.id.sf);
        mTextViewdf = (TextView) view.findViewById(R.id.df);
        mTextViewrqf = (TextView) view.findViewById(R.id.rqf);
        mTextViewlqf = (TextView) view.findViewById(R.id.lqf);
        mTextViewwyf = (TextView) view.findViewById(R.id.wyf);
        mTextViewds = (TextView) view.findViewById(R.id.ds);
        mTextViewsg = (TextView) view.findViewById(R.id.sg);
        mTextViewgs = (TextView) view.findViewById(R.id.js);
        mTextViewxn = (TextView) view.findViewById(R.id.xn);
        mTextViewxh = (TextView) view.findViewById(R.id.xh);
        mTextViewmore = (TextView) view.findViewById(R.id.more);

        init();


    }

    /**
     * 先初始化图片地址
     * 在来做界面的加载
     */
    private void init() {
        if (imageUrlList.size() > 0) {
            imageUrlList.clear();
        }
        if (imageUrlList2.size() > 0) {
            imageUrlList2.clear();
        }

        imageUrlList
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
        imageUrlList
                .add("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg");
        initBanner(imageUrlList);

        imageUrlList2
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList2
                .add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
        imageUrlList2
                .add("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg");
        imageUrlList2
                .add("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg");
        initBanner2(imageUrlList2);
        //点击事件
        mTextViewzhf.setOnClickListener(click);
        mTextViewsf.setOnClickListener(click);
        mTextViewdf.setOnClickListener(click);
        mTextViewrqf.setOnClickListener(click);
        mTextViewlqf.setOnClickListener(click);
        mTextViewwyf.setOnClickListener(click);
        mTextViewds.setOnClickListener(click);
        mTextViewsg.setOnClickListener(click);
        mTextViewgs.setOnClickListener(click);
        mTextViewxn.setOnClickListener(click);
        mTextViewxh.setOnClickListener(click);
        mTextViewmore.setOnClickListener(click);


    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.zhf:

                    break;
                case R.id.sf:

                    break;
                case R.id.df:

                    break;
                case R.id.rqf:

                    break;
                case R.id.lqf:

                    break;
                case R.id.wyf:

                    break;
                case R.id.ds:

                    break;
                case R.id.sg:

                    break;
                case R.id.js:

                    break;
                case R.id.xn:

                    break;
                case R.id.xh:

                    break;
                case R.id.more:

                    break;


            }
        }
    };

    /**
     * 加载广告图片
     *
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

    /**
     * 加载广告图片2
     *
     * @param imageUrlList2
     */
    private void initBanner2(ArrayList<String> imageUrlList2) {

        mViewFlow2.setAdapter(new ImagePagerAdapter(getActivity(), imageUrlList2).setInfiniteLoop(true));
        mViewFlow2.setmSideBuffer(imageUrlList2.size()); // 实际图片张数，
        // 我的ImageAdapter实际图片张数为3
        mViewFlow2.setFlowIndicator(mFlowIndicator2);
        mViewFlow2.setTimeSpan(4500);
        mViewFlow2.setSelection(imageUrlList2.size() * 1000); // 设置初始位置
        mViewFlow2.startAutoFlowTimer(); // 启动自动播放


    }

    public View getmViewFlowView() {
        // TODO Auto-generated method stub
        return mViewFlow;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        imageUrlList.clear();
        imageUrlList2.clear();
    }
}