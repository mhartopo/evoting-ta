/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lobe2.tpslaiya.controller;

import com.lobe2.tpslaiya.Tes;
import com.lobe2.tpslaiya.Votingdata;
import com.lobe2.tpslaiya.VotingdataRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author muhtarh
 */
@RestController
public class VoteRestController {
    
    @Autowired
    private VotingdataRepository votingdataRepository;

    @RequestMapping(value="/vote", method=RequestMethod.POST)
    public String vote(@RequestParam(value="uid") String uid, @RequestParam(value="vpId") String vpId, @RequestParam(value="candidate") String candidate,  @RequestParam(value="hash") String hash) {
        System.out.println(votingdataRepository.countByUid(uid));
        if(votingdataRepository.countByUid(uid) > 0) {
            return "already vote";
        } else {
            Votingdata vd = new Votingdata();
            vd.setUid(uid);
            vd.setVpId(vpId);
            vd.setCandidate(candidate);
            vd.setHash(hash);
            votingdataRepository.save(vd);
            return "ok";
        }
        
    }
    
}
