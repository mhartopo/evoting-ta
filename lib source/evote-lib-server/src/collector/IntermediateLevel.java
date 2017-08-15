package collector;

import java.util.ArrayList;

import recap.Recapitulation;
import recap.Recapitulations;

public class IntermediateLevel {
	private int levelId;
	private Recapitulations recapitulations;
	
	public IntermediateLevel(int levelId) {
		this.levelId = levelId;
		this.recapitulations = new Recapitulations();
	}
	
	public IntermediateLevel(int levelId, Recapitulations recapitulations) {
		this.levelId = levelId;
		this.recapitulations = recapitulations;
	}
	
	public void addRecapitulation(Recapitulations recaps) {
		if(recaps.checkHash()) {
			ArrayList<Recapitulation> recapArr = recaps.getRecapArray();
			for(Recapitulation recap : recapArr) {
				recapitulations.addNumVoteCandidate(recap.getCandidate(), recap.getNumVotes());
			}
		}
		
		updateHash();
	}
	
	public void updateHash() {
		recapitulations.generateHash();
	}
	
	public Recapitulations getRecapitulations() {
		return recapitulations;
	}
	public void setRecapitulations(Recapitulations recapitulations) {
		this.recapitulations = recapitulations;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
}
