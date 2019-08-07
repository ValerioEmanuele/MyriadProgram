package blog.valerioemanuele.myriad;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

public interface Validator {
	
	static void validate(String path) throws EmptyFolderException {
		if(StringUtils.isBlank(path)) {
			throw new IllegalArgumentException("You have provided an empty path");
		}
		
		Path dirPath = Paths.get(path);
		if(!Files.isDirectory(dirPath, LinkOption.NOFOLLOW_LINKS)) {
			throw new InvalidPathException(path, "The path is not a directory");
		}
		else if(new File(dirPath.toString()).list().length==0) {
			throw new EmptyFolderException("The folder passed as arg is empty");
		}
	}

	static void validate(String[] args) throws EmptyFolderException {
		if(args == null || args.length == 0) {
			throw new IllegalArgumentException("The application was started without any argument");
		}
		
		validate(args[0]);
	}
}
