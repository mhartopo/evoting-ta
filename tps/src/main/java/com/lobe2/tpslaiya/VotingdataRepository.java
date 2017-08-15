/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lobe2.tpslaiya;

import org.springframework.data.repository.CrudRepository;


    /**
     *
     */
public interface VotingdataRepository extends CrudRepository<Votingdata, Long> {
    Long countByUid(String uid);
}
