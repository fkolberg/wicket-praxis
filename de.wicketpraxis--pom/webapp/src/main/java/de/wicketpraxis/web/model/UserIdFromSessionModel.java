/*****************************************
Quelltexte zum Buch: Praxisbuch Wicket
(http://www.hanser.de/978-3-446-41909-4)

Autor: Michael Mosmann
(michael@mosmann.de)
*****************************************/
package de.wicketpraxis.web.model;

import org.apache.wicket.model.LoadableDetachableModel;

import de.wicketpraxis.web.session.WicketPraxisSession;

@Deprecated
public class UserIdFromSessionModel extends LoadableDetachableModel<Integer>
{
	@Override
	protected Integer load()
	{
//		return WicketPraxisSession.get().getUserId();
		return null;
	}
}
