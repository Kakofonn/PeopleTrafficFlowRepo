package hackjunction.intelligentbuildings.peopletrafficflow.classes;

public class User {
	
	private long userId;
	private int currentFloor;
	private int desiredFloor;
	private boolean notificationSent = false;
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	public int getDesiredFloor() {
		return desiredFloor;
	}
	
	public void setDesiredFloor(int desiredFloor) {
		this.desiredFloor = desiredFloor;
	}
	
	public boolean isNotificationSent() {
		return notificationSent;
	}
	
	public void setNotificationSent(boolean notificationSent) {
		this.notificationSent = notificationSent;
	}
}
