package helloworldapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class lastConvert {
    @JsonProperty("EGP")
    float EGP;
    float rate;
    public lastConvert(float EGP, float rate) {
        this.EGP = EGP;
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

    public float getRate() {

        return rate;
    }

    public void setRate(float rate)
    {
        this.rate = rate;
    }
}
