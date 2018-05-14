package de.fhro.inf.prg3.a06;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import de.fhro.inf.prg3.a06.model.Joke;
import de.fhro.inf.prg3.a06.model.JsonResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JokeAdapter extends TypeAdapter {

    private Gson gson = new Gson();

    @Override
    public void write(JsonWriter out, final Object value) throws IOException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Object read(JsonReader in) throws IOException {
        JsonResponse jsonResponse = gson.fromJson(in, JsonResponse.class);
        if(jsonResponse.getReceivedJoke() instanceof List) {
            List<Joke> jokes = new ArrayList<>();
            for (Map obj : (List<Map>)jsonResponse.getReceivedJoke()) {
                jokes.add(new Joke(obj.get("id"), obj.get("joke"), obj.get("categories")));
            }
            return jokes;
        }
        Map map = (Map) jsonResponse.getReceivedJoke();
        return new Joke(map.get("id"), map.get("joke"), map.get("categories"));
    }
}
