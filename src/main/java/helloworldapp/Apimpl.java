
package helloworldapp;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.temporal.client.ActivityCompletionClient;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;

import io.vertx.core.Vertx;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

import io.vertx.ext.web.codec.BodyCodec;
import org.json.simple.JSONArray;

import java.lang.reflect.Array;

public class Apimpl   extends vertxAbstract implements Api  {
    private final ActivityCompletionClient completionClient;


    public Apimpl( ActivityCompletionClient completionClient  ) {
        this.completionClient = completionClient;
    }
    @Override

    public vertxAbstract setVertx(Vertx vertx) {
        this.vertx=vertx;
        return this;
    }
    public String receiveApi(String currency,String currency_main,String price) {
        ActivityExecutionContext context = Activity.getExecutionContext();
        byte[] taskToken = context.getTaskToken();

        WebClient client = WebClient.create(vertx);
        client
                .getAbs("https://api.fastforex.io/convert?from="+
                        currency_main+"&to="+currency+"&amount="+price+"&api_key=5c0423eb71-18eb217b67-r03d4a")
                .as(BodyCodec.jsonObject())
                .send()
                .onSuccess(res -> {
                    String body = res.body().toString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        Currency currency_get = objectMapper.readValue(body, Currency.class);
                        completionClient.complete(taskToken,currency_get.getResult().getEGP());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    //     String result = "money: "+price+" currencymain :"+currency_main+" converted to :" +currency +
                    //  " result: "+(currency_get.money)*price;
                })
                .onFailure(err ->
                        System.out.println("Something went wrong " + err.getMessage()));
        context.doNotCompleteOnReturn();
        return "ignored";

    }



}

