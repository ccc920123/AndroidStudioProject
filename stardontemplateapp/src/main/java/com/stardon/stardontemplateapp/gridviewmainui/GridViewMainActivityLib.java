package com.stardon.stardontemplateapp.gridviewmainui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stardon.stardontemplateapp.R;

import java.util.ArrayList;
import java.util.List;
/**类名: GridViewMainActivityLib
 * <br/>功能描述:主界面默认主界面运行如下，你可以自定义
 * <br/><img src="../../../../../res/drawable/mianpre.png" />
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/5
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public abstract class GridViewMainActivityLib extends AppCompatActivity implements IBase {


    private static String[] titles = {"照片采集"};
    private static int[] imageId = {R.drawable.cameraadd};
    private ViewPager mPager;
    private List<View> mPagerList;
    private List<Model> mDatas;
    private LinearLayout mLlDot;
    private LayoutInflater inflater;
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 9;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;
    /**
     * 自定义用户标题
     */
    private static RelativeLayout customUser;
    /**
     * 默认用户标题
     */
    private static LinearLayout defaultUser;
    /**
     * 头像
     */
    private static ImageView mImagehead;
    /**
     * 标签姓名
     */
    private static TextView mLablenametext;
    /**
     * 姓名
     */
    private static TextView mNametext;
    /**
     * 标签岗位
     */
    private static TextView mLablejobtext;
    /**
     * 岗位
     */
    private static TextView mJodtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridviewmainlib);
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mLlDot = (LinearLayout) findViewById(R.id.ll_dot);
        customUser = (RelativeLayout) findViewById(R.id.customuser);
        defaultUser = (LinearLayout) findViewById(R.id.defaultuser);
        mImagehead = (ImageView) findViewById(R.id.imagehead);
        mLablenametext = (TextView) findViewById(R.id.lablenametext);
        mNametext = (TextView) findViewById(R.id.nametext);
        mLablejobtext = (TextView) findViewById(R.id.lablejobtext);
        mJodtext = (TextView) findViewById(R.id.jodtext);
        //加载逻辑入口（接口）
        bindView(savedInstanceState);
        //初始化数据源
        initDatas();
        inflater = LayoutInflater.from(this);
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<View>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
            gridView.setAdapter(new GridViewAdapter(this, mDatas, i, pageSize));
            mPagerList.add(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + curIndex * pageSize;
                    gridViewOnItemClickListener(parent,view,pos,id,mDatas);
//                    Toast.makeText(GridViewMainActivityLib.this, mDatas.get(pos).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        //设置适配器
        mPager.setAdapter(new ViewPagerAdapter(mPagerList));
        //设置圆点
        setOvalLayout();
    }

    /**
     * 初始化数据源
     */
    private void initDatas() {
        mDatas = new ArrayList<Model>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
//            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getPackageName());

            mDatas.add(new Model(titles[i], imageId[i]));
        }
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        if(pageCount==1)
        {
            return;
        }

        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }

    /**
     * 方法名称: setDefualtUserIsVisibility
     * <br/>方法详述: 设置默认的用户信息是否显示，默认情况为true显示
     * <br/>参数:  visibility  为true  或者 false  true 显示，false 隐藏
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static void setDefualtUserIsVisibility(boolean visibility) {
        if (visibility) {
            defaultUser.setVisibility(View.VISIBLE);
        } else {
            defaultUser.setVisibility(View.GONE);
        }
    }

    /**
     * 方法名称: setCustomUserIsVisibility
     * <br/>方法详述: 自定义控件是否显示，默认情况为false隐藏
     * <br/>参数: visibility  为true  或者 false  true 显示，false 隐藏
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static void setCustomUserIsVisibility(boolean visibility) {
        if (visibility) {
            customUser.setVisibility(View.VISIBLE);
        } else {
            customUser.setVisibility(View.GONE);
        }
    }

    /**
     * 方法名称: setCustomUserLayout
     * <br/>方法详述: 设置自定义用户信息的布局文件，
     * <br/>参数: view    用户在传layout时将整个layout转换成View，
     * <br/>这样即可方便在子类中找到View并且设置事件
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static void setCustomUserLayout(View view) {
        customUser.addView(view);

    }

    /**
     * 方法名称: getmJodtext
     * <br/>方法详述: 得到岗位控件
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */


    public static TextView getmJodtext() {
        return mJodtext;
    }

    /**
     * 方法名称: getmLablejobtext
     * <br/>方法详述: 得到岗位标签
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static TextView getmLablejobtext() {
        return mLablejobtext;
    }

    /**
     * 方法名称: getmNametext
     * <br/>方法详述: 得到姓名控件
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static TextView getmNametext() {
        return mNametext;
    }

    /**
     * 方法名称: getmLablenametext
     * <br/>方法详述: 得到姓名标签
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static TextView getmLablenametext() {
        return mLablenametext;
    }

    /**
     * 方法名称: getmImagehead
     * <br/>方法详述: 得到头像控件
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static ImageView getmImagehead() {
        return mImagehead;
    }

    /**
     * 方法名称: setGridViewDataTitle
     * <br/>方法详述: 设置主界面功能item的标题,item 图片id(drawable,mipmap)
     * <br/>参数: title：标题，id：图片
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static void setGridViewData(String[] title, int[] id) {
        if(title.length!=id.length)
        {
            throw new RuntimeException("标题和图标集合不相等");
        }
        titles = title;
        imageId = id;
    }


}

