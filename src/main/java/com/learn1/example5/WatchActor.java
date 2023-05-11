package com.learn1.example5;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author Feng Jie
 * @date 2023/5/9 11:04
 */
public class WatchActor extends UntypedAbstractActor {
    LoggingAdapter log = Logging.getLogger( this.getContext() .system(), this);
    ActorRef child = null;
    @Override
    public void preStart() throws Exception {
        child = getContext().actorOf(Props.create(WorkerActor.class), "workerActor");
        getContext().watch(child);
    }

    @Override
    public void postStop() throws Exception {
        log.info ( "WatchActor posts top");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String){
            if ("stop".equals(message)){
                getContext().stop(child);
            }
        } else if (message instanceof Terminated) {
            Terminated ter = (Terminated) message;
            log.info("监控到：{} 停止了", ter.getActor());
        }
    }
}
