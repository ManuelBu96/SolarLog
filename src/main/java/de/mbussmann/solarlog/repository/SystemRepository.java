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

import de.mbussmann.solarlog.boundary.dto.SystemDto;
import de.mbussmann.solarlog.boundary.dto.SystemRespDto;
import de.mbussmann.solarlog.control.SystemService;
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
public class SystemRepository implements SystemService {

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
     * Create {@link System}
     * @param newSystem new {@link SystemDto} to be add to Database
     * @return Response.Status for the Ressource
     */
    @Override
    @Transactional
    public void createSystem(SystemDto newSystem) {
        System system = new System(newSystem);
        em.persist(system);
    }

    /**
     * Check if {@link System} exist
     * @param id SystemId
     * @return Status if System exist
     */
    @Override
    public boolean pruefSystem(Long id) {
        System system = em.find(System.class, id);
        return system != null;
    }

    /**
     * Update {@link System}
     * @param id {@link System} Id
     * @param updateSystem Updated {@link SystemDto} Object
     */
    @Override
    @Transactional
    public void updateSystem(Long id, SystemDto updateSystem) {
        System system = em.find(System.class, id);
        if(system != null) {
            system.setName(updateSystem.getName());
            system.setFolder(updateSystem.getFolder());
            system.setStartedAt(updateSystem.getStartedAt());
            system.setSerial(updateSystem.getSerial());
            system.setInverters(updateSystem.getInverters());
            system.setCompensation(updateSystem.getCompensation());
            em.merge(system);
        }
    }

    /**
     * Get all {@link System}
     * @return List of {@link SystemRespDto}
     */
    @Override
    public List<SystemRespDto> getSystems() {
        return entityConverter.systemEntityListtoRespDto(em.createQuery("SELECT s FROM system s", System.class).getResultList());
    }

    /**
     * Get one {@link System}
     * @param id {@link System} Id
     * @return {@link SystemRespDto} Object
     */
    @Override
    public SystemRespDto getSystem(Long id) {
        System system = em.find(System.class, id);
        if(system != null) {
            return entityConverter.systemEntitytoRespDto(system);
        }
        return null;
    }

    /**
     * Remove {@link System}
     * @param id {@link System} Id
     */
    @Override
    @Transactional
    public boolean removeSystem(Long id) {
        System system = em.find(System.class, id);
        if(system != null) {
            //Add Inverter
            em.remove(system);
            return true;
        }
        return false;
    }
}
