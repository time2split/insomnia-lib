package insomnia.reader;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Permet la lecture d'objets depuis un flux quelconque
 * 
 * @author zuri
 * 
 */
public abstract class Reader implements Closeable
{
	private InputStream source;

	public Reader()
	{
		setSource(System.in);
	}

	public Reader(InputStream s)
	{
		setSource(s);
	}

	public Reader(String s)
	{
		setSource(s);
	}

	public Reader(File f) throws FileNotFoundException
	{
		setSource(f);
	}

	@Override
	public void finalize() throws IOException
	{
		close();
	}

	// =========================================================================

	abstract public Object read() throws ReaderException, IOException;

	abstract public Object nextRead() throws ReaderException, IOException;

	// =========================================================================

	@Override
	public void close() throws IOException
	{
		source.close();
	}

	// =========================================================================

	public void setSource(InputStream stream)
	{
		source = stream;
	}

	public void setSource(String s)
	{
		setSource(new ByteArrayInputStream(s.getBytes()));
	}

	public void setSource(File f) throws FileNotFoundException
	{
		setSource(new BufferedInputStream(new FileInputStream(f)));
	}

	public InputStream getSource()
	{
		return source;
	}
}
