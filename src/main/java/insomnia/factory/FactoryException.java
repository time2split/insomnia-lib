package insomnia.factory;

import insomnia.builder.BuilderException;

public class FactoryException extends BuilderException
{
	private static final long	serialVersionUID	= 1L;

	public FactoryException(String m)
	{
		super(m);
	}

}
