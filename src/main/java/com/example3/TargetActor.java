package com.example3;

import akka.actor.UntypedAbstractActor;

/**
 * @author Feng Jie
 * @date 2023/5/8 18:26
 */
public class TargetActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("收到消息："+message);
    }
}
