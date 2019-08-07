package blog.valerioemanuele.myriad;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TestDU {

	@Test
	void test_EmptyPathThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> DU.traverse(null));
		assertThrows(IllegalArgumentException.class, () -> DU.traverse(""));
		assertThrows(IllegalArgumentException.class, () -> DU.traverse(" "));
	}
	
	@Test
	void test_GivenNotExistingPathThrowsException() {
		assertThrows(InvalidPathException.class, () -> DU.traverse("folder_that_doesnt_exists"));
	}
	
	@Test
	void test_GivenFileThrowsException() {
		Path root = Paths.get(".").normalize().toAbsolutePath();
		Path filePath = Paths.get(root.toString(),"pom.xml");
		
		assertThrows(InvalidPathException.class, () -> DU.traverse(filePath.toString()));
	}
	
	@Test
	@Disabled //To run this test create an empty folder named empty_folder under test_du folder
	void test_GivenEmptyFolderThrowsException() {
		Path root = Paths.get(".").normalize().toAbsolutePath();
		Path filePath = Paths.get(root.toString(),"test_du","empty_folder");
		
		EmptyFolderException thrown = assertThrows(EmptyFolderException.class, () -> DU.traverse(filePath.toString()));
		assertEquals("The folder passed as arg is empty", thrown.getMessage());
	}
	
	@Test
	void test_GivenFolderWithFilesCalculatesSizes() throws EmptyFolderException, IOException {
		Path root = Paths.get(".").normalize().toAbsolutePath();
		Path filePath = Paths.get(root.toString(),"test_du");
		DU.traverse(filePath.toString());
	}
	
	@Test
	void test_emptyArgsThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> DU.main(null));
		assertThrows(IllegalArgumentException.class, () -> DU.main(new String[] {}));
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> DU.main(new String[] {""}));
		assertEquals("You have provided an empty path", thrown.getMessage());
		
		assertThrows(IllegalArgumentException.class, () -> DU.main(new String[] {"i_am_not_a_folder"}));
	}
	
	@Test
	@Disabled //To run this test create an empty folder named empty_folder under test_du folder
	void test_emptyPathArgsIsProcessed() {
		Path root = Paths.get(".").normalize().toAbsolutePath();
		Path filePath = Paths.get(root.toString(),"test_du","empty_folder");
		DU.main(new String[] {filePath.toString()});
	}
	
	@Test
	void test_validPathArgsIsProcessed() {
		Path root = Paths.get(".").normalize().toAbsolutePath();
		Path filePath = Paths.get(root.toString());
		DU.main(new String[] {filePath.toString()});
	}
}
