package crypto;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;
import utils.Prop;

public class Test {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Properties prop = Prop.loadProperties("config.properties");
		
		PrivateKey priv = new PrivateKey(new BigInteger(prop.getProperty("lambda")), new BigInteger(prop.getProperty("denominator")));
		PublicKey pub = new PublicKey(new BigInteger(prop.getProperty("n")), new BigInteger(prop.getProperty("nSquared")), new BigInteger(prop.getProperty("g")), new Integer(prop.getProperty("bits")));
		KeyPair kp = new KeyPair(priv, pub, new BigInteger(prop.getProperty("upperBound")));
		
		BigInteger a = pub.encrypt(new BigInteger("1"));
		BigInteger b = pub.encryptCustR(new BigInteger("1"), new BigInteger(prop.getProperty("r")));
		//String s = Base64.getEncoder().encodeToString(Serializer.serialize(a));
		System.out.println(a);
		System.out.println(b);
		System.out.println(kp.decrypt(a));
		System.out.println(kp.decrypt(b));
		//System.out.println(s);
		//System.out.println(kp.decrypt(new BigInteger("353514319079847035804758882645720615739540717891876915109412462519886956492345625922700362039370377887674736876153125791706085466719122843259085558318466")));
	}
}