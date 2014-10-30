package eu.hohenegger.headless;

import java.util.Map;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class Application implements IApplication {
	private static final String APPLICATION_ARGS = "application.args";

	@Override
	public Object start(final IApplicationContext context) throws Exception {

		@SuppressWarnings("unchecked")
		final Map<String, Object> args = context.getArguments();
		String[] appArgs = (String[]) args.get(APPLICATION_ARGS);

		for (String string : appArgs) {
			System.out.println(string);
		}

		waitForGoshCommandToBeRegistered();

		return IApplication.EXIT_OK;
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