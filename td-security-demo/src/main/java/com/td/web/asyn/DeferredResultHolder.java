package com.td.web.asyn;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: DeferredResultHolder
 * @description: 第四块代码。两个线程之间信息交互
 * @author: cyd
 * @date: 2019/12/1 下午6:33
 **/
@Component
public class DeferredResultHolder {

    /**
     * key：为订单号
     * value：订单处理的结果
     */
    public Map<String , DeferredResult<String>> map = new HashMap<>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
