
package helloworldapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Context;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import java.util.concurrent.CompletableFuture;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;
public class PostApimpl implements PostApi  {
   static String convert;
    private final ActivityCompletionClient completionClient;

    PostApimpl(ActivityCompletionClient completionClient) {
        this.completionClient = completionClient;
    }
        @Override
    public String postApi(String currency,String currency_main,Integer price){
        ActivityExecutionContext context = Activity.getExecutionContext();
        byte[] taskToken = context.getTaskToken();
        Vertx vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);

        client
                .post(8080, "localhost", "/currency/"+price+"/"+currency_main+"/"+currency)
                .sendJsonObject(
                        new JsonObject()
                                .put("type", "Dale")
                )
                .onSuccess(res -> {

                    completionClient.complete(taskToken,  String.valueOf(res.body()));

                })
                .onFailure(err ->
                        System.out.println("Something went wrong " + err.getMessage()));
        context.doNotCompleteOnReturn();
        return "ignored";

    }


}

