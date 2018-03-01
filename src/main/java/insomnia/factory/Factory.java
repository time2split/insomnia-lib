package insomnia.factory;

/**
 * Création d'objets
 * 
 * @author zuri
 *
 */
public abstract class Factory
{
	private Object data;

	public Factory()
	{

	}

	public Factory(Object data)
	{
		setData(data);
	}

	public void setData(Object d)
	{
		data = d;
	}

	public Object getData()
	{
		return data;
	}

	abstract public Object create();

	public Object create_alert() throws FactoryException
	{
		Object ret = create();

		if (ret == null)
			throw new FactoryException(this.getClass().getName()
					+ " cannot create an object");

		return ret;
	}
}
