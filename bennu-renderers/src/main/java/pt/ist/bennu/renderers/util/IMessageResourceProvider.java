package pt.ist.bennu.renderers.util;

public interface IMessageResourceProvider {

	public String getMessage(String bundle, String key, String... args);

}
