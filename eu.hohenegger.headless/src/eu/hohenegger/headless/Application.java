package eu.hohenegger.headless;

import java.util.Map;
import java.util.Scanner;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class Application implements IApplication {
//	private static final String APPLICATION_ARGS = "application.args";

	@Override
	public Object start(final IApplicationContext context) throws Exception {

		@SuppressWarnings("unchecked")
		final Map<String, Object> args = context.getArguments();
//		String[] appArgs = (String[]) args.get(APPLICATION_ARGS);

		waitForGoshCommandToBeRegistered();
		
		startRepl();

		return IApplication.EXIT_OK;
	}

	private static final String fNEW_LINE = System.getProperty("line.separator");

	private static String getInheritanceTree(Class<?> aClass) {
		StringBuilder superclasses = new StringBuilder();
		superclasses.append(fNEW_LINE);
		Class<?> theClass = aClass;
		while (theClass != null) {
			superclasses.append(theClass);
			superclasses.append(fNEW_LINE);
			theClass = theClass.getSuperclass();
		}
		superclasses.append(fNEW_LINE);
		return superclasses.toString();
	}

	private void startRepl() {
		Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a package-qualified class name:");
		while (sc.hasNextLine()) {
			String command = sc.nextLine();
			if ("exit".equals(command)) {
				break;
			}
			try {
				Class<?> theClass = Class.forName(command);
				System.out.println("The inheritance tree: " + getInheritanceTree(theClass));
			} catch (ClassNotFoundException e) {
				 System.out.println("Cannot find that class.");
			}
		}
		sc.close();
	}

	private void waitForGoshCommandToBeRegistered() {
		// this is just to workaround the 'sleep interrupted' that otherwise
		// would be thrown in: org.apache.felix.gogo.shell.Activator
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// do nothing
		}
	}

	@Override
	public void stop() {

	}
}