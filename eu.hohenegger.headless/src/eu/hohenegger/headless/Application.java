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

		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		// nothing to do
	}
}