package helloworldapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class sendCurrencyVerticle  extends AbstractVerticle {
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        EventBus eventBus = vertx.eventBus();
        router
                .route(HttpMethod.POST, "/currency/:money/:maincurrency/:convert_to")
                .handler(ctx -> {
                    String money = ctx.pathParam("money");
                    String maincurrency = ctx.pathParam("maincurrency");
                    String convert_to = ctx.pathParam("convert_to");

                    eventBus.publish
                            ("news.uk.sport",(money+','+maincurrency+','+convert_to));
                    ctx.end(money+","+maincurrency+","+convert_to);
                });
                  server.requestHandler(router).listen(8080);


    }
}
