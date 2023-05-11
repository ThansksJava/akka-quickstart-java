package com.learn1.example7.pool;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.routing.*;
import com.learn1.example7.RouteeActor;

/**
 * @author Feng Jie
 * @date 2023/5/9 16:18
 */
public class PoolRouterActor extends UntypedAbstractActor {
    private ActorRef router;
    @Override
    public void preStart() throws Exception {
        Props props = new RoundRobinPool(2).props(Props.create(RouteeActor.class));
        router = getContext().actorOf(props, "poolRouterActor");
        System.out.println(router);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        router.tell(message, getSender());
    }
}
