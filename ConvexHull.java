package dsaa.lab13;



import java.util.*;

public class ConvexHull {

	public static LinkedList<Point> solve(LinkedList<Point> points) {
		// Sprawdź, czy lista punktów jest pusta lub zawiera mniej niż 3 punkty
		int n = points.size();
		if (n < 3) {
			return new LinkedList<>(points);
		}

	// Znajdź najniższy punkt (o najmniejszej wartości y), a w przypadku remisu najbardziej na lewo
		int lowestIndex = 0;
		for (int i = 1; i < n; i++) {
			Point current = points.get(i);
			Point lowest = points.get(lowestIndex);

			if (current.y < lowest.y || (current.y == lowest.y && current.x < lowest.x)) {
				lowestIndex = i;
			}
		}

		// Zamień najniższy punkt z pierwszym punktem na liście
		Collections.swap(points, 0, lowestIndex);

		// Sortuj pozostałe punkty według kąta, jaki tworzą z najniższym punktem
		Point lowest = points.get(0);
		Comparator<Point> angleComparator = (p1, p2) -> {
			double angle1 = Math.atan2(p1.y - lowest.y, p1.x - lowest.x);
			double angle2 = Math.atan2(p2.y - lowest.y, p2.x - lowest.x);
			if (angle1 < angle2) {
				return -1;
			} else if (angle1 > angle2) {
				return 1;
			} else {
				return 0;
			}
		};
		points.sort((angleComparator));


		// Tworzenie wypukłej otoczki
		Stack<Point> stack = new Stack<>();

		stack.push(points.get(0));
		stack.push(points.get(1));
		stack.push(points.get(2));



		// Process the remaining points
		for (int i = 3; i < n; i++) {
			Point top = stack.pop();
			while (orientation(stack.peek(), top, points.get(i)) >0)
			{
				top = stack.pop();
			}
			stack.push(top);
			stack.push(points.get(i));
		}



		// Tworzenie kopii listy i zwracanie
// Zamień wynik ze stosu na listę i zwróć
		LinkedList<Point> convexHull = new LinkedList<>(stack);
		return convexHull;
	}



	private static long orientation(Point p, Point q, Point r) {
		long val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
		if (val == 0) {
			return 0; // punkty są współliniowe
		}
		else if (val > 0)
		{
			return 1;
		}
		return -1;
		// zwraca 1, jeśli orientacja jest zgodna z ruchem wskazówek zegara, -1 w przeciwnym razie
	}
}
