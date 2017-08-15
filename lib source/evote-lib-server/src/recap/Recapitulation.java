package recap;

import java.io.Serializable;
import java.math.BigInteger;

import crypto.HomomorphicOperation;

public class Recapitulation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9207325966462096367L;
	private BigInteger candidate;
	private BigInteger numVotes;
	
	public Recapitulation() {
		setCandidate(BigInteger.ZERO);
		setNumVotes(BigInteger.ZERO);
	}
	
	public Recapitulation(BigInteger candidate, BigInteger numVotes) {
		this.setCandidate(candidate);
		this.setNumVotes(numVotes);
	}
	
	public void increaseVotes() {
		numVotes = HomomorphicOperation.add(numVotes, HomomorphicOperation.ONE);
	}
	
	public void addNumVotes(BigInteger n) {
		numVotes = HomomorphicOperation.add(numVotes, n);
	}

	public BigInteger getCandidate() {
		return candidate;
	}

	public void setCandidate(BigInteger candidate) {
		this.candidate = candidate;
	}

	public BigInteger getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(BigInteger numVotes) {
		this.numVotes = numVotes;
	}
}
