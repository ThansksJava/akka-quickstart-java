package com.example9;

import akka.actor.UntypedAbstractActor;

/**
 * @author Feng Jie
 * @date 2023/5/9 17:53
 */
public class Subscriber extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println(getSelf()+":"+message);
    }
}
