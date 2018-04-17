package main.java.fileupload;

import org.springframework.boot.context.properties.ConfigurationProperties;

import main.java.core.constants.BackendConstants;

@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	private String location = BackendConstants.UPLOADDIRECTORY;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
