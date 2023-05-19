package utils;

public class Time {

	private static long startTime;
	private static long currentTime;
	private static long deltaTime;

	public static long getCurrentTime() {
		return Time.currentTime;
	}
	
	public static double getCurrentTimeSecond() {
		return Time.currentTime/100000000D;
	}

	public static void setCurrentTime(long newTime) {
		Time.deltaTime = newTime - Time.currentTime;
		Time.currentTime = newTime;
	}

	public static long getDeltaTime() {
		return Time.deltaTime;
	}
	
	public static double getDeltaTimeSecond() {
		return Time.deltaTime/100000000D;
	}

	public static void setStartTime(long startTime) {
		Time.startTime = startTime;
		Time.currentTime = startTime;
	}

	public static double getTimeSinceStart() {
		return Time.currentTime - Time.startTime;
	}
}
