package com.learn1.example6;

import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.Optional;

/**
 * @author Feng Jie
 * @date 2023/5/9 11:06
 */
public class WorkerActor extends UntypedAbstractActor {
    LoggingAdapter log = Logging.getLogger( this.getContext() .system(), this);
    private Integer stateCount = 1;
    @Override
    public void onReceive(Object message) throws Throwable {
        this.stateCount++;
        if (message instanceof Exception) {
            throw ((Exception) message);
        } else if ("getValue".equals(message)) {
            getContext().actorSelection("/user/supervisorActor").tell(stateCount, getSelf());
        }else {
            unhandled(message);
        }

    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        log.info("worker 停止");
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        log.info("worker 启动");
    }

    @Override
    public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
        log.info("worker preRestart start statecount:{}", this.stateCount);
        super.preRestart(reason, message);
        log.info("worker preRestart end statecount:{}", this.stateCount);
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        log.info("worker postRestart start statecount:{}", this.stateCount);
        super.postRestart(reason);
        log.info("worker postRestart end statecount:{}", this.stateCount);
    }
}
