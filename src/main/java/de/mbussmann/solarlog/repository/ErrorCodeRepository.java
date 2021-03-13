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
