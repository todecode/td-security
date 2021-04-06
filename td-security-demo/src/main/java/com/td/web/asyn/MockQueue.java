package com.td.web.asyn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @className: MockQueue
 * @description: 模拟消息队列
 * @author: cyd
 * @date: 2019/12/1 下午6:28
 **/
@Component
public class MockQueue {
    private Logger log = LoggerFactory.getLogger(MockQueue.class);
    /**
     * 下单消息
     */
    private String placeOrder;

    /**
     * 订单完成消息
     */
    private String comPlaceOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        // 模拟下单处理流程，相当图示应用2
        // 线程2，处理下单逻辑
        new Thread(()->{
            log.info("接到下单请求"+placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.comPlaceOrder = placeOrder;
            log.info("下单处理完毕,"+placeOrder);
        }).start();
    }

    public String getComPlaceOrder() {
        return comPlaceOrder;
    }

    public void setComPlaceOrder(String comPlaceOrder) {
        this.comPlaceOrder = comPlaceOrder;
    }
}
