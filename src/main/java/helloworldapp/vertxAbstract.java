package helloworldapp;

import io.vertx.core.Vertx;

public abstract class vertxAbstract  {
    Vertx vertx;

    public vertxAbstract(Vertx vertx) {
        this.vertx = vertx;
    }
}
