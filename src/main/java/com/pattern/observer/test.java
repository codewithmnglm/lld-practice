package com.pattern.observer;

public class test {


    static void main() {

        WhatsApp channel = new WhatsApp("WhatsApp");

        Subscriber s1 = new Subscriber("Ram", channel);
        Subscriber s2 = new Subscriber("Shyam", channel);

        channel.subscribe(s1);
        channel.subscribe(s2);

        System.out.println("Subscriber List Size:" + channel.getSubscribers().size());
        channel.broadCastMsg("KrMnglmArrivng");




    }
}
