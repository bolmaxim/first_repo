package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main (String[] args) {
		hello("world");
		hello("User");
		hello("Max");

		Square s = new Square(5);
		System.out.println("S" + s.l + "=" + s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("S" + r.a + "and" + r.b + "=" + r.area());

		Point p1 = new Point();
		p1.a = 2;
		p1.b = 4;
		Point p2 = new Point();
		p2.a = 6;
		p2.b = 8;
		System.out.println("distance between p1 and p2= " + distance(p1,p2));

	}
	public static void hello(String somebody) {

		System.out.println("Hello, " + somebody + "!");
		}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt ((p2.a - p1.a)*(p2.a - p1.a) + (p2.b - p1.b)*(p2.b - p1.b));


	}


}
