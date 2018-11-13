package com.BehaviorPattern.statePattern;

/**
 * State design pattern is one of the behavioral design pattern. State design
 * pattern is used when an Object change its behavior based on its internal
 * state. Then use if-else condition block to perform different actions based on
 * the state. State design pattern is used to provide a systematic and loosely
 * coupled way to achieve this through Context and State implementations.
 */
public class TVRemoteBasic {

	private String state = "";

	public void setState(String state) {
		this.state = state;
	}

	public void doAction() {
		if (state.equalsIgnoreCase("ON")) {
			System.out.println("TV is turned ON");
		} else if (state.equalsIgnoreCase("OFF")) {
			System.out.println("TV is turned OFF");
		}
	}

	public static void main(String args[]) {
		TVRemoteBasic remote = new TVRemoteBasic();

		remote.setState("ON");
		remote.doAction();

		remote.setState("OFF");
		remote.doAction();
	}

}
