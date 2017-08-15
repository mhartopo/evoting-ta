package vote;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import utils.Serializer;

public class VotewHash implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vote vote;
	private byte[] hash;
	
	public VotewHash(Vote vote, byte[] hash) {
		this.vote = vote;
		this.hash = hash;
	}
	
	public VotewHash(Vote vote) {
		this.vote = vote;
	}
	
	private byte[] voteDigest() throws IOException, NoSuchAlgorithmException {
		byte[] bytes = Serializer.serialize(vote);
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return digest.digest(bytes);
	}
	
	public void generateHash() {
		try {
			hash = voteDigest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean vefifyHash() {
		try {
			byte[] hashVote = voteDigest();
			return Arrays.equals(hashVote, hash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public Vote getVote() {
		return vote;
	}
	
	public byte[] getHash() {
		return hash;
	}
}
