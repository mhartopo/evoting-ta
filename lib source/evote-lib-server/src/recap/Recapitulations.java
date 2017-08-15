package recap;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import utils.Serializer;

public class Recapitulations implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7241759989112703788L;
	private ArrayList<Recapitulation> recapArray;
	private byte[] hash;
	
	public Recapitulations() {
		recapArray = new ArrayList<>();
	}
	
	public Recapitulations(ArrayList<Recapitulation> recaps) {
		recapArray = recaps;
	}
	
	public Recapitulations(ArrayList<Recapitulation> recaps, byte[] hash) {
		recapArray = recaps;
		this.hash = hash;
	}
	
	public void addRecap(Recapitulation recap) {
		recapArray.add(recap);
	}
	
	public byte[] getRecapsDigest() throws NoSuchAlgorithmException, IOException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] data = Serializer.serialize(recapArray);
		return digest.digest(data);
	}
	
	public boolean checkHash() {
		try {
			return Arrays.equals(hash, getRecapsDigest());
		} catch (NoSuchAlgorithmException |IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void generateHash() {
		try {
			hash = getRecapsDigest();
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean hasCandidateId(BigInteger id) {
		boolean found = false;
		int i = 0;
		while(!found && i < recapArray.size()) {
			found  = id.equals(recapArray.get(i).getCandidate());
			i++;
		}
		return found;
	}
	
	public void increaseVoteCandidate(BigInteger id) {
		boolean found = false;
		int i = 0;
		while(!found && i < recapArray.size()) {
			found  = id.equals(recapArray.get(i).getCandidate());
			if(found) {
				recapArray.get(i).increaseVotes();
			} else {
				i++;
			}
		}
	}
	
	public void addNumVoteCandidate(BigInteger id, BigInteger n) {
		boolean found = false;
		int i = 0;
		while(!found && i < recapArray.size()) {
			found  = id.equals(recapArray.get(i).getCandidate());
			if(found) {
				recapArray.get(i).addNumVotes(n);
			} else {
				i++;
			}
		}
		if(!found) {
			recapArray.add(new Recapitulation(id, n));
		}
	}
	
	public ArrayList<Recapitulation> getRecapArray() {
		return recapArray;
	}
	public void setRecapArray(ArrayList<Recapitulation> recapArray) {
		this.recapArray = recapArray;
	}
	public byte[] getHash() {
		return hash;
	}
	public void setHash(byte[] hash) {
		this.hash = hash;
	}
}
