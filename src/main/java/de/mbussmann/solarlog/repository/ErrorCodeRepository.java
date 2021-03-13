package de.mbussmann.solarlog.repository;

import de.mbussmann.solarlog.boundary.dto.ErrorCodeDto;
import de.mbussmann.solarlog.control.ErrorCodeService;
import de.mbussmann.solarlog.entity.ErrorCode;
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
public class ErrorCodeRepository implements ErrorCodeService {

    @Inject
    EntityManager em;

    @Inject
    EntityConverter entityConverter;

    @Override
    @Transactional
    public void createErrorCode(ErrorCodeDto newErrorCode) {
        ErrorCode errorCode = new ErrorCode(newErrorCode);
        em.persist(errorCode);
    }

    @Override
    public ErrorCodeDto getErrorCode(Long code) {
        ErrorCode errorCode = em.find(ErrorCode.class,code);
        if(errorCode != null) {
            return entityConverter.errorCodeEntitytoDto(errorCode);
        }
        return null;
    }

    @Override
    public List<ErrorCodeDto> getErrorCode(String inverterTyp) {
        return entityConverter.errorCodeEntityListtoDto(em.createQuery("SELECT ec FROM errorCode ec WHERE ec.inverter_typ = :typ", ErrorCode.class)
                .setParameter("typ", inverterTyp)
                .getResultList());
    }
}
