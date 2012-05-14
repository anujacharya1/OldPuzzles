package com.anuj;

import java.io.*;

public class Triangle {
	final static int SCALENE = 1;
	final static int ISOSCELES = 2;
	final static int EQUILATERAL = 3;
	final static int ERROR = 4;
	final static int testDataSize = 14;
	
	public static int TriangleType(int x, int y, int z) {
		
		
		if ((x + y <= z) || (x + z <= y) || (z + y <= x)) return ERROR;
		if (x<=0 || y<=0 || z<=0) return ERROR;
		if (x == y && y == z) return EQUILATERAL;
		if (x == y || y == z || z == x) return ISOSCELES;
		return SCALENE;

	}
	
	

}
