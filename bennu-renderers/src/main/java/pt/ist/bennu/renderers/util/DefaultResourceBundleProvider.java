package pt.ist.bennu.renderers.util;

import java.util.Properties;
import java.util.ResourceBundle;

import pt.ist.bennu.core.util.Language;

public class DefaultResourceBundleProvider extends AbstractMessageResourceProvider {

	public DefaultResourceBundleProvider() {
		super();
	}

	public DefaultResourceBundleProvider(Properties bundleMappings) {
		super(bundleMappings);
	}

	@Override
	public String getMessage(String bundle, String key, String... args) {
		if (containsMapping(bundle)) {
			return format(ResourceBundle.getBundle(getBundleMapping(bundle), Language.getLocale()).getString(key), args);
		} else {
			return format(ResourceBundle.getBundle(bundle, Language.getLocale()).getString(key), args);
		}
	}

}
