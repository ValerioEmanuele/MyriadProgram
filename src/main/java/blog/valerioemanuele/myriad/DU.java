package blog.valerioemanuele.myriad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

public class DU {

	public static void main(String[] args) {
		try {
			Validator.validate(args);
			traverse(args[0]);
		} catch (EmptyFolderException | IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void traverse(String rootPath) throws EmptyFolderException, IOException {
		Validator.validate(rootPath);
		Path p = Paths.get(rootPath);

		CustomVisitor cv = new CustomVisitor(p);
		Files.walkFileTree(p, cv);
		cv.getFiles().entrySet().stream()
				.sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
				.forEach(e -> fileInfo(e.getValue()));

	}

	public static void fileInfo(FileWrapper f) {
		System.out.println(MessageFormat.format("{0} {1} {2}KB", dirOrFile(f.isDir()), f.getFile().getAbsolutePath(),
				f.getSize() / 1024));
	}

	public static String dirOrFile(boolean isDir) {
		return isDir ? "DIR" : "FILE";
	}
}
