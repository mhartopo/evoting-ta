package crypto;

import java.math.BigInteger;
import java.util.Properties;
import java.util.Random;

import utils.Prop;

public class PublicKey {
    private final int bits;
    private final BigInteger n;
    private final BigInteger nSquared;
    private final BigInteger g;
    public PublicKey(BigInteger n, BigInteger nSquared, BigInteger g, int bits) {
        this.n = n;
        this.nSquared = nSquared;
        this.bits = bits;
        this.g = g;
    }
    
    public PublicKey(String config) {
    	Properties prop = Prop.loadProperties("config.properties");
		n = new BigInteger(prop.getProperty("n"));
		nSquared = new BigInteger(prop.getProperty("nSquared"));
		g = new BigInteger(prop.getProperty("g"));
		bits = new Integer(prop.getProperty("bits"));
		
    }
    
    public String toString() {
    	String res = "";
    	res += n.toString() + "\n";
    	res += nSquared.toString() + "\n";
    	res += g.toString();
    	return res;
    }

    public int getBits() {
        return bits;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getnSquared() {
        return nSquared;
    }

    public BigInteger getG() {
        return g;
    }


    public final BigInteger encrypt(BigInteger m) {

        BigInteger r;
        do {
            r = new BigInteger(bits, new Random());
            
        } while (r.compareTo(n) >= 0);
        BigInteger result = g.modPow(m, nSquared);
        BigInteger x = r.modPow(n, nSquared);

        result = result.multiply(x);
        result = result.mod(nSquared);

        return result;
    }
    
    public final BigInteger encryptCustR(BigInteger m, BigInteger r) {

        BigInteger result = g.modPow(m, nSquared);
        BigInteger x = r.modPow(n, nSquared);

        result = result.multiply(x);
        result = result.mod(nSquared);

        return result;
    }
}
