package com.learn1.example7.router;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import com.learn1.example7.RouteeActor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Feng Jie
 * @date 2023/5/9 16:18
 */
public class RouterActor extends UntypedAbstractActor {
    private Router router;
    @Override
    public void preStart() throws Exception {
        List<Routee> routeeActorList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            routeeActorList.add(new ActorRefRoutee(getContext().actorOf(Props.create(RouteeActor.class), "routee"+i)));
        }
        router = new Router(new RoundRobinRoutingLogic(), routeeActorList);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        router.route(message, getSender());
    }
}
