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
    	this.nameMap = new MyHashMap<String, ArrayList<FileData>>();
    	this.dateMap = new MyHashMap<String, ArrayList<FileData>>();
    }

    // TODO
    public FileSystem(String inputFile) {
    	this.nameMap = new MyHashMap<String, ArrayList<FileData>>();
    	this.dateMap = new MyHashMap<String, ArrayList<FileData>>();
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                add(data[0], data[1], data[2]);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    // TODO
	public boolean add(String fileName, String directory, String modifiedDate) {
    	FileData name = new FileData(fileName, directory, modifiedDate); //for nameMap
    	FileData date = new FileData(fileName, directory, modifiedDate); //for dateMap
    	if (this.nameMap.containsKey(fileName) || this.dateMap.containsKey(modifiedDate)) {
    		if (this.nameMap.containsKey(fileName)) {//file exists in nameMap
    			ArrayList<FileData> v1 = this.nameMap.get(fileName);
    			for (int i = 0; i < v1.size(); i += 1) {
    				if (v1.get(i).dir.equals(directory) && v1.get(i).name.equals(fileName)) {
    					return false;
    				}
    			}
    			v1.add(name);
    			this.nameMap.replace(fileName, v1);
    		}
    		else {
    			ArrayList<FileData> list = new ArrayList<FileData>();
    			list.add(name);
    			this.nameMap.put(fileName, list);
    		}
    		if (this.dateMap.containsKey(modifiedDate)) {//file exists in dateMap
    			ArrayList<FileData> v2 = this.dateMap.get(modifiedDate);
    			for (int i = 0; i < v2.size(); i += 1) {
    				if (v2.get(i).dir.equals(directory) && v2.get(i).name.equals(fileName)) {
    					return false;
    				}
    			}
    			v2.add(date);
    			this.dateMap.replace(modifiedDate, v2);
    		}
    		else {
    			ArrayList<FileData> list2 = new ArrayList<FileData>();
    			list2.add(date);
    			this.dateMap.put(modifiedDate, list2);
    		}
    	}
    	else {
    		// for nameMap
    		ArrayList<FileData> list = new ArrayList<FileData>();
			list.add(name);
			this.nameMap.put(fileName, list);
			
			// for dateMap
			ArrayList<FileData> list2 = new ArrayList<FileData>();
			list2.add(date);
			this.dateMap.put(modifiedDate, list2);
    	}
    	return true;
    }

    // TODO
    public FileData findFile(String name, String directory) {
    	if (this.nameMap.containsKey(name)) {
    		ArrayList<FileData> nameValue = new ArrayList<FileData>();
    		for (int i = 0; i < nameValue.size(); i += 1) {
    			if (nameValue.get(i).dir.equals(directory)) {
    				return nameValue.get(i);
    			}
    		}
    	}
    	return null;
    }

    // TODO
    public ArrayList<String> findAllFilesName() {
    	ArrayList<String> fileList = new ArrayList<String>();
    	if (this.nameMap.isEmpty() && this.dateMap.isEmpty()) {
    		return fileList;
    	}
    	int size = this.nameMap.keys().size();
    	for (int i = 0; i < size; i += 1) {
    		fileList.add(this.nameMap.keys().get(i));
    	}
    	return fileList;
    }

    // TODO
    public ArrayList<FileData> findFilesByName(String name) {
    	if (this.nameMap.get(name) != null) {
    		return this.nameMap.get(name);
    	}
    	return new ArrayList<FileData>();
    }

    // TODO
    public ArrayList<FileData> findFilesByDate(String modifiedDate) {
    	if (this.dateMap.get(modifiedDate) != null) {
    		return this.dateMap.get(modifiedDate);
    	}
    	return new ArrayList<FileData>();
    }

    // TODO
    public ArrayList<FileData> findFilesInMultDir(String modifiedDate) {
    	ArrayList<FileData> dateList = this.findFilesByDate(modifiedDate);
    	ArrayList<FileData> fileList = new ArrayList<FileData>();
    	for (int i = 0; i < dateList.size(); i += 1) {
    		FileData info = dateList.get(i);
    		for (int j = i + 1; j < dateList.size(); j += 1) {
    			FileData info2 = dateList.get(j);
    			if (info.name.equals(info2.name) && !info.dir.equals(info2.dir)) {
    				if (fileList.contains(info)) {
    					fileList.add(info);
    				}
    				else if (fileList.contains(info2)) {
    					fileList.add(info2);
    				}
    			}
    		}
    	}
    	return new ArrayList<FileData>();
    }

    // TODO
    public boolean removeByName(String name) {
    	if (!this.nameMap.containsKey(name)) {
    		return false;
    	}
    	ArrayList<FileData> nameList = this.findFilesByName(name);
    	this.nameMap.remove(name);
    	for (int i = 0; i < nameList.size(); i += 1) {
    		FileData info = nameList.get(i);
    		this.dateMap.remove(info.lastModifiedDate);
    	}
    	return true;
    }

    // TODO
    public boolean removeFile(String name, String directory) {
    	boolean removed = false;
    	String fileDate = "";
    	ArrayList<FileData> nameList = this.nameMap.get(name);
    	if (nameList == null) {
    		return false;
    	}
    	for (int i = 0; i < nameList.size(); i += 1) {
    		if (nameList.get(i).name.equals(name) && nameList.get(i).dir.equals(directory)) {
    			fileDate = nameList.get(i).lastModifiedDate;
    			nameList.remove(i);
    			removed = true;
    			break;
    		}
    	}
    	if (!removed) {
    		return false;
    	}
    	if (this.nameMap.get(name).size() == 0) {
    		this.nameMap.remove(name);
    	}
    	ArrayList<FileData> dateList = this.dateMap.get(fileDate);
    	if (dateList == null) {
    		return false;
    	}
    	for (int i = 0; i < dateList.size(); i += 1) {
    		if (dateList.get(i).name.equals(name) && dateList.get(i).dir.equals(directory)) {
    			fileDate = dateList.get(i).lastModifiedDate;
    			dateList.remove(i);
    			removed = true;
    			break;
    		}
    	}
    	if (this.nameMap.get(fileDate).size() == 0) {
    		this.dateMap.remove(fileDate);
    	}
    	return removed;
    }

}
