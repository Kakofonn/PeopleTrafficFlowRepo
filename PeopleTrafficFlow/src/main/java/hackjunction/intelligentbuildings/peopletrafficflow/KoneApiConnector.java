package hackjunction.intelligentbuildings.peopletrafficflow;

import java.io.IOException;

import hackjunction.intelligentbuildings.peopletrafficflow.classes.User;
import hackjunction.intelligentbuildings.peopletrafficflow.utils.Utilities;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class KoneApiConnector {

	/**
	 * This method sends api call to call the elevator. In the current implementation,
	 * it's a mock up. Furthermore, it should use data about areas and set it accordingly.
	 * 
	 * @param user
	 * @return
	 * @throws IOException
	 */
	public boolean callLift(User user) throws IOException {

		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/vnd.api+json");
		RequestBody body = RequestBody.create(mediaType, "{\"template\":{\"data\":[{\"name\":\"sourceAreaId\", \"value\":\"area:9990000508:1000\"}, {\"name\":\"direction\", \"value\":\"up\"} ] } }");
		Request request = new Request.Builder()
		  .url("https://api.kone.com/api/building/" + Utilities.getBuildingId() + "/call/")
		  .post(body)
		  .addHeader("x-ibm-client-id", Utilities.getClientId())
		  .addHeader("x-ibm-client-secret", Utilities.getSecretId())
		  .addHeader("content-type", "application/vnd.api+json")
		  .addHeader("accept", "application/vnd.api+json")
		  .build();

		Response response = client.newCall(request).execute();
		return response.isSuccessful();
	}
	
	/**
	 * This method should return the number of lift calls for particular lift using KONE api.
	 * It should help to provide the time estimation for waiting users. Currently it's void
	 * and does nothing but just receiving the JSON stirng from the API.
	 * 
	 * @throws IOException
	 */
	public void getLiftCalls() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://api.kone.com/api/building/" + Utilities.getBuildingId() + "/call/")
		  .get()
		  .addHeader("x-ibm-client-id", Utilities.getClientId())
		  .addHeader("x-ibm-client-secret", Utilities.getSecretId())
		  .addHeader("accept", "application/vnd.api+json")
		  .build();

		Response response = client.newCall(request).execute();
		String jsonResponse = response.message();
		//TODO figure out which object is returned from the API, return how many calls are pending for that lift
	}
	
}
