package testeventbus;

import com.google.gson.JsonObject;
import io.vertx.core.AbstractVerticle;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
 import io.vertx.ext.web.Router;
public class MainVerticle extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router
                .route(HttpMethod.POST, "/george/:productType/")
                .handler(ctx -> {

                    String productType = ctx.pathParam("productType");



                });
        server.requestHandler(router).listen(8080);
    }
}
