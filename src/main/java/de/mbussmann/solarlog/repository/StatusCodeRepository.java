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

import de.mbussmann.solarlog.boundary.dto.StatusCodeDto;
import de.mbussmann.solarlog.control.StatusCodeService;
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
