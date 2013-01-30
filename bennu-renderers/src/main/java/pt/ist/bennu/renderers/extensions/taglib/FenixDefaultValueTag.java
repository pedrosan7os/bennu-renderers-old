package pt.ist.bennu.renderers.extensions.taglib;

import pt.ist.bennu.renderers.core.taglib.CreateObjectTag;
import pt.ist.bennu.renderers.core.taglib.DefaultValueTag;

public class FenixDefaultValueTag extends DefaultValueTag {

	@Override
	protected CreateObjectTag getParentCreateTag() {
		return (CreateObjectTag) findAncestorWithClass(this, FenixCreateObjectTag.class);
	}

}
