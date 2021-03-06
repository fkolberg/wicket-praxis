/*****************************************
 * Quelltexte zum Buch: Praxisbuch Wicket
 * (http://www.hanser.de/978-3-446-41909-4)
 * 
 * Autor: Michael Mosmann
 * (michael@mosmann.de)
 *****************************************/
package de.wicketpraxis.web.thema.debug.visitor;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

public class DebugLabel extends Label {

	public DebugLabel(String id) {
		super(id, new Model<String>());
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();

		getPage().visitChildren(Label.class, new IVisitor<Label,Void>() {

			@Override
			public void component(Label component, IVisit<Void> visit) {
				if (component != DebugLabel.this) {
					// irgendeine Überprüfung
					if ("ist".equals(component.getDefaultModelObject())) {
						component.add(new MarkComponentBorder());
					}
					if ("ein".equals(component.getDefaultModelObject())) {
						component.add(new AttributeModifier("style", true, Model.of("border:1px solid red;")));
					}
				}
				visit.dontGoDeeper();
			}
		});
	}
}
