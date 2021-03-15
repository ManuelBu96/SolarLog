/*
 *
 * Copyright 2021 - Manuel Bußmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package de.mbussmann.solarlog.repository;

import de.mbussmann.solarlog.boundary.dto.InverterDto;
import de.mbussmann.solarlog.boundary.dto.InverterRespDto;
import de.mbussmann.solarlog.control.InverterService;
import de.mbussmann.solarlog.control.SystemService;
import de.mbussmann.solarlog.entity.Inverter;
import de.mbussmann.solarlog.entity.System;
import de.mbussmann.solarlog.util.EntityConverter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Manuel Bußmann
 */
@ApplicationScoped
public class InverterRepository implements InverterService {

    @Inject
    EntityManager em;

    @Inject
    EntityConverter entityConverter;

    /**
     * Boolean if Logging is active
     */
    @ConfigProperty(name = "de.mbussmann.solarlog.logging")
    boolean allowLogging;

    /**
     * Create {@link Inverter}
     * @param newInverter new {@link InverterDto} to be add to Database
     * @return Response.Status for the Ressource
     */
    @Override
    @Transactional
    public void createInverter(InverterDto newInverter) {
        System system = em.find(System.class,newInverter.getSystemId());
        Inverter inverter = new Inverter(system, newInverter);
        em.persist(inverter);
    }

    /**
     * Check if {@link de.mbussmann.solarlog.entity.Inverter} exist
     * @param id SystemId
     * @return Status if System exist
     */
    @Override
    public boolean pruefInverter(Long id) {
        Inverter inverter = em.find(Inverter.class, id);
        return inverter != null;
    }

    /**
     * Update {@link Inverter}
     * @param id {@link Inverter} Id
     * @param updateInverter Updated {@link InverterDto} Object
     */
    @Override
    @Transactional
    public void updateInverter(Long id, InverterDto updateInverter) {
        Inverter inverter = em.find(Inverter.class, id);
        if(inverter != null) {
            inverter.setName(updateInverter.getName());
            inverter.setStrings(updateInverter.getStrings());
            inverter.setPeak(updateInverter.getPeak());
            inverter.setOrientation(updateInverter.getOrientation());
        }
    }

    /**
     * Get all {@link Inverter}
     * @return List of {@link InverterRespDto}
     */
    @Override
    public List<InverterRespDto> getInverters(Long systemId) {
        return entityConverter.inverterEntityListRespDto(em.createQuery("SELECT i FROM inverter i WHERE i.sytem = :id", Inverter.class)
            .setParameter("id", systemId)
            .getResultList());

    }

    /**
     * Get one {@link Inverter}
     * @param id {@link Inverter} Id
     * @return {@link InverterRespDto} Object
     */
    @Override
    public InverterRespDto getInverter(Long id) {
        Inverter inverter = em.find(Inverter.class,id);
        if(inverter != null) {
            return entityConverter.inverterEntityRespDto(inverter);
        }
        return null;
    }

    /**
     * Remove {@link System}
     * @param id {@link System} Id
     */
    @Override
    @Transactional
    public boolean removeInverter(Long id) {
        Inverter inverter = em.find(Inverter.class,id);
        if(inverter != null) {
            //Add Inverter
            em.remove(inverter);
            return true;
        }
        return false;
    }
}