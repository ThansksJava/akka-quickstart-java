package com.learn1.example3;

import akka.actor.*;

import java.util.Optional;

/**
 * @author Feng Jie
 * @date 2023/5/8 18:26
 */
public class LookedUpActor extends UntypedAbstractActor {
    private ActorRef targetActor = getContext().actorOf(Props.create(TargetActor.class), "targetActor");
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String){
            if ("find".equals(message)){
                // 因为是当前的子级（本actor中创建的就是当前的子级）,直接这么写也行
                //ActorSelection targetSelection = getContext().actorSelection("targetActor");
                // 功能和上面一样
                ActorSelection targetSelection = getContext().actorSelection("/user/lookedUpActor/targetActor");
                targetSelection.tell(new Identify("A0001"), getSelf());

            }
        } else if (message instanceof ActorIdentity) {
            ActorIdentity ai = (ActorIdentity) message;
            if ("A0001".equals(ai.correlationId())){
                Optional<ActorRef> actorRefOpt = ai.getActorRef();
                actorRefOpt.ifPresent(actorRef -> {
                    actorRef.tell("hi target", getSelf());
                });
            }
        }else {
            unhandled(message);
        }
    }
}
