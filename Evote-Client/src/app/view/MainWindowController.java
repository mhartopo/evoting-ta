package app.view;

import java.math.BigInteger;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import crypto.PublicKey;
import app.demo.SenderDemo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import utils.Prop;
import vote.Vote;
import vote.VotewHash;

public class MainWindowController implements Initializable{
	
	@FXML
	TextField idTf;
	@FXML
	RadioButton cand1rb;
	@FXML
	RadioButton cand2rb;
	@FXML
	RadioButton cand3rb;
	
	private Properties prop;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		prop = Prop.loadProperties("config.properties");
		reInit();
	}
	
	public void reInit() {
		idTf.setText("");
		cand1rb.setSelected(false);
		cand2rb.setSelected(false);
		cand3rb.setSelected(false);
	}
	
	public void vote() {
		String id = idTf.getText();
		int candidate = getCandVote();
		System.out.println("id = " + id + ", candidate = " + candidate);
		if(!id.isEmpty() && candidate != -1) {
			sendVote(generateVote(id, Integer.toString(candidate)), prop.getProperty("parent_url"));
		}
		reInit();
	}
	
	private int getCandVote() {
		if(cand1rb.isSelected()) {
			return 1;
		} else if(cand2rb.isSelected()) {
			return 2;
		} else if(cand3rb.isSelected()) {
			return 3;
		} else {
			return -1;
		}
	}
	
	
	private VotewHash generateVote(String id, String cand) {
		BigInteger vpId = new BigInteger(prop.getProperty("vp_id"));
		PublicKey pub = new PublicKey("config.properties");
		BigInteger idCrypt = pub.encryptCustR(new BigInteger(id), new BigInteger(prop.getProperty("r")));
		BigInteger candCrypt = pub.encryptCustR(new BigInteger(cand), new BigInteger(prop.getProperty("r")));
		Vote vote = new Vote(idCrypt, vpId, candCrypt);
		VotewHash vhash = new VotewHash(vote);
		vhash.generateHash();
		
		return vhash;
	}
	
	private void sendVote(VotewHash vhash, String url) {
		SenderDemo sd = new SenderDemo();
		sd.setData(vhash);
		try {
			sd.send(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
