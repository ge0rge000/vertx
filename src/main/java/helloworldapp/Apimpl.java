
package helloworldapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                .getAbs("http://data.fixer.io/api/latest?access_key=18acc0fc4fb1ce211d4b8345cf05781e&format=1")
                .as(BodyCodec.jsonObject())
                .send()
                .onSuccess(res -> {
                    String body = res.body().toString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = objectMapper.readTree(body);
                        int money_converted = jsonNode.get("rates").get(currency).asInt();

        String result = "money: "+price+" currencymain :"+currency_main+" converted to :" +currency +
          " result: "+money_converted*price;
                        completionClient.complete(taskToken, result);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

//    String result = "money: "+price+" currencymain :"+get_currency.Currencymain+" converted to :" +get_currency.converted +
//            " result: "+get_currency.money;;

                })
                .onFailure(err ->
                        System.out.println("Something went wrong " + err.getMessage()));
        context.doNotCompleteOnReturn();
        return "ignored";

    }
}

