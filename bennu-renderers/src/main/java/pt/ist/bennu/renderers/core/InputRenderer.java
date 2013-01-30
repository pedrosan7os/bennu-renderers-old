package pt.ist.bennu.renderers.core;

import java.util.List;

import org.apache.commons.collections.Predicate;

import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlFormComponent;
import pt.ist.bennu.renderers.core.components.Validatable;
import pt.ist.bennu.renderers.core.contexts.InputContext;
import pt.ist.bennu.renderers.core.contexts.PresentationContext;
import pt.ist.bennu.renderers.core.model.MetaSlot;
import pt.ist.bennu.renderers.core.utils.RenderKit;
import pt.ist.bennu.renderers.core.utils.RenderMode;
import pt.ist.bennu.renderers.core.validators.HtmlChainValidator;
import pt.ist.bennu.renderers.core.validators.HtmlValidator;

/**
 * The base renderer for every input renderer.
 * 
 * @author cfgi
 */
public abstract class InputRenderer extends AbstractRenderer {
	public InputContext getInputContext() {
		return (InputContext) getContext();
	}

	protected Validatable findValidatableComponent(HtmlComponent component) {
		if (component == null) {
			return null;
		}

		if (component instanceof Validatable) {
			return (Validatable) component;
		} else {
			List<HtmlComponent> children = component.getChildren(new Predicate() {

				@Override
				public boolean evaluate(Object component) {
					if (!(component instanceof HtmlFormComponent)) {
						return false;
					}

					HtmlFormComponent formComponent = (HtmlFormComponent) component;
					return formComponent.hasTargetSlot();
				}

			});

			if (children.size() > 0) {
				return (Validatable) children.get(0);
			}
		}

		return null;
	}

	protected HtmlChainValidator getChainValidator(Validatable inputComponent, MetaSlot slot) {
		if (inputComponent == null) {
			return null;
		}

		HtmlChainValidator chainValidator = new HtmlChainValidator(inputComponent);
		for (HtmlValidator validator : slot.getValidatorsList()) {
			chainValidator.addValidator(validator);
		}
		return chainValidator;
	}

	@Override
	protected HtmlComponent renderSlot(MetaSlot slot) {
		PresentationContext newContext = getContext().createSubContext(slot);
		newContext.setSchema(slot.getSchema() != null ? slot.getSchema() : null);
		newContext.setLayout(slot.getLayout());
		newContext.setProperties(slot.getProperties());

		if (slot.isReadOnly()) {
			newContext.setRenderMode(RenderMode.OUTPUT);
		}

		Object value = slot.getObject();
		Class type = slot.getType();

		RenderKit kit = RenderKit.getInstance();
		return kit.render(newContext, value, type);
	}
}
