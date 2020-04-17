package com.withyou.platform.demo;

import akka.actor.*;

/**
 * @author jeremy.zhao
 */
public class ActorCase {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("testActor");
        for (int i = 0; i < 1000; i++) {
            final ActorRef ref = system.actorOf(Props.create(StudentActor.class), "student" + i);
            System.out.println("Send message: " + i);
            ref.tell("No. " + i, ActorRef.noSender());
        }
    }
}

class StudentActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        System.out.println(message.toString() + " Received.");
        Thread.sleep(1500);
    }
}
