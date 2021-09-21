package helloworldapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vertx.core.json.JsonObject;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Currency {
    lastConvert result;
    public Currency(lastConvert result) {
        this.result = result;
    }
    public Currency() {
        super();
    }
    public void setResult(lastConvert result) {
        this.result = result;
    }

    public lastConvert getResult() {
        return result;
    }


}
