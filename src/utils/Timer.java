package utils;

public class Timer {
	public static double getTime() {
		return (double) (System.nanoTime() / 1e9);
	}
}
