package br.com.diegorezende.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class JsonUtil implements Serializable {

    private JsonUtil() {

    }

    public static <T> T converterJsonToObject(String json, Class<T> clazzObject) throws IOException {
        return new ObjectMapper().readValue(json, clazzObject);
    }

}
