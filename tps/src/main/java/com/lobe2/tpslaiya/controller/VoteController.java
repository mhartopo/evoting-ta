/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lobe2.tpslaiya.controller;

import com.lobe2.tpslaiya.Votingdata;
import com.lobe2.tpslaiya.VotingdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author muhtarh
 */
@Controller
public class VoteController {
    @Autowired
    private VotingdataRepository votingdataRepository;
    
    @GetMapping(path="/semua-vote")
    public String getAllVotes(Model model) {
        Iterable<Votingdata> data = votingdataRepository.findAll();
        model.addAttribute("votes", data);
        return "allvote";
    }
    
    @GetMapping(path="/")
    public String index(Model model) {
        return "redirect:semua-vote";
    }
}
