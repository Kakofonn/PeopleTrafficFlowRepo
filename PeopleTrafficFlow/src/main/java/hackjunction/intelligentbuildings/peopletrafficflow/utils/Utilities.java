package hackjunction.intelligentbuildings.peopletrafficflow.utils;

public class Utilities {

	/*
	 * For the time being and demo, these values are set this way.
	 */
	private static final long BUILDING_ID = 9990000508l;
	private static final String CLIENT_ID = "50b38604-19e6-4914-af63-5b3c2c6514fd";
	private static final String SECRET_ID = "Q5cI0eN2aV8eV8kS7uL5cE5dW8kE4iN5rV4sW0tN2xF4kH4qM1";
	private static final int ELEVATOR_CAPACITY = 8;
	
	public static String getClientId() {
		return CLIENT_ID;
	}
	
	public static String getSecretId() {
		return SECRET_ID;
	}

	public static long getBuildingId() {
		return BUILDING_ID;
	}

	public static int getElevatorCapacity() {
		return ELEVATOR_CAPACITY;
	}
	
}
