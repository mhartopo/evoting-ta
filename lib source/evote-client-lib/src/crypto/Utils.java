package crypto;

import java.math.BigInteger;

public class Utils {
	public static BigInteger addPailier(BigInteger a, BigInteger b) {
		return a.multiply(b);
	}
	
	public static BigInteger addPailier(BigInteger a, BigInteger b, PublicKey pub) {
		return a.multiply(b).mod(pub.getnSquared());
	}
}
