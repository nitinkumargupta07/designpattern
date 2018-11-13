package com.BehaviorPattern.Iterator;


/**
 * Iterator design pattern in one of the behavioral pattern. Iterator pattern is
 * used to provide a standard way to traverse through a group of Objects.
 * Iterator pattern is widely used in Java Collection Framework
 * 
 * @author 44106716
 *
 */

interface Iterator {
	public boolean hasNext();

	public Object next();
}

interface Container {
	public Iterator getIterator();
}

class NameRepository implements Container {
	public String names[] = { "Robert", "John", "Julie", "Lora" };

	@Override
	public Iterator getIterator() {
		return new NameIterator();
	}

	private class NameIterator implements Iterator {

		int index;

		@Override
		public boolean hasNext() {

			if (index < names.length) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {

			if (this.hasNext()) {
				return names[index++];
			}
			return null;
		}
	}
}

public class IteratorDesignPattern {

	public static void main(String[] args) {
		NameRepository namesRepository = new NameRepository();

		for (Iterator iter = namesRepository.getIterator(); iter.hasNext();) {
			String name = (String) iter.next();
			System.out.println("Name : " + name);
		}
	}

}

