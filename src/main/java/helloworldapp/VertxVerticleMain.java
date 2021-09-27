package helloworldapp;

 import io.vertx.core.CompositeFuture;
 import io.vertx.core.DeploymentOptions;

 import io.vertx.core.Vertx;
 import io.vertx.core.eventbus.EventBus;
 import io.vertx.core.eventbus.MessageConsumer;

public class VertxVerticleMain     {
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions options = new DeploymentOptions().setWorker(true);

        CompositeFuture.all(vertx.deployVerticle(new sendCurrencyVerticle(),options),
         vertx.deployVerticle(new HelloWorldWorkerVerticle(),options), vertx.deployVerticle(new InitiateHelloWorldVerticle(),options)

        ).onComplete(ar -> {
            if (ar.succeeded()) {
                System.out.println("success");
            } else {
                System.out.println("failed");
            }
        });



    }
}

