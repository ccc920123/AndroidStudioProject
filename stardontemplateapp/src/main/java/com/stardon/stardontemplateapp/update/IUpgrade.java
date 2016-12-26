package com.stardon.stardontemplateapp.update;

/**
 * @类名: IUpgrade
 * @功能描述: 升级界面的相关接口
 * @作者: MouTao
 * @时间: 2016·12·6
 * @最后修改者:
 * @最后修改内容:
 */
public interface IUpgrade {
    /**
     * <br/> 方法名称: getIntentData
     * <br/> 方法详述: 获取传递过来的相关东西，有Url，是否强制升级
     * <br/>           版本号，版本内容等。此方法只用于数据获取，逻辑处理在
     * <br/> 参数:
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    void getIntentData();
}
//jhfghfh