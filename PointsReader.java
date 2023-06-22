package dsaa.lab13;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static dsaa.lab13.Main.scan;

public class PointsReader {
	public static LinkedList<Point> load(Scanner scanner, int nrOfPoints) {
		LinkedList<Point> points = new LinkedList<>();

		for (int i = 0; i < nrOfPoints; i++) {
			long x = scanner.nextLong();
			long y = scanner.nextLong();
			points.add(new Point(x, y));
		}

		return points;
	}

}



