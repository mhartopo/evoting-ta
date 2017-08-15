package crypto;

import java.math.BigInteger;

import utils.ConfigReader;

public class HomomorphicOperation {
	
	public static BigInteger ONE = new BigInteger(ConfigReader.read("hm_one"));
	public static BigInteger ZERO = new BigInteger(ConfigReader.read("hm_zero"));
	public static BigInteger nSquared = new BigInteger(ConfigReader.read("nSquared"));
	
	public static BigInteger add(BigInteger a , BigInteger b) {
		return a.multiply(b).mod(nSquared);
	}
	
	public static BigInteger multiply(BigInteger number, int factor) {
		BigInteger result = number;
		for(int i = 0; i < factor-1; i++) {
			result = result.multiply(number).mod(nSquared);
		}
		return result;
	}
}
