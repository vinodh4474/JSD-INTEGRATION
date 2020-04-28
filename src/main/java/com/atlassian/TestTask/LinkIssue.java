package com.atlassian.TestTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import javax.sound.midi.MidiDevice.Info;

import org.apache.commons.io.IOUtils;

public class LinkIssue {


	public void linkIssue(String summary, String inwardIssueKey, String serverIssueKey) throws IOException {
		// TODO Auto-generated method stub
		String linkIssueUrl = "https://crgjiranew.atlassian.net/rest/api/2/issue/"+inwardIssueKey+"/remotelink";
		System.out.println(linkIssueUrl);
		//String linkIssueUrl = "https://crgjiranew.atlassian.net/rest/api/2/issue/TTS-51/remotelink";
		String linkIssueJsonInfo = "{\n    \"globalId\": \"system=http://localhost:8080/browse/"+serverIssueKey+"?id=1\",\n     \"object\": {\n        \"url\": \"http://localhost:8080/browse/"+serverIssueKey+"id=1\",\n        \"title\": \""+summary+"\",\n        \"summary\": \""+summary+"\",\n        \"icon\": {\n            \"url16x16\": \"https://p7.hiclipart.com/preview/335/521/74/jira-technology-roadmap-computer-software-workflow-bitbucket-others.jpg\",\n            \"title\": \"Support Ticket\"\n        }\n    }\n}";
		Authentication authentication = new Authentication();
		String userName = "vinodh@crgroup.co.in";
		String password="IZux1kJHeUjTvQx0ifIfF5C9";
	
		
		HttpURLConnection linkIssueConnection = authentication.authenticate(linkIssueUrl, userName, password);
		
		linkIssueConnection.setRequestMethod("POST");
		
		OutputStream outputStream = linkIssueConnection.getOutputStream();
		outputStream.write(linkIssueJsonInfo.getBytes("UTF-8"));
		// Response Code
		System.out.println(linkIssueConnection.getResponseCode());
		System.out.println(linkIssueConnection.getResponseMessage());
		

		/*InputStream inputStream = new BufferedInputStream(linkIssueConnection.getInputStream()); //  /rest/api/2/issue/{issueIdOrKey}/remotelink
		String jsonResponse = IOUtils.toString(inputStream, "UTF-8");
		System.out.println("executing................");
		System.out.println(jsonResponse);*/
		
	}

}
