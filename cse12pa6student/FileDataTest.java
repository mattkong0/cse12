import static org.junit.Assert.*;

import org.junit.*;

public class FileDataTest {
	
	@Test
	public void createFile() {
		String name = "Matthew";
		String directory = "Home";
		String date = "November 29";
		FileData newFile = new FileData(name, directory, date);
		assertEquals("Name: Matthew, Directory: Home, Modified Date: November 29", newFile.toString());
	}
	
	@Test
	public void nullFile() {
		String name = null;
		String directory = null;
		String date = null;
		FileData nullFile = new FileData(name, directory, date);
		assertEquals(null, nullFile.toString());
	}
}
