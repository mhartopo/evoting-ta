package app.demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import vote.Sender;
import vote.VotewHash;

public class SenderDemo implements Sender{
	private VotewHash data;
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public void setData(VotewHash data) {
		this.data = data;
	}
	@Override
	public void send(String url) throws Exception{
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String id = data.getVote().getId().toString();
		String vpId = data.getVote().getVotingPlaceId().toString();
		String candidate = data.getVote().getCandidateVote().toString();
		String hash = Base64.getUrlEncoder().encodeToString(data.getHash());
		String urlParameters = "uid="+id+"&vpId="+vpId+"&candidate="+candidate+"&hash="+hash;
		System.out.println("Send data : "+urlParameters);
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

	}

}
