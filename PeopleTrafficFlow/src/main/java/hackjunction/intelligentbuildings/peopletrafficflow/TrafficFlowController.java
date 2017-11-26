package hackjunction.intelligentbuildings.peopletrafficflow;

import java.io.IOException;
import java.util.Set;

import hackjunction.intelligentbuildings.peopletrafficflow.servlets.ConnectorServlet;
import hackjunction.intelligentbuildings.peopletrafficflow.utils.JsonParser;
import hackjunction.intelligentbuildings.peopletrafficflow.utils.Utilities;
import hackjunction.intelligentbuildings.peopletrafficflow.classes.User;

public class TrafficFlowController {
	

	private TrafficData trafficData = new TrafficData();
	private KoneApiConnector koneApiConnector;
	
	public TrafficFlowController(ConnectorServlet connector) {
		koneApiConnector = new KoneApiConnector();
	}
	
	/**
	 * This method tries to find a place for a user in any of the elevators.
	 * If it does not find it, returns false. Otherwise, return true.
	 * 
	 * @param jsonString
	 * @return
	 */
	public String alocateUser(String jsonString) {
		JsonParser parser = new JsonParser();
		User user = parser.getUserObjectFromJsonString(jsonString);
		if (!userFitsInTheElevator(user)) {
			user.setNotificationSent(true);
		}
		try {
			koneApiConnector.callLift(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("User " + user.getUserId() + " successfully called the elevator. Adding user to the local storage");
		trafficData.addUser(user.getCurrentFloor(), user);
		return parser.getJsonStringFromUserObject(user);
	}
	
	private boolean userFitsInTheElevator(User user) {
		Set<User> users = trafficData.getUserData().get(new Integer(user.getCurrentFloor()));
		if (users != null && !users.isEmpty()) {
			return !(users.size() > Utilities.getElevatorCapacity());
		} else {
			System.err.println("User data was empty");
			return true;
		}
	}
	


}
