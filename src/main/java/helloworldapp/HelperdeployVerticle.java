package helloworldapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

public class HelperdeployVerticle extends AbstractVerticle {
    public void start() {
        vertx.eventBus().consumer("address", message -> {
            System.out.println("message received by receiver");
            System.out.println(message.body());
        });
    }
}
