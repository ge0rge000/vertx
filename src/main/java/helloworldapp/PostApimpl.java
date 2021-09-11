
package helloworldapp;

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
    public String postApi(){
        ActivityExecutionContext context = Activity.getExecutionContext();
        byte[] taskToken = context.getTaskToken();
        Vertx vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);

        client
                .post(8080, "localhost", "/currency/15/USD/EGP")
                .sendJsonObject(
                        new JsonObject()
                                .put("type", "Dale")
                )
                .onSuccess(res -> {
                    this.convert= String.valueOf(res.body());
                    completionClient.complete(taskToken,  convert);

                })
                .onFailure(err ->
                        System.out.println("Something went wrong " + err.getMessage()));
        context.doNotCompleteOnReturn();
        return "ignored";

    }


}

