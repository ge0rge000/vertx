package helloworldapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class HelloVerticle extends AbstractVerticle {

    public void start(){

        EventBus eb = vertx.eventBus();

        eb.consumer("/currency/:money/:maincurrency/:convert_to", message -> {
            System.out.println("I have received a message: " + message.body());
        });
    }
}
