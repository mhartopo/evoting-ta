/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lobe2.kpu;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author muhtarh
 */
public interface RecapdataRepository extends CrudRepository<Recapdata, Long> {
    List<Recapdata> removeByVpId(int vpId);
    Long countByVpId(int vpId);

    @Modifying
    @Transactional
    @Query(value="delete from Recapdata rd where rd.vpId = ?1")
    void deleteByVpId(int vpId);
}
