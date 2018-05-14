package de.fhro.inf.prg3.a06.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.fhro.inf.prg3.a06.ICNDBApi;
import de.fhro.inf.prg3.a06.JokeAdapter;
import de.fhro.inf.prg3.a06.model.Joke;
import de.fhro.inf.prg3.a06.model.JsonResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
class ICNDBTests {

	private static final Logger logger = LogManager.getLogger(ICNDBTests.class);
	private static final int REQUEST_COUNT = 10;

	private ICNDBApi icndbApi = null;

	@BeforeEach
	public void befor() {
		GsonBuilder b = new GsonBuilder();
		b.registerTypeAdapter(Joke.class, new JokeAdapter());
		Gson gson = b.create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://api.icndb.com/")
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();

		icndbApi = retrofit.create(ICNDBApi.class);
	}

	@Test
	void testCategories() throws IOException {
		Joke joke = icndbApi.getRandomJoke(new String[] {"nerdy"}).execute().body();
		assertTrue(joke != null);
	}

	@Test
	void testCount() throws IOException {
		List<Joke> joke =  icndbApi.getRandomJokes(3).execute().body();
		assertTrue(joke != null);
	}

	@Test
	void testCollision() throws IOException {
		Set<Integer> jokeNumbers = new HashSet<>();
		int requests = 0;
		boolean collision = false;

		while (requests++ < REQUEST_COUNT) {
			// TODO Prepare call object

			// TODO Perform a synchronous request

			// TODO Extract object

			Joke j = null;

			if(jokeNumbers.contains(j.getNumber())) {
				logger.info(String.format("Collision at joke %s", j.getNumber()));
				collision = true;
				break;
			}

//			jokeNumbers.add(j.getNumber());
			logger.info(j.toString());
		}

		assertTrue(collision, String.format("Completed %d requests without collision; consider increasing REQUEST_COUNT", requests));
	}
}
