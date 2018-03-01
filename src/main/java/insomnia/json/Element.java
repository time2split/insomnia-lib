package insomnia.json;

import java.util.Collection;

public abstract class Element implements Cloneable
{
	public boolean isArray()
	{
		return false;
	}

	public boolean isObject()
	{
		return false;
	}

	public boolean isNumber()
	{
		return false;
	}

	public boolean isString()
	{
		return false;
	}

	public boolean isLiteral()
	{
		return false;
	}

	/**
	 * Ajoute l'élément e dans l'élément this
	 * 
	 * @param e
	 * @return null si impossible, e si ok
	 */
	public Element add(Element e, String l)
	{
		if (isArray())
		{
			((ElementArray) this).add(e);
			return e;
		}
		else if (isObject())
		{
			((ElementObject) this).add(l,e);
			return e;
		}
		return null;
	}

	/**
	 * Version de add avec retour d'exception
	 * 
	 * @see Element#add(Element)
	 * @param e
	 * @throws UnsupportedOperationException
	 */
	public void add_alert(Element e, String l) throws UnsupportedOperationException
	{
		if (null == add(e,l))
			throw new UnsupportedOperationException(
				"Cannot add an element on a " + this.getClass().getName());
	}

	@Override
	public abstract Element clone();

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Element))
			return false;

		return ((Element) o).getValue().equals(getValue());
	}

	@Override
	public int hashCode()
	{
		return this.getValue().hashCode();
	}

	public abstract Object getValue();

	/**
	 * Suit un chemin dans l'arborescence
	 * 
	 * @param keys
	 *            Ensemble des clés à parcourir
	 * @param offset
	 *            Indice de keys[i] de départ
	 * @return
	 */
	public Element followPath(String[] keys, int offset)
	{
		// Comportement par défaut des objets non traversables
		if (offset == keys.length)
			return this;

		if (keys[0].equals(""))
			return this.followPath(keys, offset++);

		return null;
	}

	final public Element followPath(String[] keys)
	{
		return followPath(keys, 0);
	}

	final public Element followPath(Collection<String> keys)
	{
		return followPath((String[]) keys.toArray(new String[0]));
	}

	final public Element followPath(String path)
	{
		return followPath(path.split("\\."));
	}

	public String toString()
	{
		return "element(" + getValue().toString() + ")";
	}
}