package de.mbussmann.solarlog.repository;

import de.mbussmann.solarlog.boundary.dto.SystemDto;
import de.mbussmann.solarlog.control.SystemService;
import de.mbussmann.solarlog.entity.System;
import de.mbussmann.solarlog.util.EntityConverter;

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
     * @return List of {@link SystemDto}
     */
    @Override
    public List<SystemDto> getSystems() {
        return entityConverter.systemEntityListtoDto(em.createQuery("SELECT s FROM system s", System.class).getResultList());
    }

    /**
     * Get one {@link System}
     * @param id {@link System} Id
     * @return {@link SystemDto} Object
     */
    @Override
    public SystemDto getSystem(Long id) {
        System system = em.find(System.class, id);
        if(system != null) {
            return entityConverter.systemEntitytoDto(system);
        }
        return null;
    }

    /**
     * Remove {@link System}
     * @param id {@link System} Id
     */
    @Override
    public void removeSystem(Long id) {
        System system = em.find(System.class, id);
        if(system != null) {
            //Add Inverter
            em.remove(system);
        }
    }
}
