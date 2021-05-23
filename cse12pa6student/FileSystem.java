import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystem {

    MyHashMap<String, ArrayList<FileData>> nameMap;
    MyHashMap<String, ArrayList<FileData>> dateMap;

    // TODO
    public FileSystem() {
    	nameMap = new MyHashMap<String, ArrayList<FileData>>();
    	dateMap = new MyHashMap<String, ArrayList<FileData>>();
    }

    // TODO
    public FileSystem(String inputFile) {
    	String key = inputFile;
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                String name = data[0];
                String directory = data[1];
                String date = data[2];
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    // TODO
    @SuppressWarnings("unchecked")
	public boolean add(String fileName, String directory, String modifiedDate) {
    	FileData newFile = new FileData(fileName, directory, modifiedDate);
    	if (nameMap.key.equals(fileName)) {
    		return false;
    	}
    	return true;
    }

    // TODO
    public FileData findFile(String name, String directory) {

    }

    // TODO
    public ArrayList<String> findAllFilesName() {

    }

    // TODO
    public ArrayList<FileData> findFilesByName(String name) {

    }

    // TODO
    public ArrayList<FileData> findFilesByDate(String modifiedDate) {

    }

    // TODO
    public ArrayList<FileData> findFilesInMultDir(String modifiedDate) {

    }

    // TODO
    public boolean removeByName(String name) {

    }

    // TODO
    public boolean removeFile(String name, String directory) {

    }

}
