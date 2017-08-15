package crypto;

import java.math.BigInteger;

/**
 * A class that represents the private part of the Paillier key pair.
 */
public class PrivateKey {

    private final BigInteger lambda;
    private final BigInteger preCalculatedDenominator;

    public PrivateKey(BigInteger lambda, BigInteger preCalculatedDenominator) {
        this.lambda = lambda;

        this.preCalculatedDenominator = preCalculatedDenominator;
    }
    
    public String toString() {
    	String res = "";
    	res += lambda.toString() + "\n";
    	res += preCalculatedDenominator.toString();
    	return res;
    }

    public BigInteger getLambda() {
        return lambda;
    }

    public BigInteger getPreCalculatedDenominator() {
        return preCalculatedDenominator;
    }
}
