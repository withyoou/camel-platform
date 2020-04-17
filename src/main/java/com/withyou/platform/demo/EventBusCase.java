package com.withyou.platform.demo;

import akka.actor.*;
import akka.event.japi.LookupEventBus;

public class EventBusCase {

    public static String TOPIC_1 = "apple";

    private static String TOPIC_2 = "orange";

    public static void main(String[] args) {
        EventBusCenter busCenter = new EventBusCenter();
        ActorSystem system = ActorSystem.apply("eventbus");
        ActorRef javaRef = system.actorOf(Props.create(JavaActor.class));
        ActorRef pythonRef = system.actorOf(Props.create(PythonActor.class));

        //Subscribe
        busCenter.subscribe(javaRef, TOPIC_1);
        busCenter.subscribe(pythonRef, TOPIC_1);


        //Publish
        busCenter.publish(new EventMsg(TOPIC_1, "hello world"));

        system.terminate();
    }
}

class EventMsg {
    public final String topic;
    public final Object payload;

    public EventMsg(String topic, Object payload) {
        this.topic = topic;
        this.payload = payload;
    }
}

class EventBusCenter extends LookupEventBus<EventMsg, ActorRef, String> {

    @Override
    public int mapSize() {
        return 128;
    }

    @Override
    public int compareSubscribers(ActorRef a, ActorRef b) {
        return a.compareTo(b);
    }

    @Override
    public String classify(EventMsg event) {
        return event.topic;
    }

    @Override
    public void publish(EventMsg event, ActorRef subscriber) {
        subscriber.tell(event.payload, Actor.noSender());
    }
}

class JavaActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        System.out.println("JavaActor " + message.toString());
    }
}

class PythonActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        System.out.println("PythonActor " + message.toString());
    }
}