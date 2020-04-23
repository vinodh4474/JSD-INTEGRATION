package com.atlassian.TestTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.apache.commons.io.IOUtils;

public class CreateIssue {

	public void createIssue(String summary, String description) throws IOException {

		String createIssueUrl = "https://crgjiranew.atlassian.net/rest/api/2/issue";
		String createIssueJsonInfo = " { \"fields\": {\n        \"project\": {\n            \"key\": \"10001\"\n        },\n        \"summary\": \""
				+ summary
				+ "\",\n        \"issuetype\": {\n            \"id\": \"10002\"\n        },\n        \"assignee\": {\n            \"name\": \"vinodh A\"\n        },\n       \"description\" : \""
				+ description + "\"\n}}";
		Authentication authentication = new Authentication();
		String userName = "vinodh@crgroup.co.in";
		String password="IZux1kJHeUjTvQx0ifIfF5C9";

		HttpURLConnection createIssueConnection = authentication.authenticate(createIssueUrl, userName, password);
		createIssueConnection.setRequestMethod("POST");
		OutputStream outputStream = createIssueConnection.getOutputStream();
		outputStream.write(createIssueJsonInfo.getBytes("UTF-8"));
		// Response Code
		System.out.println(createIssueConnection.getResponseCode());
		System.out.println(createIssueConnection.getResponseMessage());

		InputStream inputStream = new BufferedInputStream(createIssueConnection.getInputStream());
		String jsonResponse = IOUtils.toString(inputStream, "UTF-8");

		System.out.println(jsonResponse);

	}

}