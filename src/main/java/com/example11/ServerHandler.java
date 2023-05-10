package com.example11;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.util.ByteString;

/**
 * @author Feng Jie
 * @date 2023/5/10 11:23
 */
public class ServerHandler extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Tcp.Received){
            Tcp.Received re = (Tcp.Received) message;
            ByteString data = re.data();
            String s = data.utf8String();
            System.out.println("server:" + s);
            ActorRef conn = getSender();
            conn.tell(TcpMessage.write(ByteString.fromString("Thanks!")), getSelf());
            
        } else if (message instanceof Tcp.ConnectionClosed) {
            System.out.println("连接关闭："+message);
            getContext().stop(getSelf());
        }else {
            System.out.println("其他TCP连接");
        }
    }
}
