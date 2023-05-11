package com.learn1.example5;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * @author Feng Jie
 * @date 2023/5/9 11:06
 */
public class WorkerActor extends UntypedAbstractActor {
    LoggingAdapter log = Logging.getLogger( this.getContext() .system(), this);
    @Override
    public void onReceive(Object message) throws Throwable {
        log.info("收到消息：{}", message);
    }

    @Override
    public void postStop() throws Exception {
        log.info("worker 停止");
    }
}
