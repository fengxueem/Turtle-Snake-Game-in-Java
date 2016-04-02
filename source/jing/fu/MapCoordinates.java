package jing.fu;

import java.util.*;

public class MapCoordinates {

	static HashMap<Led,int[]> map = new HashMap<Led,int[]>();// generate a static hashmap to hold Led and its position
	private MapCoordinates() {};// keep the constructor from be overwritten and keep it empty.

	public static void put(Led l,int[] i) {
		map.put(l,i);
	}

	public static int[] get(Led l) {
		 return map.get(l);
	}

}