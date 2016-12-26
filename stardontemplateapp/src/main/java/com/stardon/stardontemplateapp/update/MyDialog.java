package com.stardon.stardontemplateapp.update;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class MyDialog extends Dialog {
    private static Context context;
    private static int theme;

    public MyDialog(Context context) {
        super(context);
        this.context = context;
    }

    public MyDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
    }

    private MyDialog() {
        super(context);
    }

    private static MyDialog mDialog = null;

    //静态工厂方法   
    public static MyDialog getInstance() {
        if (mDialog == null) {
            mDialog = new MyDialog(context, theme);
        }
        return mDialog;
    }


}
