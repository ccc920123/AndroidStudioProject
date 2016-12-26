package com.stardon.stardontemplateapp.loading;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 类名: Loading   <H3>入口</H3>
 * <br/>功能描述:Loading  在使用时用该类直接调用该类中的方法，如果你要使用自定义控件，请直接使用到
 * <br/> LoadingIndicatorView,这里有28中样式。通过LoadingIndicatorView.BallPulse(方法来时样式切换)
 * <br/> 方法依次：与下图对应
 * <br/> BallPulse,BallGridPulse, BallClipRotate, BallClipRotatePulse,
 * <br/>SquareSpin,BallClipRotateMultiple,BallPulseRise,BallRotate,
 * <br/> CubeTransition,BallZigZag,BallZigZagDeflect,BallTrianglePath,
 * <br/> BallScale,LineScale,LineScaleParty,BallScaleMultiple,
 * <br/> BallPulseSync,BallBeat,LineScalePulseOut,LineScalePulseOutRapid,
 * <br/>BallScaleRipple,BallScaleRippleMultiple,BallSpinFadeLoader,LineSpinFadeLoader,
 * <br/>TriangleSkewSpin,Pacman,BallGridBeat,SemiCircleSpin
 * <br/><img width="234" height="273" src="../../../../../res/drawable/loading.gif" />
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/2
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */


public class Loading {

    /**
     * 定义对话框
     */
    public static LoadingDialog loadingDialog;
    /**
     * 计时器，
     */
    private static Timer timer;
    /**
     * 字体大小，默认是和消息等待框相同
     */
    private static int loadsize=0;
    /**
     * 设置load图标大小
     */
    private static int loadingSize=0;

    /**
     * @param context
     * @Description 显示一个自定义ProgressDialog，默认不可取消，通过Dialogs.setCloseAble()设置可取消。
     */
    public static void alertProgress(Context context) {
        alertProgress(context,LoadingIndicatorView.BallSpinFadeLoader);
    }

    /**
     * @param context
     * @param style    ProgressView有28种样式可选，创建的时候使用LoadingIndicatorView.XX或直接传入数字选择样式，
     *                还可以自定义颜色和大小
     * @Description 显示一个自定义ProgressDialog，默认不可取消，通过Dialogs.setCloseAble()设置可取消。
     */
    public static void alertProgress(Context context,int style) {
        alertProgress(context, -1,style);
    }

    /**
     * @param context
     * @param style    ProgressView有28种样式可选，创建的时候使用ProgressView.XX或直接传入数字选择样式，
     *                还可以自定义颜色和大小
     * @param message 消息,消息颜色默认和LoadingIndicatorView一致，可使用Dialogs.progressDialog.
     *                setTextColor()设置颜色
     * @Description 显示一个自定义ProgressDialog，默认不可取消，通过Dialogs.setCloseAble()设置可取消。
     */
    public static void alertProgress(Context context,
                                     String message,int style) {
        alertProgress(context, message, -1,style);
    }

    /**
     * @param context
     * @param style        ProgressView有28种样式可选，创建的时候使用LoadingIndicatorView.XX或直接传入数字选择样式，
     *                    还可以自定义颜色和大小
     * @param maxWaitTime 等待时间
     * @Description 显示一个自定义ProgressDialog
     */
    public static void alertProgress(Context context,
                                     long maxWaitTime,int style) {
        alertProgress(context, null, maxWaitTime,style);
    }

    /**
     * @param context
     * @param style        ProgressView有28种样式可选，创建的时候使用LoadingIndicatorView.XX或直接传入数字选择样式，
     *                    还可以自定义颜色和大小
     * @param message     消息,消息颜色默认和LoadingIndicatorView一致，可使用Dialogs.progressDialog.
     *                    setTextColor()设置颜色
     * @param maxWaitTime 等待时间
     * @Description 显示一个自定义ProgressDialog，默认不可取消，通过Dialogs.setCloseAble()设置可取消。
     */
    public static void alertProgress(Context context,
                                     String message, long maxWaitTime,int style) {
        try {
            closeLoadingDialog();
            if(loadingSize!=0) {
                LoadingIndicatorView.setSize(loadingSize);
            }
            LoadingIndicatorView.setStyle(style);
            loadingDialog = new LoadingDialog(context, new LoadingIndicatorView(context), message);
           if(loadsize!=0) {
               loadingDialog.setTextSize(loadsize);
           }
            loadingDialog.show();
            if (maxWaitTime > 1000) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        closeLoadingDialog();
                    }
                }, maxWaitTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 /**方法名称: closeLoadingDialog
 * <br/>方法详述: 关闭消息等待框
 * <br/>参数:
 * <br/>返回值:
 * <br/>异常抛出 Exception:
 * <br/>异常抛出 NullPointerException:
 */

    public static void closeLoadingDialog() {
        try {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
        } catch (Exception e) {
        }

        try {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        } catch (Exception e) {
        }
    }

    /**方法名称: setTextSize
    * <br/>方法详述: 设置文字大小
    * <br/>参数:
    * <br/>返回值:
    * <br/>异常抛出 Exception:
    * <br/>异常抛出 NullPointerException:
    */

   public static void setTextSize(int size)
    {

        loadsize=size;

    }

 /**方法名称: setLoadingSize
 * <br/>方法详述: 设置图标大小
 * <br/>参数: 
 * <br/>返回值: 
 * <br/>异常抛出 Exception:
 * <br/>异常抛出 NullPointerException:
 */ 
 
    public  static  void setLoadingSize(int loadSize)
    {
        loadingSize=loadSize;
    }
}
