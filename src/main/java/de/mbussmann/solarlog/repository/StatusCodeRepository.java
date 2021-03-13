package de.mbussmann.solarlog.repository;

import de.mbussmann.solarlog.boundary.dto.StatusCodeDto;
import de.mbussmann.solarlog.control.StatusCodeService;
import de.mbussmann.solarlog.entity.Inverter;
import de.mbussmann.solarlog.entity.StatusCode;
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
public class StatusCodeRepository implements StatusCodeService {

    @Inject
    EntityManager em;

    @Inject
    EntityConverter entityConverter;

    @Override
    @Transactional
    public void createStatusCode(StatusCodeDto newStatusCode) {
        StatusCode statusCode = new StatusCode(newStatusCode);
        em.persist(statusCode);
    }

    @Override
    public StatusCodeDto getStatusCode(Long code) {
        StatusCode statusCode = em.find(StatusCode.class,code);
        if(statusCode != null) {
            return entityConverter.statusCodeEntitytoDto(statusCode);
        }
        return null;
    }

    @Override
    public List<StatusCodeDto> getStatusCode(String inverterTyp) {
        return entityConverter.statusCodeEntityListtoDto(em.createQuery("SELECT sc FROM statusCode sc WHERE sc.inverter_typ = :typ", StatusCode.class)
                .setParameter("typ", inverterTyp)
                .getResultList());
    }
}
