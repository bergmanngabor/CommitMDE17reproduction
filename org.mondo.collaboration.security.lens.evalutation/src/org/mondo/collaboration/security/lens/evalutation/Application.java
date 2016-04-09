package org.mondo.collaboration.security.lens.evalutation;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.ui.contexts.IContext;

public class Application implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		
	}

}
