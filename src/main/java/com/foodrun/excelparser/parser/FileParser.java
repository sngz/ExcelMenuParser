package com.foodrun.excelparser.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public abstract class FileParser {
	private File file;
	private FileInputStream FileIS;

	  public FileParser() {
	    setFile(null);
	  }

	  public FileParser(File file) {
	    setFile(file);
	  }

	  public void setFile(File file) {
	    this.file = file;
	  }

	  public File getFile() throws FileNotFoundException {
	    if (file == null) {
	      throw new FileNotFoundException("Missing file. Pass the file " +
	          "using setFile method before parsing.");
	    }
	    
	    return file;
	  }
	  
	  public void setFileStream(File file) throws FileNotFoundException {
		  if (file == null) {
		      throw new FileNotFoundException("Missing file. Pass the file " +
		          "using setFile method before parsing.");
		  }
		  
		  FileIS = new FileInputStream(file);
	  }
	  
	  public FileInputStream getFileStream() {
		  return FileIS;
	  }

	  public abstract Map<Integer, String> getFieldNames() throws Exception;
	  
	  public abstract List<Map<Integer, String>> getSheetRows() throws Exception;
}
