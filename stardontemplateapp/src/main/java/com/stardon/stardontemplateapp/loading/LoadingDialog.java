package com.stardon.stardontemplateapp.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stardon.stardontemplateapp.R;

/**
 * 类名: LoadingDialog
 * <br/>功能描述:LoadingDialog的对话框  定义了对话框显示的类型，以及是否显示请等待的字样，颜色大小
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/2
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */


public class LoadingDialog extends Dialog {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 动画框
     */
    private LinearLayout animation;
    /**
     * 消息
     */
    private TextView message;
    /**
     * 动画View
     */
    private LoadingIndicatorView view;
    /**
     * 字体颜色，默认和动画View颜色一致。
     */
    private int textColor;

    /**
     * 默认样式为LoadingIndicatorView的默认样式
     *
     * @param context
     */
    public LoadingDialog(Context context) {
        super(context, R.style.Theme_Light_FullScreenDialogAct);
        init(context, new LoadingIndicatorView(context));
    }

    /**
     * @param context
     * @param view    LoadingIndicatorView样式
     */
    public LoadingDialog(Context context, LoadingIndicatorView view) {
        super(context, R.style.Theme_Light_FullScreenDialogAct);
        init(context, view);
    }

    /**
     * @param context
     * @param view    LoadingIndicatorView样式
     * @param message 会显示消息
     */
    public LoadingDialog(Context context, LoadingIndicatorView view, String message) {
        super(context, R.style.Theme_Light_FullScreenDialogAct);
        init(context, view);
        if (message != null && message.length() > 0) {
            this.message.setText(message);
            this.message.setVisibility(View.VISIBLE);
        }
    }
/**方法名称: init
* <br/>方法详述: 处理等待框的入口
* <br/>参数:
* <br/>返回值:
* <br/>异常抛出 Exception:
* <br/>异常抛出 NullPointerException:
*/

    public void init(Context context, LoadingIndicatorView view) {
        this.context = context;
        setContentView(R.layout.common_progress_dialog);
        animation = (LinearLayout) findViewById(R.id.progress_dialog_animation);
        this.view = view;
        this.textColor = view.getColor();
        animation.addView(view);
        message = (TextView) findViewById(R.id.progress_dialog_message);
        message.setTextColor(this.textColor);
        setCancelable(false);// 默认不可取消
        setCanceledOnTouchOutside(false);
    }

    /**
     * @param message 设置并显示消息
     */
    public void setMessage(String message) {
        this.message.setText(message);
        this.message.setVisibility(View.VISIBLE);
    }

    /**
     * @return textColor 字体颜色，默认和动画View颜色一致。
     */
    public int getTextColor() {
        return textColor;
    }

    /**
     * @param textColor 字体颜色ID，默认和动画View颜色一致。
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
        this.message.setTextColor(this.context.getResources().getColor(
                this.textColor));
    }

    /**
     * @param color String 类型 ff0f81d9
     *              颜色ID
     * @Description 设置动画View颜色
     */
    public void setColor(String color) {
        view.setColor(color);
    }

    /**
     * @return view 动画View
     */
    public LoadingIndicatorView getView() {
        return view;
    }

    /**
     * @param view 动画View
     */
    public void setView(LoadingIndicatorView view) {
        this.view = view;
    }

    /**
     * @param textSize 字体大小， 和TextView的setTextSize（）相同。
     * @Description 设置字体大小
     */
    public void setTextSize(int textSize) {
        message.setTextSize(textSize);
    }

    /**
     * @param size 动画view大小，单位dip，默认45dip，
     * @Description 设置动画View的大小
     */
    public void setViewSize(int size) {
        view.setSize(size);
    }
}
