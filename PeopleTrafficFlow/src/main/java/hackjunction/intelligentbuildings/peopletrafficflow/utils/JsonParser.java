package hackjunction.intelligentbuildings.peopletrafficflow.utils;

import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import hackjunction.intelligentbuildings.peopletrafficflow.classes.User;

public class JsonParser {
	
	public User getUserObjectFromJsonString(String json) {
		JSONParser parser = new JSONParser();
		User user = new User();
		try{
			JSONObject jsonObject = (JSONObject)parser.parse(json);
			user.setUserId((Long) jsonObject.get("userid"));
			user.setCurrentFloor(((Long) jsonObject.get("currentfloor")).intValue());
			user.setDesiredFloor(((Long) jsonObject.get("desiredfloor")).intValue());
		}catch(ParseException pe){
			System.err.println("position: " + pe.getPosition());
			System.err.println(pe);
			return null;
		} catch (Exception e) {
			System.err.println("Exception while parsing json fields:");
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public String getJsonStringFromUserObject(User user) {
		JSONObject obj = new JSONObject();

		try {
			obj.put("userid", new Long(user.getUserId()));
			obj.put("startingfloor",new Integer(user.getCurrentFloor()));
			obj.put("endfloor",new Integer(user.getDesiredFloor()));
			obj.put("notificationsent",new Boolean(user.isNotificationSent()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringWriter out = new StringWriter();
		try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String jsonString = out.toString();
		return jsonString;
	}

}
