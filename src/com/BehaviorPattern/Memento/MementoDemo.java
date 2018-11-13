package com.BehaviorPattern.Memento;


import java.util.ArrayList;

/**
 * One of the best real life example is the text editors where we can save it’s
 * data anytime and use undo to restore it to previous saved state.
 * 
 * @author 44106716 Memento design pattern is used when we want to save the
 *         state of an object so that we can restore later on. Memento pattern
 *         is used to implement this in such a way that the saved state data of
 *         the object is not accessible outside of the object, this protects the
 *         integrity of saved state data.
 */

class Memento {
	private String state;

	public Memento(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}

class Originator {
	private String state;
	/*
	 * lots of memory consumptive private data that is not necessary to define
	 * the state and should thus not be saved. Hence the small memento object.
	 */

	public void setState(String state) {
		System.out.println("Originator: Setting state to " + state);
		this.state = state;
	}

	public Memento save() {
		System.out.println("Originator: Saving to Memento.");
		return new Memento(state);
	}

	public void restore(Memento m) {
		state = m.getState();
		System.out.println("Originator: State after restoring from Memento: " + state);
	}
}

class Caretaker {
	private ArrayList<Memento> mementos = new ArrayList<>();

	public void addMemento(Memento m) {
		mementos.add(m);
	}

	public Memento getMemento() {
		return mementos.get(1);
	}
}

public class MementoDemo {
	public static void main(String[] args) {
		Caretaker caretaker = new Caretaker();
		Originator originator = new Originator();
		originator.setState("State1");
		originator.setState("State2");
		caretaker.addMemento(originator.save());
		originator.setState("State3");
		caretaker.addMemento(originator.save());
		originator.setState("State4");
		originator.restore(caretaker.getMemento());
	}
}