package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;
import java.util.Scanner;

import crypto.KeyPair;
import crypto.PrivateKey;
import crypto.PublicKey;
import utils.Prop;

public class Decrypt {
	public static KeyPair mykp;
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Properties prop = Prop.loadProperties("config.properties");
		
		PrivateKey priv = new PrivateKey(new BigInteger(prop.getProperty("lambda")), new BigInteger(prop.getProperty("denominator")));
		PublicKey pub = new PublicKey(new BigInteger(prop.getProperty("n")), new BigInteger(prop.getProperty("nSquared")), new BigInteger(prop.getProperty("g")), new Integer(prop.getProperty("bits")));
		mykp = new KeyPair(priv, pub, new BigInteger(prop.getProperty("upperBound")));
		Scanner sc = new Scanner(System.in);
		System.out.print("Nama file : ");
		String file = sc.nextLine();
		String res = readFile(file);
		sc.close();
		writeText("hasil.txt", res);
	}
	
	private static String readFile(String FileName) {
		BufferedReader br = null;
		String line = "";
		String res = "";
		try {
			br = new BufferedReader(new FileReader(FileName));
			while ((line = br.readLine()) != null) {
			    // use comma as separator
				if(line.length() > 1) {
					String[] s = line.split(",");
					res += mykp.decrypt(new BigInteger(s[0])).toString() + "," + mykp.decrypt(new BigInteger(s[1])).toString()+ "\n";
				}
			}
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(res);
		return res;
		
	}
	
	public static void writeText(String name, String content) {
		try {
			File file = new File(name);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
