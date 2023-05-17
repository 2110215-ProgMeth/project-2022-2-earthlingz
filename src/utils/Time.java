package utils;

public class Time {

	private static long startTime;
	private static long currentTime;
	private static long deltaTime;

	public static double getCurrentTime() {
		return Time.currentTime;
	}

	public static void setCurrentTime(long newTime) {
		Time.deltaTime = newTime - Time.currentTime;
		Time.currentTime = newTime;
	}

	public static double getDeltaTime() {
		return Time.deltaTime;
	}

	public static void setStartTime(long startTime) {
		Time.startTime = startTime;
	}

	public static double getTimeSinceStart() {
		return Time.currentTime - Time.startTime;
	}
}
