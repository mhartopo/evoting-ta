package collector;

import java.math.BigInteger;
import java.util.ArrayList;

import crypto.HomomorphicOperation;
import recap.Recapitulation;
import recap.Recapitulations;

public class VotingPlace {
	
	private int id;
	private ArrayList<VotewHash> votes;
	private Recapitulations recapitulations;
	
	public VotingPlace() {
		id = 0;
		votes = new ArrayList<VotewHash>();
		recapitulations = new Recapitulations();
	}
	
	public VotingPlace(int id, ArrayList<VotewHash> votes, Recapitulations recapitulations) {
		this.id = id;
		this.votes = votes;
		this.recapitulations = recapitulations;
	}
	
	public VotingPlace(int id, ArrayList<VotewHash> votes) {
		this.id = id;
		this.votes = votes;
	}
	
	public void addVote(VotewHash vote) {
		if(vote.vefifyHash()) {
			if(!isAlreadyVote(vote.getVote().getId())) {
				votes.add(vote);
			} else {
				System.out.println("Already vote");
			}
		} else {
			System.out.println("hash is not verified");
		}
	}
	
	public boolean isAlreadyVote(BigInteger uid) {
		boolean found = false;
		int i = 0;
		while(!found && i < votes.size()) {
			found = votes.get(i).getVote().getId().equals(uid);
			i++;
		}
		return found;
		
	}
	
	public void recapVote() {
		Recapitulations recaps = new Recapitulations();
		for(VotewHash votehash : votes) {
			Vote vote = votehash.getVote();
			if(recaps.hasCandidateId(vote.getCandidateVote())) {
				recaps.increaseVoteCandidate(vote.getCandidateVote());
			} else {
				Recapitulation recap = new Recapitulation(vote.getCandidateVote(), HomomorphicOperation.ONE);
				recaps.addRecap(recap);
			}
		}
		recapitulations = recaps;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<VotewHash> getVotes() {
		return votes;
	}
	
	public void setVotes(ArrayList<VotewHash> votes) {
		this.votes = votes;
	}
	
	public Recapitulations getRecapitulations() {
		return recapitulations;
	}
	
	public void setRecapitulations(Recapitulations recapitulations) {
		this.recapitulations = recapitulations;
	}
}
