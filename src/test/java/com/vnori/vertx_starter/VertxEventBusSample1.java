package com.vnori.vertx_starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

public class VertxEventBusSample1 extends AbstractVerticle {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new VertxEventBusSample1());
  }

  @Override
  public void start() throws Exception {
    System.out.println("In Vertx Event Start");

    EventBus eb = vertx.eventBus();
    MessageConsumer<Object> consumer1 = eb.consumer("sample.news1");
    consumer1.handler(objectMessage -> {
      System.out.println("I have received a message: " + objectMessage.body());
    });

    eb.request("sample.news1", "Yay! Someone kicked a ball across a patch of grass", ar -> {
      if (ar.succeeded()) {
        System.out.println("Received reply: " + ar.result().body());
      }
    });

    //===============================================================================================================

    //Create Consumer and Handler in the same step
    MessageConsumer<Object> consumer = vertx.eventBus().consumer("sample.news",
      message -> {
        System.out.println("Message Output: " + message.body());
      }
    );

  }
}
