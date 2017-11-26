package hackjunction.intelligentbuildings.peopletrafficflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import hackjunction.intelligentbuildings.peopletrafficflow.classes.User;

public class TrafficData {
	
	private Map<Integer, Set<User>> userData = new ConcurrentHashMap<>();
	
	public Map<Integer, Set<User>> getUserData() {
		return this.userData;
	}
	
	public void setUserData(Map<Integer, Set<User>> userData) {
		this.userData = userData;
	}
	
	public void addUser(Integer currentFloor, User user) {
		try {
			if (!userData.containsKey(currentFloor)) {
				Set<User> awaitingUsers = new HashSet<>();
				userData.put(currentFloor, awaitingUsers);
			}
			userData.get(currentFloor).add(user);
		} catch (Exception e) {
			System.err.println("Failed to add new user to user data:");
			e.printStackTrace();
		}
		debugPrintUserData();
	}
	
	/**
	 * Single user cannot be on 2 floors at the same time. So we just remove it from full map
	 * @param user
	 */
	public boolean removeUser(User user) {
		for (Integer floor : userData.keySet()) {
			Iterator<User> iterator = userData.get(floor).iterator();
			while (iterator.hasNext()) {
				User selectedUser = iterator.next();
				if (user.getUserId() != 0 && selectedUser.getUserId() == user.getUserId()) {
					iterator.remove();
					return true;
				}
			}
		}
		return false;
	}
	
	private void debugPrintUserData() {
		System.err.println("Debugging user data: ");
		for (Integer floor : userData.keySet()) {
			for (User user : userData.get(floor)) {
				System.err.println("User " + user.getUserId() + " at floor " + floor + " would like to get to " + user.getDesiredFloor() + " and has received notification: " + user.isNotificationSent());
			}
		}
	}

}
