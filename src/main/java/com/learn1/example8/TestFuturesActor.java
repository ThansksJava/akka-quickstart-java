package com.learn1.example8;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.dispatch.Mapper;
import akka.dispatch.OnComplete;
import akka.dispatch.OnFailure;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.Function1;
import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.util.Try;

import java.util.concurrent.TimeoutException;

/**
 * @author Feng Jie
 * @date 2023/5/9 16:57
 */
public class TestFuturesActor {
    public static void main(String[] args) {
        ActorSystem sys = ActorSystem.create("sys");
        ActorRef futureActorRef = sys.actorOf(Props.create(FutureActor.class), "futureActor");
        Timeout timeout = new Timeout(Duration.create(3, "seconds"));
        Future<Object> ask = Patterns.ask(futureActorRef, "hello future", timeout);
        Future<String> map = ask.map(new Mapper<Object, String>() {
            @Override
            public String apply(Object parameter) {
                return ((String) parameter).toUpperCase();
            }
        }, sys.dispatcher());
        map.onComplete(new OnComplete<String>() {
            @Override
            public void onComplete(Throwable failure, String success) throws Throwable {
                System.out.println(success);
            }
        },sys.dispatcher());
//        try {
//            String result = (String) Await.result(ask, timeout.duration());
//            System.out.println(result);
//        } catch (TimeoutException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    static class FutureActor extends UntypedAbstractActor{
        @Override
        public void onReceive(Object message) throws Throwable {
            getSender().tell("abcdefg", getSelf());
        }
    }
}
