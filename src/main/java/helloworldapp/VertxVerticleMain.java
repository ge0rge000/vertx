package helloworldapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import helloworldapp.InitiateHelloWorld;
import helloworldapp.HelloWorldWorker;
public class VertxVerticleMain  {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloWorldWorker());
        vertx.deployVerticle(new InitiateHelloWorld());
    }
}