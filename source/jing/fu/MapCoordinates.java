package jing.fu;

import java.util.*;

public class MapCoordinates {
	static HashMap<Led,int[]> map=new HashMap<Led,int[]>();
	private MapCoordinates() {};
	public static void put(Led l,int[] i) {
		map.put(l,i);
	}

	public static int[] get(Led l) {
		 return map.get(l);
	}

}