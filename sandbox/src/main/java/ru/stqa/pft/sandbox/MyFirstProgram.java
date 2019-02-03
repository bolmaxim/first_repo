package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main (String[] args) {

		Point p1 = new Point(2,4);
		Point p2 = new Point(6,8);
		System.out.println("distance between p1 and p2 = " + p1.distance(p2));

	}

}
