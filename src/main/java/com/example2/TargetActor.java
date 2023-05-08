package com.example2;

import akka.actor.UntypedAbstractActor;

/**
 * @author Feng Jie
 * @date 2023/5/8 18:26
 */
public class TargetActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("收到转发消息："+message);
    }
}
