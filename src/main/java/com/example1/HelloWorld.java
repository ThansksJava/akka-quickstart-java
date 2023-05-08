package com.example1;

import akka.actor.*;
import com.example.Greeter;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * @author Feng Jie
 * @date 2023/5/8 17:09
 */
public class HelloWorld extends UntypedAbstractActor {
    @Override
    public void preStart() throws Exception {
        System.out.println("================");
    }

    @Override
    public void onReceive(Object message) throws Throwable {

    }
}
