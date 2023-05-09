package com.example10;

import akka.actor.UntypedAbstractActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Feng Jie
 * @date 2023/5/9 20:11
 */
@Component
@Scope("prototype")
public class ActorDemo extends UntypedAbstractActor {

    @Autowired
    private UserService userService;
    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("收到消息："+ message);
        userService.saveMsg(((String) message));
    }
}
