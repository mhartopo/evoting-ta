package data;

import java.math.BigInteger;

import recap.Recapitulations;

public interface DataAccessor {
	public void addVote(int candidate);
	public BigInteger getVotes(int candidate);
	public void addRecap(Recapitulations recaps);
	public Recapitulations getRecaps();
}
