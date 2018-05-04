package consulo.internal.windows;

import static consulo.internal.windows.WindowsFileDialog.trace;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author VISTALL
 * @since 2018-05-04
 */
class LibraryLoader
{
	static void loadLibrary() throws Exception
	{
		String libraryName;
		if(System.getProperty("os.arch").indexOf("64") >= 0)
		{
			libraryName = "windows-file-chooser64.dll";
		}
		else
		{
			libraryName = "windows-file-chooser.dll";
		}

		trace("JNI>>: Loading " + libraryName);

		Path tempFile = Files.createTempFile("windows-file-chooser", ".dll");

		try (InputStream stream = LibraryLoader.class.getResourceAsStream("/" + libraryName))
		{
			Files.copy(stream, tempFile, StandardCopyOption.REPLACE_EXISTING);
		}

		System.load(tempFile.toFile().getAbsolutePath());
	}
}
