/*
 * HomeAction.java
 *
 * Copyright (c) 2013, Instituto Superior Técnico. All rights reserved.
 *
 * This file is part of bennu-renderers.
 *
 * bennu-renderers is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * bennu-renderers is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with bennu-renderers.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.bennu.renderers.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.bennu.dispatch.Application;
import pt.ist.bennu.dispatch.Functionality;
import pt.ist.bennu.renderers.annotation.Mapping;

@Mapping(path = "/home")
@Application(
		path = "home",
		group = "anyone",
		bundle = "resources.RendererResources",
		title = "title.renderers.home",
		description = "title.renderers.home.description")
public class HomeAction extends ContextBaseAction {
	@Functionality(
			app = HomeAction.class,
			path = "view",
			group = "anyone",
			bundle = "resources.RendererResources",
			title = "title.renderers.home",
			description = "title.renderers.home.description")
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return forward(request, "/renderers/home.jsp");
	}
}
