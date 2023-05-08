package com.example2;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * @author Feng Jie
 * @date 2023/5/8 18:26
 */
public class ForwardActor extends UntypedAbstractActor {
    private ActorRef targetActor = getContext().actorOf(Props.create(TargetActor.class), "targetActor");
    @Override
    public void onReceive(Object message) throws Throwable {
        targetActor.forward(message, getContext());
    }
}
