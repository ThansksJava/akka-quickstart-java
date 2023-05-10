package com.example11;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.io.Tcp;
import akka.io.TcpMessage;

import java.net.InetSocketAddress;

/**
 * @author Feng Jie
 * @date 2023/5/10 11:17
 */
public class TcpServerDemo extends UntypedAbstractActor {
    @Override
    public void preStart() throws Exception {
        ActorRef tcpManager = Tcp.get(getContext().getSystem()).manager();
        tcpManager.tell(TcpMessage.bind(getSelf(), new InetSocketAddress("127.0.0.1", 8888), 100), getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Tcp.Bound){
            // bound来自TCPListener
            Tcp.Bound bound = (Tcp.Bound) message;
            System.out.println("Bound:"+bound);
        }else if (message instanceof Tcp.Connected){
            Tcp.Connected connected = (Tcp.Connected) message;
            System.out.println("conn:" + connected);
            ActorRef handler = getContext().actorOf(Props.create(ServerHandler.class));
            // Connected 消息的发送者（ sender ）就是我需要的连接对象，它是一个 Actor
            //（也称为 ConnectionActor ），我们可以直接通过 getSender()来引用它，并通过它与对方进
            //行通信
            getSender().tell(TcpMessage.register(handler), getSelf());
        }
    }
}