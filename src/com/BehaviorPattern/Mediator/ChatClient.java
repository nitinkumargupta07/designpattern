package com.BehaviorPattern.Mediator;


import java.util.ArrayList;
import java.util.List;

/**
 * Mediator pattern is used to reduce communication complexity between multiple
 * objects or classes. This pattern provides a mediator class which normally
 * handles all the communications between different classes and supports easy
 * maintenance of the code by loose coupling. Mediator pattern falls under
 * behavioral pattern category
 * 
 * Air traffic controller is a great example of mediator pattern where the
 * airport control room works as a mediator for communication between different
 * flights. Mediator works as a router between objects and it can have it�s own
 * logic to provide way of communication.
 * 
 * @author 44106716
 *
 */

interface ChatMediator {
	public void sendMessage(String msg, User user);

	void addUser(User user);
}

abstract class User {
	protected ChatMediator mediator;
	protected String name;

	public User(ChatMediator med, String name) {
		this.mediator = med;
		this.name = name;
	}

	public abstract void send(String msg);

	public abstract void receive(String msg);
}

class UserImpl extends User {

	public UserImpl(ChatMediator med, String name) {
		super(med, name);
	}

	@Override
	public void send(String msg) {
		System.out.println(this.name + ": Sending Message=" + msg);
		mediator.sendMessage(msg, this);
	}

	@Override
	public void receive(String msg) {
		System.out.println(this.name + ": Received Message:" + msg);
	}

}

class ChatMediatorImpl implements ChatMediator {

	private List<User> users;

	public ChatMediatorImpl() {
		this.users = new ArrayList<>();
	}

	@Override
	public void addUser(User user) {
		this.users.add(user);
	}

	@Override
	public void sendMessage(String msg, User user) {
		for (User u : this.users) {
			// message should not be received by the user sending it
			if (u != user) {
				u.receive(msg);
			}
		}
	}

}

public class ChatClient {

	public static void main(String[] args) {
		ChatMediator mediator = new ChatMediatorImpl();
		User user1 = new UserImpl(mediator, "Pankaj");
		User user2 = new UserImpl(mediator, "Lisa");
		User user3 = new UserImpl(mediator, "Saurabh");
		User user4 = new UserImpl(mediator, "David");
		mediator.addUser(user1);
		mediator.addUser(user2);
		mediator.addUser(user3);
		mediator.addUser(user4);

		user1.send("Hi All");

	}

}
