package blog.valerioemanuele.myriad;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

import lombok.Getter;

@Getter
public class CustomVisitor extends SimpleFileVisitor<Path> {
	private HashMap<String, FileWrapper> files;
	private Path rootPath;
	
	public CustomVisitor(Path rootPath) {
		files = new HashMap<>();
		this.rootPath = rootPath;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		if(!dir.equals(rootPath)) {
			files.putIfAbsent(topLevelFolderOrFile(dir), buildEmptyDir(dir));
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		String key = topLevelFolderOrFile(file);
		FileWrapper fielWrapper = files.get(key) == null ? buildEmptyFile(file) : files.get(key);
		fielWrapper.setSize(fielWrapper.getSize() + file.toFile().length());
		files.put(key, fielWrapper);		
		
		return FileVisitResult.CONTINUE;
	}
	
	private String topLevelFolderOrFile(Path file) {
		if(file.getParent().endsWith(rootPath)) {
			return file.getFileName().toString();
		}
		return topLevelFolderOrFile(file.getParent());
	}

	private FileWrapper buildEmptyFile(Path dir) {
		return buildEmptyFileWrapper(dir, false);
	}
	
	private FileWrapper buildEmptyDir(Path dir) {
		return buildEmptyFileWrapper(dir, true);
	}
	
	private FileWrapper buildEmptyFileWrapper(Path dir, boolean isDir) {
		return FileWrapper.builder().file(dir.toFile()).size(0L).isDir(isDir).build();
	}

}
