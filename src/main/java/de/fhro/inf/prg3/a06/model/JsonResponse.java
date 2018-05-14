package de.fhro.inf.prg3.a06.model;

import com.google.gson.annotations.SerializedName;

public class JsonResponse {
    @SerializedName("type")
    private String responeType;
    @SerializedName("value")
    private Object receivedJoke;

    public String getResponeType() {
        return responeType;
    }

    public void setResponeType(String responeType) {
        this.responeType = responeType;
    }

    public Object getReceivedJoke() {
        return receivedJoke;
    }

    public void setReceivedJoke(Object receivedJoke) {
        this.receivedJoke = receivedJoke;
    }
}
