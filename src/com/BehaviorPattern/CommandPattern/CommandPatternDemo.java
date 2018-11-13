package com.BehaviorPattern.CommandPattern;

/**
 * One example of the command pattern being executed in the real world is the
 * idea of a table order at a restaurant: the waiter takes the order, which is a
 * command from the customer.This order is then queued for the kitchen staff.
 * The waiter tells the chef that the a new order has come in, and the chef has
 * enough information to cook the meal.
 * 
 * Command, which allows the requester of a particular action to be decoupled
 * from the object that performs the action. Where the Chain of Responsibility
 * pattern forwarded requests along a chain, the Command pattern forwards the
 * request to a specific module.
 * 
 * A request is wrapped under an object as command and passed to invoker object.
 * Invoker object looks for the appropriate object which can handle this command
 * and passes the command to the corresponding object which executes the
 * command.
 * 
 * @author 44106716
 */

// Command
interface Command {
	public void execute();
}

// Receiver
class Light {
	private boolean on;

	public void switchOn() {
		System.out.println("Light is on"); 
		on = true;
	}

	public void switchOff() {
		System.out.println("Light is off"); 
		on = false;
	}

}

// Concrete Command
class LightOnCommand implements Command {
	// reference to the light
	Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.switchOn();
	}

}

// Concrete Command
class LightOffCommand implements Command {
	// reference to the light
	Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.switchOff();
	}
}

// Invoker
class RemoteControl {
	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void pressButton() {
		command.execute();
	}
}

public class CommandPatternDemo {
	public static void main(String[] args) {
		RemoteControl control = new RemoteControl();
		Light light = new Light();
		Command lightsOn = new LightOnCommand(light);
		Command lightsOff = new LightOffCommand(light);
		// switch on
		control.setCommand(lightsOn);
		control.pressButton();
		// switch off
		control.setCommand(lightsOff);
		control.pressButton();

	}
}
