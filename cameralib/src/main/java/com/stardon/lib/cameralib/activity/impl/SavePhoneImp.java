package com.stardon.lib.cameralib.activity.impl;

import android.graphics.Bitmap;
import android.view.View;
/**类名: SavePhoneImp
 * <br/>功能描述: 保存照片界面的回调事件，提供得到“返回按钮”，
 * <br/>”重拍按钮“，"保存按钮","得到照片数据byte"，回调事件。
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/14
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */



public interface SavePhoneImp {
    /**方法名称: getPhoneBack
    * <br/>方法详述: 得到返回按钮，做你想做的事情
    * <br/>参数:
    * <br/>返回值:view你需要的返回按钮
    * <br/>异常抛出 Exception:
    * <br/>异常抛出 NullPointerException:
    */

    public void getPhoneBack(View view);
    /**方法名称: getPhoneCancel
     * <br/>方法详述: 得到重拍按钮，该重拍按钮的事件已经处理好了不需要你重新定义事，\
     * <br/>该处提供按钮，是方便你对按钮设置显示的字，背景，动画
     * <br/>参数:
     * <br/>返回值: view你需要的重拍按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public void getPhoneCancel(View view);
    /**方法名称: getPhoneSave
     * <br/>方法详述: 得到保存按钮，做你想做的事情，你可以将图片上传到服务器，
     * <br/>参数:
     * <br/>返回值:view你需要的返回按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public void getPhoneSave(View view);
    /**方法名称: getPhoneDate
     * <br/>方法详述: 得到照片的数据byte[如果添加水印，该数据返回时没有没有加水印]
     * <br/>参数:
     * <br/>返回值:view你需要的返回按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public void getPhoneDate(byte[] data);
    /**方法名称: getPhoneDate
     * <br/>方法详述: 得到照片的数据Bitmap[如果添加水印，该bitmap已经添加水印了]
     * <br/>参数:
     * <br/>返回值:view你需要的返回按钮
     *   Environment
     .getExternalStorageDirectory().getAbsolutePath() + "/Camera/video/";
     * //        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
     //                .format(new Date());
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public void getPhoneDate(Bitmap bitmap);

}
