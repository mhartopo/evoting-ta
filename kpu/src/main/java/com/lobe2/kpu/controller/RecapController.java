/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lobe2.kpu.controller;

import collector.IntermediateLevel;
import com.lobe2.kpu.Recapdata;
import com.lobe2.kpu.RecapdataRepository;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import recap.*;

/**
 *
 * @author muhtarh
 */
@Controller
public class RecapController {
    @Autowired
    private RecapdataRepository recapDataRepo;
    
    private IntermediateLevel iLevel;
    private ArrayList<Recapitulation> recapitulations = new ArrayList<>();
    
    @GetMapping(path="/")
    public String index(Model model) {
        return "redirect:rekap-masuk";
    }
    
    @GetMapping(path="/rekap-masuk")
    public String allRecaps(Model model) {
        Iterable<Recapdata> recapdata = recapDataRepo.findAll();
        recapDataRepo.deleteByVpId(99);
        model.addAttribute("recaps", recapdata);
        return "rekap-masuk";
    }    
    
    @GetMapping(path="/rekap-hasil")
    public String recap(Model model) {
        iLevel = new IntermediateLevel(11);
        Iterable<Recapdata> recapdata = recapDataRepo.findAll();
        Recapitulations recaps = new Recapitulations();
        for(Recapdata rd : recapdata) {
            Recapitulation recap = new Recapitulation(new BigInteger(rd.getCandidate()), new BigInteger(rd.getNvote()));
            recaps.addRecap(recap);
        }
        recaps.generateHash();
        iLevel.addRecapitulation(recaps);
        recapitulations = iLevel.getRecapitulations().getRecapArray();
        System.out.println(recapitulations.size());
        model.addAttribute("recaps", recapitulations);
        return "rekap-hasil";
    }
    
    @GetMapping(path="/download_recap")
    public void export(HttpServletResponse response ) throws IOException {
        String myString = "";
        for(Recapitulation rec : recapitulations) {
            myString += rec.getCandidate().toString() + ","+rec.getNumVotes().toString()+"\n";
        }
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition","attachment;filename=rekap-il.txt");
        ServletOutputStream out = response.getOutputStream();
        out.println(myString);
        out.flush();
        out.close();
    }
    
}
