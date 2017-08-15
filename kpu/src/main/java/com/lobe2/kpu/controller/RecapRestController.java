/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lobe2.kpu.controller;

import com.google.gson.Gson;
import com.lobe2.kpu.Recapdata;
import com.lobe2.kpu.RecapdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import recap.Recapitulation;
import recap.Recapitulations;

/**
 *
 * @author muhtarh
 */
@RestController
public class RecapRestController {
    @Autowired
    private RecapdataRepository recapDataRepo;
    
    @RequestMapping(path="/tambah-rekap", method=RequestMethod.POST)
    public String addRecap(@RequestParam(value="data") String data, @RequestParam(value="vpid") int vpid) {
        Gson gs = new Gson();
        Recapitulations recaps = gs.fromJson(data, Recapitulations.class);
        recapDataRepo.deleteByVpId(vpid);
        for(Recapitulation recap : recaps.getRecapArray()) {
            Recapdata recapdata = new Recapdata();
            recapdata.setCandidate(recap.getCandidate().toString());
            recapdata.setNvote(recap.getNumVotes().toString());
            recapdata.setVpId(vpid);
            recapDataRepo.save(recapdata);
        }
        System.out.println("ok");
        return "ok";
    }
    
}
