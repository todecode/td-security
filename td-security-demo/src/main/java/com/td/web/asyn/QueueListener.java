package com.td.web.asyn;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @className: QueueListener
 * @description: 队列监听器
 * ContextRefreshedEvent事件，是整个spring初始化监听的事件
 * @author: cyd
 * @date: 2019/12/1 下午6:42
 **/
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 代码在启动过程中执行。若不是用多线程，则会造成整个系统启动阻塞
        // 线程3，接收处理返回的结果
        new Thread(()->{
            while (true){
                if(StringUtils.isNotBlank(this.mockQueue.getComPlaceOrder())){
                    String orderNumber = this.mockQueue.getComPlaceOrder();
                    log.info("返回订单处理结果，"+orderNumber);
                    this.deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
                    this.mockQueue.setComPlaceOrder(null );
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
