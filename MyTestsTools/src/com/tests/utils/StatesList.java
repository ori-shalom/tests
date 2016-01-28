package com.tests.utils;

import java.util.HashMap;
import java.util.Map;

public class StatesList {
	private Map<Integer,ScreenState> statesList;

	public StatesList() {
		this.statesList = new HashMap<Integer, ScreenState>();

		this.statesList.put(5, new ScreenState(5, 709, 282));
		this.statesList.put(4, new ScreenState(4, 1155, 664));
		this.statesList.put(3, new ScreenState(3, 1057, 669));
		this.statesList.put(2, new ScreenState(2, 847, 338));
		this.statesList.put(1, new ScreenState(1, 750, 395));
		this.statesList.put(0, new ScreenState(0, 711, 229));
		
	}
	
	public ScreenState getState(int stateId) {
		return statesList.get(stateId);
	}
}
