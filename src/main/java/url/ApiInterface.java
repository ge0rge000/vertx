package url;

import io.vertx.core.Context;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.Context;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.core.Vertx;
public class ApiInterface extends AbstractVerticle {


    public static void main(String[ ] args){
        Vertx vertx = Vertx.vertx();

        WebClient client = WebClient.create(vertx);

// Send a GET request

                client
                        .getAbs("http://data.fixer.io/api/latest?access_key=e32dbc0bada6ce316c659ff029d0673d&format=1")
                        .as(BodyCodec.jsonObject())

                        .send()
                        .onSuccess(res -> {
                            JsonObject body = res.body();
                            JsonObject rates =body.getJsonObject("rates");
                            String result="EGP:"+rates.getString("EGP");
                            String val="USD"+rates.getString("USD");



                        })
                .onFailure(err ->
                        System.out.println("Something went wrong " + err.getMessage()));


// Send a GET request

//        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
//        HttpResponse response=null;
//        try{
//            String endPoint="http://data.fixer.io/api/latest?access_key=e32dbc0bada6ce316c659ff029d0673d&format=1";
//            URI uri = URI.create(endPoint+"?foo=bar&foo2=bar2");
//            HttpRequest request =HttpRequest.newBuilder().uri(uri).build();
//            response=client.send(request,HttpResponse.BodyHandlers.ofString());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        Object obj=JSONValue.parse((String) response.body());
//        JSONObject cuurrency = (JSONObject) obj;
//
//        JSONObject ratess = (JSONObject) cuurrency.get("rates");
//
//        System.out.println("EGP:"+ratess.get("EGP"));
//        System.out.println("USD:"+ratess.get("USD"));
//


    }

}
