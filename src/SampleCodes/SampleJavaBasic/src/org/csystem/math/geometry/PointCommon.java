
/*-------------------------------------------------------------
	FILE		: PointCommon.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 14th Oct 2023

    Utility friendly class for common operations of
    Point and MutablePoint classes

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.math.geometry;

final class PointCommon {
    private PointCommon()
    {
    }

    static final double DELTA = 0.00001;

    static boolean equals(double x1, double y1, double x2, double y2)
    {
        return Math.abs(x1 - x2) < DELTA && Math.abs(y1 - y2) < DELTA;
    }

    static double distance(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    static String toString(double x, double y)
    {
        return String.format("(%f, %f)", x, y);
    }
}
