package com.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class YouTube implements Channel {

    private List<ISubscriber> subscriberList = new ArrayList<>();
    private String name;

    public String getVideoName() {
        return videoName;
    }

    private String videoName;

    YouTube(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<ISubscriber> getSubscriberList() {
        return this.subscriberList;
    }

    @Override
    public void notifyUser() {

        for (ISubscriber subscriber : subscriberList) {
            subscriber.update(getVideoName());
        }

    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        if (subscriber != null && !subscriberList.contains(subscriber)) {
            subscriberList.add(subscriber);
            System.out.println("Subscriber Added Successfully");
        }

    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        if (subscriber != null && subscriberList.contains(subscriber)) {
            subscriberList.remove(subscriber);
            System.out.println("Subscriber Removed Successfully");
        }
    }

    public void uploadVideo(String videoName) {
        this.videoName = videoName;
        System.out.println("New Video Uploaded On Channel " + getName()  +" ~ " + this.videoName);
        notifyUser();

    }




}
