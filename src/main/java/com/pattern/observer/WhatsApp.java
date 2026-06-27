package com.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class WhatsApp implements Channel{

    List<ISubscriber> subscribers = new ArrayList<ISubscriber>();
    String msgName;
    String name;

    public WhatsApp(String name) {
        this.name = name;
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public String getMsgName() {
        return msgName;
    }
    public String getChannelName() {
        return this.name;
    }

    @Override
    public void notifyUser() {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(getMsgName());
        }

    }

    @Override
    public void subscribe(ISubscriber subscriber) {

        if(subscribers != null && !subscribers.contains(subscriber)){
            subscribers.add(subscriber);

        }

    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        if(subscribers != null && subscribers.contains(subscriber)){
            subscribers.remove(subscriber);

        }
    }

    public void broadCastMsg(String msgName) {
        this.msgName = msgName;
        System.out.println("New Video Uploaded On Channel " + getChannelName()  +" ~ " + getMsgName());
        notifyUser();

    }
}
