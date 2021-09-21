package helloworldapp;

import io.vertx.core.Vertx;

public abstract class vertxAbstract  {
    Vertx vertx;

    public void setVertx(Vertx vertx) {
        this.vertx = vertx;
    }


}
