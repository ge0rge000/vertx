package helloworldapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class post_Vertx  {
    public static String type="USD";
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router
                .route(HttpMethod.POST, "/currency/:money/:maincurrency/:convert_to")
                .handler(ctx -> {
                    String money = ctx.pathParam("money");
                    String maincurrency = ctx.pathParam("maincurrency");
                    String convert_to = ctx.pathParam("convert_to");

                    ctx.end(convert_to);
                });
        server.requestHandler(router).listen(8080);


    }
}
