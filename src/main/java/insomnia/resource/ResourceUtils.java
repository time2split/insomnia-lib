package insomnia.resource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ResourceUtils
{

	/**
	 * List directory contents for a resource folder. Not recursive. This is
	 * basically a brute-force implementation. Works for regular files and also
	 * JARs.
	 * 
	 * @author Greg Briggs
	 * @author Olivier Rodriguez
	 * @param clazz
	 *            Any java class that lives in the same place as the resources
	 *            you want.
	 * @param path
	 *            Should end with "/", but not start with one.
	 * @return Just the name of each member item, not the full paths.
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static String[] getResourcesOf(@SuppressWarnings("rawtypes") Class clazz, String path)
			throws URISyntaxException, IOException
	{
		URL dirURL = clazz.getResource(path);

		while (path.startsWith("/"))
			path = path.substring(1);

		if (dirURL != null && dirURL.getProtocol().equals("file"))
		{
			return new File(dirURL.toURI()).list();
		}

		if (dirURL == null)
		{
			/*
			 * In case of a jar file, we can't actually find a directory. Have
			 * to assume the same jar as clazz.
			 */
			String me = clazz.getName().replace(".", "/") + ".class";
			dirURL = clazz.getClassLoader().getResource(me);
		}

		if (dirURL.getProtocol().equals("jar"))
		{
			String jarPath = dirURL.getPath().substring(5,
				dirURL.getPath().indexOf("!"));
			JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
			Enumeration<JarEntry> entries = jar.entries();
			Set<String> result = new HashSet<String>();
			
			while (entries.hasMoreElements())
			{
				String name = entries.nextElement().getName();

				if (name.startsWith(path))
				{
					String entry = name.substring(path.length());

					int checkSubdir = entry.indexOf("/");
					if (checkSubdir >= 0)
					{
						// if it is a subdirectory, we just return the directory
						// name
						entry = entry.substring(0, checkSubdir);
					}
					result.add(entry);
				}
			}
			jar.close();
			return result.toArray(new String[result.size()]);
		}

		throw new UnsupportedOperationException(
			"Cannot list files for URL " + dirURL);
	}
}
