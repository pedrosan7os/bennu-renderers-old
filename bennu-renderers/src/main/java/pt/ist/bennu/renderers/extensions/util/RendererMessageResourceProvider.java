package pt.ist.bennu.renderers.extensions.util;

import java.util.Properties;

import pt.ist.bennu.renderers.core.utils.RenderUtils;
import pt.ist.bennu.renderers.util.AbstractMessageResourceProvider;

public class RendererMessageResourceProvider extends AbstractMessageResourceProvider {

	public RendererMessageResourceProvider() {
		super();
	}

	public RendererMessageResourceProvider(Properties bundleMappings) {
		super(bundleMappings);
	}

	@Override
	public String getMessage(String bundle, String key, String... args) {
		if (containsMapping(bundle)) {
			return format(RenderUtils.getResourceString(getBundleMapping(bundle), key), args);
		} else {
			return format(RenderUtils.getResourceString(bundle, key), args);
		}
	}
}
