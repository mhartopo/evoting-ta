/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lobe2.tpslaiya.controller;

import collector.Vote;
import collector.VotewHash;
import collector.VotingPlace;
import com.google.gson.Gson;
import com.lobe2.tpslaiya.SenderDemo;
import com.lobe2.tpslaiya.Votingdata;
import com.lobe2.tpslaiya.VotingdataRepository;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import recap.Recapitulation;
import recap.Recapitulations;
import utils.Prop;

/**
 *
 * @author muhtarh
 */

@Controller
public class RecapController {
    @Autowired
    private VotingdataRepository votingdataRepository;
    private ArrayList<Recapitulation> recapitulations = new ArrayList<>();
    private Recapitulations recaps;
   @GetMapping(path="/rekapitulasi")
    public String recap(Model model) {
        ArrayList<VotewHash> varr = new ArrayList<>();
        for(Votingdata vdata : votingdataRepository.findAll()) {
            Vote vote = new Vote(new BigInteger(vdata.getUid()), new BigInteger(vdata.getVpId()), new BigInteger(vdata.getCandidate()));
            VotewHash vhash = new VotewHash(vote, Base64.getUrlDecoder().decode(vdata.getHash()));
            varr.add(vhash);
        }
        VotingPlace vplace = new VotingPlace(1, varr);
        vplace.recapVote();
        recaps = vplace.getRecapitulations();
        recapitulations = recaps.getRecapArray();
        model.addAttribute("recaps", recaps.getRecapArray());
        return "recap";
    }
    
    @GetMapping(path="/download_recap")
    public void export(HttpServletResponse response ) throws IOException {
        String myString = "";
        for(Recapitulation rec : recapitulations) {
            myString += rec.getCandidate().toString() + ","+rec.getNumVotes().toString()+"\n";
        }
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition","attachment;filename=rekap.txt");
        ServletOutputStream out = response.getOutputStream();
        out.println(myString);
        out.flush();
        out.close();
    }
    
    @GetMapping(path="/kirim-rekap")
    public String sendRecap(Model model) {
        SenderDemo sd = new SenderDemo();
        Properties prop = Prop.loadProperties("config.properties");
        String url = prop.getProperty("parent_url");
        String vpid = prop.getProperty("vp_id");
        Gson gson = new Gson();
        recaps.generateHash();
        String data = gson.toJson(recaps);
        String param = "data="+data+"&vpid="+vpid;
        try {
            String message = sd.send(url, param);
            model.addAttribute("message", message);
        } catch (Exception ex) {
            Logger.getLogger(RecapController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:rekapitulasi";
    }
}
