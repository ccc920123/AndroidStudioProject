package com.stardon.stardontemplateapp.pictruecameralistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.stardon.stardontemplateapp.R;

import java.util.List;

/**
 * 类名: ListViewActivityLib
 * <br/>功能描述: 照片list列表
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/5
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public abstract class ListViewActivityLib extends AppCompatActivity implements IListBase {

    /**
     * 其他照片
     */
    private static LinearLayout mOtherpictrue;
    /**
     * 照片列表
     */
    private ListView mRecycler;
    /**
     * 照片适配器
     */
    private CommonAdapter<RecyclerItemBean> mAdapter;
    /**
     * 数据
     */
    private static List<RecyclerItemBean> itemData;

    /**
     * 其他照片选择框
     */
    private ConfirmDialog confDialog;

    private  int mLayout=0;
    /**
     * 其他照片下拉选择
     */
    private Spinner picSpinner;
    /**
     * 自定义view
     */
    View view;
    /**
     * 下拉选择其他照片spinner
     */
    ArrayAdapter<PictrueBean> adapter;
    /**
     * 默认照片的列表集合
     */
    static List<PictrueBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewlib);
        initfindView();
        bindView(savedInstanceState);
        init();

    }

    /**
     * 方法名称: init
     * <br/>方法详述: 处理逻辑
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void init() {
        if(itemData==null)
        {
            throw  new RuntimeException("请调用setData(List<RecyclerItemBean> data)设置拍照list的数据");
        }
        mRecycler.setAdapter(mAdapter = new CommonAdapter<RecyclerItemBean>(getApplicationContext(), itemData, R.layout.item_recyclerview) {
            @Override
            public void convert(ViewHolder helper, RecyclerItemBean item) {
                helper.setText(R.id.picname, item.getItemName());
                helper.setCheck(R.id.checkstat, item.isItemStat());
            }
        });
        mRecycler.setOnItemClickListener(itemClcik);

        mOtherpictrue.setOnClickListener(click);


    }

    private AdapterView.OnItemClickListener itemClcik = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            listViewOnItemClickListener(parent, view, position, id, itemData);

        }
    };


    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            confDialog = ConfirmDialog.getBuilder(ListViewActivityLib.this).setLayoutView(getCustomLayout(0))
                    .setButtonLeft("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confDialog.dismiss();
                        }
                    }).setButtonLeft("拍照", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confDialog.dismiss();

                         otherPictrueDialogOKButtonClick(v,itemData);

                        }
                    }).build();
            confDialog.show();


        }
    };

 /**方法名称: getCustomLayout
 * <br/>方法详述: 自定义对话框的内容布局
 * <br/>参数:
 * <br/>返回值:
 * <br/>异常抛出 Exception:
 * <br/>异常抛出 NullPointerException:
 */

    private  View  getCustomLayout(int Layout)
    {

        mLayout=Layout;
        if(mLayout!=0) {
            view = LayoutInflater.from(this).inflate(mLayout, null);
        }else{
            view = LayoutInflater.from(this).inflate(R.layout.dialog_spiner, null);
            picSpinner= (Spinner) view.findViewById(R.id.spinner);
            if(data==null)
            {
                throw  new RuntimeException("请调用setSpinnerArryayData（List<PictrueBean> itemotherdatas）设置其他照片的数据");
            }
            adapter=new ArrayAdapter<PictrueBean>(this,android.R.layout.simple_spinner_item,data);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            picSpinner.setAdapter(adapter);
        }
        return  view;
    }

    /**
     * 方法名称: initfindView
     * <br/>方法详述: 找控件
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void initfindView() {
        mOtherpictrue = (LinearLayout) findViewById(R.id.otherpictrue);
        mRecycler = (ListView) findViewById(R.id.recycler);


    }

    /**
     * 方法名称: setData
     * <br/>方法详述: 设置（list）拍照的数据
     * <br/>参数: List<RecyclerItemBean> data    拍照的数据
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static void setData(List<RecyclerItemBean> data) {
        itemData = data;
    }

    /**
     * 方法名称: isVisibility
     * <br/>方法详述: 设置其他照片layout是否显示
     * <br/>参数: visibility  true  或者false     true 显示，false不显示
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static void isVisibility(boolean visibility) {
        if (visibility)
            mOtherpictrue.setVisibility(View.VISIBLE);
        else
            mOtherpictrue.setVisibility(View.GONE);

    }

     /**方法名称: setSpinnerArryayData
     * <br/>方法详述: 其他照片的值
     * <br/>参数: itemotherdatas     List<PictrueBean>
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public  static void  setSpinnerArryayData(List<PictrueBean> itemotherdatas)
    {
       data=itemotherdatas;
    }

}
