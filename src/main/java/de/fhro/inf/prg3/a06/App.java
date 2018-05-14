package de.fhro.inf.prg3.a06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fhro.inf.prg3.a06.model.Joke;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App {

	public static void main(String[] args) throws IOException {
		// TODO fetch a random joke and print it to STDOUT
		GsonBuilder b = new GsonBuilder();
		b.registerTypeAdapter(Joke.class, new JokeAdapter());
		Gson gson = b.create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://api.icndb.com/")
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();

		ICNDBApi icndbApi = retrofit.create(ICNDBApi.class);

		Joke joke = icndbApi.getRandomJoke().execute().body();
		System.out.println(joke);
	}

}
