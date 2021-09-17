
package helloworldapp;

import io.vertx.core.Context;
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
public class Apimpl implements Api {
    private final ActivityCompletionClient completionClient;

    Apimpl(ActivityCompletionClient completionClient) {
        this.completionClient = completionClient;
    }    @Override

    public String receiveApi(String currency,String currency_main,Integer price) {
        ActivityExecutionContext context = Activity.getExecutionContext();
        byte[] taskToken = context.getTaskToken();
        Vertx vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);
        client
                .getAbs("http://data.fixer.io/api/latest?access_key=e32dbc0bada6ce316c659ff029d0673d&format=1")
                .as(BodyCodec.jsonObject())
                .send()
                .onSuccess(res -> {
                    JsonObject body = res.body();
                    JsonObject rates =body.getJsonObject("rates");
    String result = "money: "+price+" currencymain :"+currency_main+" converted to :" +currency +" result: "+(rates.getInteger(currency))*price;;
                    completionClient.complete(taskToken, result);
                })
                .onFailure(err ->
                        System.out.println("Something went wrong " + err.getMessage()));
        context.doNotCompleteOnReturn();
        return "ignored";

    }
}

