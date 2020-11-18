package com.withyou.platform.demo;

import akka.actor.*;

/**
 * @author jeremy.zhao
 */
public class ActorCase {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("testActor");
        final ActorRef ref = system.actorOf(Props.create(StudentActor.class), "student");
        for (int i = 0; i < 100000; i++) {
            System.out.println("Send message: " + i);
            ref.tell("No. " + i, ActorRef.noSender());
            if (i == 10) {
                ref.tell(PoisonPill.getInstance(), ref);
            }

        }
        system.terminate();
    }
}

class StudentActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        System.out.println(message.toString() + " Received.");
        Thread.sleep(500);
    }
}
