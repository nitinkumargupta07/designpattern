package com.StructuralPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.baeldung.com/java-dynamic-proxies
 * https://dzone.com/articles/java-dynamic-proxy
 * 
 * @author nitin
 *         <h1>Java Dynamic Proxy: What is a Proxy and How can We Use It</h1>
 *         Proxy is a design pattern. We create and use proxy objects when we
 *         want to add or modify some functionality of an already existing
 *         class. The proxy object is used instead of the original one.
 * 
 *         This way proxy classes can implement many things in a convenient way:
 * 
 *         1. logging when a method starts and stops
 *
 *         2. perform extra checks on arguments
 * 
 *         3. mocking the behavior of the original class
 * 
 *         4. implement lazy access to costly resources Without modifying the
 *         original code of the class.
 */

class DynamicInvocationHandler implements InvocationHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(DynamicInvocationHandler.class);

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		LOGGER.info("Invoked method: {}", method.getName());

		return 42;
	}
}

public class DynamicProxyTest {
	interface If {
		void originalMethod(String s);
	}

	static class Original implements If {
		public void originalMethod(String s) {
			System.out.println(s);
		}
	}

	static class Handler implements InvocationHandler {
		private final If original;

		public Handler(If original) {
			this.original = original;
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			System.out.println("BEFORE");
			method.invoke(original, args);
			System.out.println("AFTER");
			return null;
		}
	}

	public static void main(String[] args) {
		Original original = new Original();
		Handler handler = new Handler(original);
		If f = (If) Proxy.newProxyInstance(If.class.getClassLoader(), new Class[] { If.class }, handler);
		f.originalMethod("Hallo");

		Map proxyInstance = (Map) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
				new Class[] { Map.class }, new DynamicInvocationHandler());
		proxyInstance.put("hello", "world");

		Map mapProxyInstance = (Map) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
				new Class[] { Map.class }, new TimingDynamicInvocationHandler(new HashMap<>()));

		mapProxyInstance.put("hello", "world");

		CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
				new Class[] { CharSequence.class }, new TimingDynamicInvocationHandler("Hello World"));

		csProxyInstance.length();

		// Lambda expression
		proxyInstance = (Map) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(), new Class[] { Map.class },
				(proxy, method, methodArgs) -> {
					if (method.getName().equals("get")) {
						return 42;
					} else {
						throw new UnsupportedOperationException("Unsupported method: " + method.getName());
					}
				});

	}
}

class TimingDynamicInvocationHandler implements InvocationHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(TimingDynamicInvocationHandler.class);

	private final Map<String, Method> methods = new HashMap<>();

	private Object target;

	public TimingDynamicInvocationHandler(Object target) {
		this.target = target;

		for (Method method : target.getClass().getDeclaredMethods()) {
			this.methods.put(method.getName(), method);
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long start = System.nanoTime();
		Object result = methods.get(method.getName()).invoke(target, args);
		long elapsed = System.nanoTime() - start;

		LOGGER.info("Executing {} finished in {} ns", method.getName(), elapsed);

		return result;
	}
}