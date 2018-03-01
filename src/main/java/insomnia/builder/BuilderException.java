package insomnia.builder;

public class BuilderException extends Exception
{
	private static final long serialVersionUID = 1L;

	public BuilderException(String m)
	{
		super(m);
	}

	public BuilderException(Exception e)
	{
		super(e);
	}
}
