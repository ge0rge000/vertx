package helloworldapp;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class VertxVerticleMain  {

    public static void main(String[] args)  {


        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new sendCurrencyVerticle());
        vertx.deployVerticle(new HelloWorldWorkerVerticle());
        vertx.deployVerticle(new InitiateHelloWorldVerticle());
    }
}
