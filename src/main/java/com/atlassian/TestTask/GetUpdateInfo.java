package com.atlassian.TestTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class GetUpdateInfo {

	public void getUpdateInfo(String getUpdateJsonInfo) throws IOException {

		String getUpdateUrl = "http://localhost:8080/rest/api/2/search";
		Authentication authentication = new Authentication();
		String userName = "Jira_User";
		String password="arun";

		CreateIssue create = new CreateIssue();
		HttpURLConnection getUpdateConnection = authentication.authenticate(getUpdateUrl, userName,  password);
		getUpdateConnection.setRequestMethod("POST");
		OutputStream outputStream = getUpdateConnection.getOutputStream();
		outputStream.write(getUpdateJsonInfo.getBytes("UTF-8"));
		// Response Code
		System.out.println(getUpdateConnection.getResponseCode());
		System.out.println(getUpdateConnection.getResponseMessage());

		InputStream inputStream = new BufferedInputStream(getUpdateConnection.getInputStream());
		String jsonResponse = IOUtils.toString(inputStream, "UTF-8");

		System.out.println(jsonResponse);

		JSONObject issueDetails = new JSONObject(jsonResponse);

		System.out.println("---------------------------------------------------------------");
		System.out.println(jsonResponse);
		for (int i = 0; i < (issueDetails.getInt("total")); i++) {
			System.out.println(issueDetails.getJSONArray("issues").getJSONObject(i).get("key"));
			System.out.println(issueDetails.getJSONArray("issues").getJSONObject(i).getJSONObject("fields")
					.get("summary").toString());
			System.out.println(issueDetails.getJSONArray("issues").getJSONObject(i).getJSONObject("fields")
					.get("assignee").toString());
			if ((issueDetails.getJSONArray("issues").getJSONObject(i).getJSONObject("fields").get("summary")
					.toString()) != null
					|| (issueDetails.getJSONArray("issues").getJSONObject(i).get("key").toString() != null)) {
				create.createIssue(
						issueDetails.getJSONArray("issues").getJSONObject(i).getJSONObject("fields").get("summary")
								.toString(),
						issueDetails.getJSONArray("issues").getJSONObject(i).get("key").toString());
			}
		}
	}

}