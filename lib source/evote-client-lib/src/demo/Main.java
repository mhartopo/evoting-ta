package demo;

import java.math.BigInteger;
import java.util.Properties;
import java.util.Scanner;

import crypto.*;
import utils.Prop;
import vote.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("NAMA KANDIDAT");
			System.out.println("1. Apel");
			System.out.println("2. Mangga");
			System.out.println("3. Jeruk");
			System.out.print("Nomor id : ");
			String id = sc.nextLine();
			System.out.print("Kandidat yang dipilih : ");
			String candidate = sc.nextLine();
			if(Integer.parseInt(candidate) > 3 || Integer.parseInt(candidate) < 1) {
				System.out.println("Input nomor kandidat yang benar !");
			} else {
				sendVote(generateVote(id, candidate));
			}
		}
	}
	
	private static VotewHash generateVote(String id, String cand) {
		BigInteger vpId = new BigInteger("1");
		Properties prop = Prop.loadProperties("config.properties");
		PublicKey pub = new PublicKey("config.properties");
		BigInteger idCrypt = pub.encrypt(new BigInteger(id));
		BigInteger candCrypt = pub.encryptCustR(new BigInteger(cand), new BigInteger(prop.getProperty("r")));
		//BigInteger idCrypt = new BigInteger(id);
		//BigInteger candCrypt = new BigInteger(cand);
		System.out.println(idCrypt + ", "+candCrypt);
		Vote vote = new Vote(idCrypt, vpId, candCrypt);
		VotewHash vhash = new VotewHash(vote);
		vhash.generateHash();
		
		return vhash;
	}
	
	private static void sendVote(VotewHash vhash) {
		SenderDemo sd = new SenderDemo();
		sd.setData(vhash);
		try {
			sd.send("http://localhost:8081/vote");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
