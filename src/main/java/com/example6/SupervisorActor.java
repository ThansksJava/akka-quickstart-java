package com.example6;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Feng Jie
 * @date 2023/5/9 11:04
 */
public class SupervisorActor extends UntypedAbstractActor {
    LoggingAdapter log = Logging.getLogger( this.getContext() .system(), this);
    private final SupervisorStrategy supervisorStrategy = new OneForOneStrategy(3, Duration.create("1 minute"), t -> {
        if (t instanceof IOException) {
            log.info("==========IOException=========");
            return SupervisorStrategy.resume( ) ;
        } else if (t instanceof IndexOutOfBoundsException) {
            log.info("==========IndexOutOfBoundsException=========");
            return SupervisorStrategy.restart();
        } else if (t instanceof SQLException) {
            log.info("==========SQL Exception=========");
            return SupervisorStrategy.stop();
        } else {
            log.info("＝＝＝＝＝＝＝＝＝＝ escalate ＝＝＝＝＝＝＝＝＝");
            return SupervisorStrategy.escalate();
        }
    });

    public SupervisorActor() {
        ActorRef actor = getContext().actorOf(Props.create(WorkerActor.class), "workerActor");
        getContext().watch(actor);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Terminated){
            Terminated message1 = (Terminated) message;
            log.info("{}已经终止", message1.getActor());
        }else {
            log.info("stateCount={}",message);
        }
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return supervisorStrategy;
    }

    @Override
    public void preStart() throws Exception {
//        ActorRef actor = getContext().actorOf(Props.create(WorkerActor.class), "workerActor");
//        getContext().watch(actor);
//        actor.tell(new IOException("IO Ex"), getSelf());
//////        actor.tell(new SQLException("SQL Ex"), getSelf());
//////        actor.tell (new IndexOutOfBoundsException (), getSelf());
//        actor.tell("getValue", getSelf());
    }
}
