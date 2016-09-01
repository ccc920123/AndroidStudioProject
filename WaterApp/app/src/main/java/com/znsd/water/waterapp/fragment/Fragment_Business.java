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


public class Fragment_Business extends Fragment {

    private ViewFlow mViewFlow;
    private CircleFlowIndicator mFlowIndicator;
    private ArrayList<String> imageUrlList = new ArrayList<String>();


    private TextView mTextViewzxcz;
    private TextView mTextViewgzsb;
    private TextView mTextViewzxkf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_business, container, false);
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        mViewFlow = (ViewFlow) view.findViewById(R.id.viewflow);
        mFlowIndicator = (CircleFlowIndicator) view.findViewById(R.id.viewflowindic);

        mTextViewzxcz = (TextView) view.findViewById(R.id.zxcz);
        mTextViewgzsb = (TextView) view.findViewById(R.id.gzsb);
        mTextViewzxkf = (TextView) view.findViewById(R.id.zxkf);

        init();
    }

    private void init() {
        if (imageUrlList.size() > 0) {
            imageUrlList.clear();
        }
        imageUrlList
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg");
        initBanner(imageUrlList);
        mTextViewzxcz.setOnClickListener(click);
        mTextViewgzsb.setOnClickListener(click);
        mTextViewzxkf.setOnClickListener(click);


    }

    private void initBanner(ArrayList<String> imageUrlList) {
        mViewFlow.setAdapter(new ImagePagerAdapter(getActivity(), imageUrlList).setInfiniteLoop(true));
        mViewFlow.setmSideBuffer(imageUrlList.size()); // 实际图片张数，
        // 我的ImageAdapter实际图片张数为3
        mViewFlow.setFlowIndicator(mFlowIndicator);
        mViewFlow.setTimeSpan(4500);
        mViewFlow.setSelection(imageUrlList.size() * 1000); // 设置初始位置
        mViewFlow.startAutoFlowTimer(); // 启动自动播放


    }

    private View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.zxcz:

                    break;
                case R.id.gzsb:

                    break;
                case R.id.zxkf:

                    break;

            }


        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        imageUrlList.clear();
    }

}
