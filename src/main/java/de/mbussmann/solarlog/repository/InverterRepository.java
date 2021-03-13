package de.mbussmann.solarlog.repository;

import de.mbussmann.solarlog.boundary.dto.InverterDto;
import de.mbussmann.solarlog.control.InverterService;
import de.mbussmann.solarlog.entity.Inverter;
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
public class InverterRepository implements InverterService {

    @Inject
    EntityManager em;

    @Inject
    EntityConverter entityConverter;

    /**
     * Create {@link Inverter}
     * @param newInverter new {@link InverterDto} to be add to Database
     * @return Response.Status for the Ressource
     */
    @Override
    @Transactional
    public void createInverter(InverterDto newInverter) {
        Inverter inverter = new Inverter(newInverter);
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
     * @return List of {@link InverterDto}
     */
    @Override
    public List<InverterDto> getInverters(Long systemId) {
        return entityConverter.inverterEntityListDto(em.createQuery("SELECT i FROM inverter i WHERE i.sytem = :id", Inverter.class)
            .setParameter("id", systemId)
            .getResultList());

    }

    /**
     * Get one {@link Inverter}
     * @param inverterId {@link Inverter} Id
     * @return {@link InverterDto} Object
     */
    @Override
    public InverterDto getInverter(Long inverterId) {
        Inverter inverter = em.find(Inverter.class,inverterId);
        if(inverter != null) {
            return entityConverter.inverterEntityDto(inverter);
        }
        return null;
    }
}