package helloworldapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class lastConvert {
    @JsonProperty("EGP")
    float EGP;
    @JsonProperty("EUR")
    float EUR;
    @JsonProperty("USD")
    float USD;
    float rate;

    public lastConvert(float EGP, float EUR, float USD, float rate) {
        this.EGP = EGP;
        this.EUR = EUR;
        this.USD = USD;
        this.rate = rate;
    }
    public lastConvert() {

    }

    public float getEGP() {
        return EGP;
    }

    public void setEGP(float EGP) {
        this.EGP = EGP;
    }

    public float getEUR() {
        return EUR;
    }

    public void setEUR(float EUR) {
        this.EUR = EUR;
    }

    public float getUSD() {
        return USD;
    }

    public void setUSD(float USD) {
        this.USD = USD;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
