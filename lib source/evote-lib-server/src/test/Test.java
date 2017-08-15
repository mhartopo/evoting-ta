package test;

import java.math.BigInteger;

import com.google.gson.Gson;

import collector.IntermediateLevel;
import recap.*;

public class Test {
	public static void main(String[] args) {
		
		//Recapitulation recap = new Recapitulation(new BigInteger("1490824222728902565447563983435356283990817812467561775916142882160560426053745578598308795781491499322363465304220546690214799338570126742617319927859255"), new BigInteger("4391203566528380286072911256893580279829713584892026283834881192137050599247446609780021296491860088650695053021035074968424254261610291259878216127744840"));
		//Recapitulation recap1 = new Recapitulation(new BigInteger("4849170683827168805985339605261211823214165249420052102861048598872547155966305261849900873677874779953795572204055213993660292909423334179893403790885753"), new BigInteger("4977903004920692756826385471255379823423429952322057469668722521221964092991319494144492213119367674953795152951216519826647185649334268637672800991601500"));
		//Recapitulation recap2 = new Recapitulation(new BigInteger("61393870666071186292715871909276014475888212770049844174168310897518270798752360320996295378395635129822892211836468149356826430812478502697335357336901"), new BigInteger("4977903004920692756826385471255379823423429952322057469668722521221964092991319494144492213119367674953795152951216519826647185649334268637672800991601500"));
		Recapitulation recap = new Recapitulation(new BigInteger("1"), new BigInteger("2"));
		Recapitulation recap1 = new Recapitulation(new BigInteger("2"), new BigInteger("1"));
		Recapitulation recap2 = new Recapitulation(new BigInteger("3"), new BigInteger("1"));
		
		Recapitulations recaps = new Recapitulations();
		recaps.addRecap(recap);
		recaps.addRecap(recap1);
		recaps.addRecap(recap2);
		recaps.addRecap(recap);
		recaps.addRecap(recap1);
		recaps.addRecap(recap2);
		recaps.generateHash();
		IntermediateLevel inter = new IntermediateLevel(1);
		inter.addRecapitulation(recaps);
		Recapitulations r = inter.getRecapitulations();
		Gson gson = new Gson();
		String s = gson.toJson(r);
		System.out.println(s);
		Sender sender = new Sender();
		try {
			sender.send("http://localhost:8082/tambah-rekap", "data="+s+"&vpid=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
