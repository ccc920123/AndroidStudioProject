package com.stardon.stardontemplateapp.pictruecameralistview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stardon.stardontemplateapp.R;


/**类名: ConfirmDialog
 * <br/>功能描述:确认对话框。使用builder创建对象
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/6
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class ConfirmDialog extends Dialog {
	/**
	 * 标题框
	 */
	private TextView titleView;
	/**
	 * 自定义布局
	 */
	private RelativeLayout messageView;
	/**
	 * 按钮1
	 */
	private TextView buttonLeft;
	/**
	 * 按钮2
	 */
	private TextView buttonRight;
	/**
	 * 上下文
	 */
	private static Context context;
	/**
	 * 实例
	 */
	private static ConfirmDialog instance;

	/**
	 * 构造器
	 *
	 * @param context
	 */
	private ConfirmDialog(Context context) {
		super(context, R.style.Theme_Light_FullScreenDialogAct);
		setContentView(R.layout.dialog_confirm);
		titleView = (TextView) findViewById(R.id.dialog_confirm_title);
		messageView = (RelativeLayout) findViewById(R.id.dialog_confirm_message);
		buttonLeft = (TextView) findViewById(R.id.dialog_confirm_left);
		buttonRight = (TextView) findViewById(R.id.dialog_confirm_right);
		this.context = context;
	}

/**类名: Builder
 * <br/>功能描述:dialog创建者Builder
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/6
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

	public static class Builder {
		private String title;
		private View layoutView;
		private String left;
		private View.OnClickListener leftListener;
		private String right;
		private View.OnClickListener rightListener;
		private Context context;

		/**
		 *
		 * @param context
		 */
		private Builder(Context context) {
			this.context = context;
		}

		/**
		 *
		 * @Description 设置标题
		 * @param title
		 * @return
		 */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 *
		 * @Description 设置布局
		 * @param view
		 * @return
		 */
		public Builder setLayoutView(View view) {
			this.layoutView = view;
			return this;
		}

		/**
		 *
		 * @Description 设置按钮
		 * @param left
		 * @param listener
		 * @return
		 */
		public Builder setButtonLeft(String left, View.OnClickListener listener) {
			this.left = left;
			leftListener = listener;
			return this;
		}

		/**
		 *
		 * @Description 设置按钮2
		 * @param right
		 * @param listener
		 * @return
		 */
		public Builder setButtonRight(String right,
									  View.OnClickListener listener) {
			this.right = right;
			rightListener = listener;
			return this;
		}

		/**
		 *
		 * @Description 创建dialog
		 * @CreateDate 2016-8-19下午5:36:07
		 * @return
		 */
		public ConfirmDialog build() {
			if (ConfirmDialog.context == this.context
					&& ConfirmDialog.instance != null) {
				ConfirmDialog.instance.dismiss();
			}
			ConfirmDialog dialog = new ConfirmDialog(context);
			dialog.titleView.setText(title);
			dialog.messageView.addView(layoutView);
			dialog.buttonLeft.setText(left);
			dialog.buttonLeft.setOnClickListener(leftListener);
			dialog.buttonRight.setText(right);
			dialog.buttonRight.setOnClickListener(rightListener);
			ConfirmDialog.instance = dialog;
			return dialog;
		}
	}

/**方法名称: getBuilder
* <br/>方法详述: 获取builder实例
* <br/>参数:
* <br/>返回值:
* <br/>异常抛出 Exception:
* <br/>异常抛出 NullPointerException:
*/

	public static Builder getBuilder(Context context) {
		return new Builder(context);
	}
}
