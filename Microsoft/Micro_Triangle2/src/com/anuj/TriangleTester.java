package com.anuj;

import java.io.IOException;


public class TriangleTester {
	
	public static void main(String[] args) throws IOException {
		final int SCALENE = 1;
		final int ISOSCELES = 2;
		final int EQUILATERAL = 3;
		final int ERROR = 4;
		final int testDataSize = 14;
		//Triangle.TriangleType(x, y, z);
		int[][] testData = new int[][] { 
				{ 0, 0, 0, ERROR },
				{ 0, 1, 2, ERROR }, 
				{ 1, 0, 2, ERROR },
				{ 1, 2, 0, ERROR },
				{-1, -1, -1, ERROR }, // negative number checks
				{ -1, 1, 2, ERROR }, 
				{ 1, -1, 2, ERROR }, 
				{ 1, 2, -1, ERROR },
				{ 2, 2, 4, ERROR }, // line segment not a triangle
				{ 1, 1, 5, ERROR }, // not a triangle
				{ 1, 1, 1, EQUILATERAL },
				{ 4, 6, 6, ISOSCELES },
				{ 5, 4, 4, ISOSCELES },
				{ 5, 3, 5, ISOSCELES },
				
		};
		for (int i = 0; i < testDataSize; ++i) {
			int result = Triangle.TriangleType(testData[i][0], testData[i][1], testData[i][2]);
			if (result == testData[i][3]) {
				System.out.println(" Pass for: triangleType(" + testData[i][0] + ", "
								+ testData[i][1] + ", " + testData[i][2]
								+ ")==");
			} else {
				System.out.println(" FAIL for: triangleType(" + testData[i][0]
						+ ", " + testData[i][1] + ", " + testData[i][2] + ")=="
						+ " Expected: " + testData[i][3]);
			}
		}
	}

}
