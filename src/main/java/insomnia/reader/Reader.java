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
	// private java.io.Reader rsource;

	public Reader()
	{
		setSource(System.in);
	}

	// public Reader(java.io.Reader r)
	// {
	// setSource(r);
	// }

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

	// public int readInt() throws IOException
	// {
	// if (rsource != null)
	// return rsource.read();
	//
	// return source.read();
	// }

	abstract public Object read() throws ReaderException, IOException;

	abstract public Object nextRead() throws ReaderException, IOException;

	// =========================================================================

	@Override
	public void close() throws IOException
	{
		source.close();
	}

	// =========================================================================

	// public void setSource(java.io.Reader reader)
	// {
	// rsource = reader;
	// source = null;
	// }

	public void setSource(InputStream stream)
	{
//		rsource = null;
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
