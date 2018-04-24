package main.java.vm.google;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.java.core.constants.BackendConstants;
import main.java.core.persistence.google.GoogleJson;

public class GoogleJsonTranslator {

	String username;
	String json;

	public GoogleJson createFromFile(String username, String fileName) {
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new GoogleJson(username, stringBuilder.toString());
	}

	public void readFromEntity(GoogleJson googleJson) {
		String json = googleJson.getJson();

		//Programmatic way of creating filenames
		String filename = BackendConstants.UPLOADDIRECTORY + "//" //
				+ googleJson.getUsername() + googleJson.getId();

		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw = new BufferedWriter(fw);
		try {
			bw.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
