package com.example1;

import akka.actor.Actor;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.japi.Creator;

/**
 * @author Feng Jie
 * @date 2023/5/8 18:06
 */
public class PropsDemoActor extends UntypedAbstractActor {
    @Override
    public void preStart() throws Exception {
        System.out.println("========");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("发送方是:" + getSender());
        getSender().tell("hello " + message, getSelf());
    }
    public static Props createProps(){
        return Props.create(new Creator<PropsDemoActor>() {
            @Override
            public PropsDemoActor create() throws Exception {
                return new PropsDemoActor();
            }
        });
    }
}
