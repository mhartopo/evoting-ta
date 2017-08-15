package collector;

import java.io.Serializable;
import java.math.BigInteger;

public class Vote implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private BigInteger id;
	private BigInteger votingPlaceId;
	private BigInteger candidateVote;
	
	public Vote(BigInteger id, BigInteger votingPlaceId, BigInteger candidateVote) {
		this.setId(id);
		this.setVotingPlaceId(votingPlaceId);
		this.setCandidateVote(candidateVote);
	}
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getVotingPlaceId() {
		return votingPlaceId;
	}

	public void setVotingPlaceId(BigInteger votingPlaceId) {
		this.votingPlaceId = votingPlaceId;
	}

	public BigInteger getCandidateVote() {
		return candidateVote;
	}

	public void setCandidateVote(BigInteger candidateVote) {
		this.candidateVote = candidateVote;
	}	
	
}
