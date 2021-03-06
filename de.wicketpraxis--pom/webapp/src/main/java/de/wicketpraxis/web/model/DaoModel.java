/*****************************************
 * Quelltexte zum Buch: Praxisbuch Wicket
 * (http://www.hanser.de/978-3-446-41909-4)
 * 
 * Autor: Michael Mosmann
 * (michael@mosmann.de)
 *****************************************/
package de.wicketpraxis.web.model;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

import de.wicketpraxis.persistence.DaoInterface;
import de.wicketpraxis.persistence.DoInterface;

public class DaoModel<K extends Serializable, T extends DoInterface<K>> implements IModel<T> {

	DaoInterface<K, T> _dao;

	private K _id;
	private transient T _object;

	private transient boolean _attached = false;

	public DaoModel(DaoInterface<K, T> dao) {
		_dao = dao;
	}

	public DaoModel(DaoInterface<K, T> dao, T object) {
		this(dao);

		_object = object;
		_id = _object.getId();

		_attached = true;
	}

	public DaoModel(DaoInterface<K, T> dao, K id) {
		this(dao);

		_id = id;
	}

	protected T load(K id) {
		if (id == null)
			return _dao.getNew();
		return _dao.get(id);
	}

	public T getObject() {
		if (!_attached) {
			_object = load(_id);
			_attached = true;
		}
		return _object;
	}

	public void setObject(T object) {
		_attached = false;
		_id = null;

		_object = object;
		if (_object != null) {
			_id = _object.getId();
			_attached = true;
		}
	}

	public void detach() {
		_object = null;
		_attached = false;
	}

}
