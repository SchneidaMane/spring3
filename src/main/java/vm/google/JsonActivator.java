package main.java.vm.google;

import java.io.File;
import java.util.ArrayList;

import main.java.core.constants.BackendConstants;
import main.java.core.constants.RegexConstants;
import main.java.core.io.CommandLine;
import main.java.core.persistence.google.GoogleJson;
import main.java.core.persistence.google.GoogleRepository;

public class JsonActivator {

	String fileName;
	CommandLine commandLine;
	GoogleRepository googleRepository;

	public JsonActivator(String fileName, GoogleRepository googleRepository) {
		this.fileName = fileName;
		commandLine = new CommandLine();
		this.googleRepository = googleRepository;
	}

	//TODO: Make sure fileName can't be injected with something weird by hackers
	public boolean activateServiceAccount() {
		boolean success = false;
		String line = "gcloud auth activate-service-account --key-file " + BackendConstants.UPLOADDIRECTORY + "//" + fileName;
		try {
			success = commandLine.setupProcess("C:\\Windows\\system32\\cmd.exe").startThreads() //
					.setLine(line, "Error", RegexConstants.GOOGLESERVICEACCOUNTACTIVATEDSUCCESS, RegexConstants.GOOGLESERVICEACCOUNTACTIVATEDFAILURE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		JsonTranslator translator = new JsonTranslator();
		GoogleJson googleJson = translator.createFromFile("sampleUser", BackendConstants.UPLOADDIRECTORY + "//" + fileName);
		googleRepository.selectDatabase("awesomedatabase");
		googleRepository.save(googleJson);
		ArrayList<GoogleJson> jsons = (ArrayList<GoogleJson>) googleRepository.findAllJson();
		for (GoogleJson json : jsons) {
			System.out.println(json.getJson());
		}
		//File is no longer needed because we have the text in the database
		File file = new File(BackendConstants.UPLOADDIRECTORY + "//" + fileName);
		file.delete();
		return success;
	}
}
