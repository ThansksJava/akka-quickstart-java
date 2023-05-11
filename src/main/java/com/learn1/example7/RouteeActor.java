package com.learn1.example7;

import akka.actor.UntypedAbstractActor;

/**
 * @author Feng Jie
 * @date 2023/5/9 16:17
 */
public class RouteeActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(getSelf() + "---->" +message + "| parent:" + getContext().getParent() );
    }
}
