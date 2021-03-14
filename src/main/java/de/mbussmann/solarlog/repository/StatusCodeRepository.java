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
import de.mbussmann.solarlog.boundary.dto.StatusCodeRespDto;
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

    /**
     * Create {@link StatusCode}
     * @param newStatusCode new {@link StatusCodeDto} to be add to Database
     * @return Response.Status for the Ressource
     */
    @Override
    @Transactional
    public void createStatusCode(StatusCodeDto newStatusCode) {
        StatusCode statusCode = new StatusCode(newStatusCode);
        em.persist(statusCode);
    }

    /**
     * Get one {@link StatusCode}
     * @param code {@link StatusCode} Id
     * @return {@link StatusCodeRespDto} Object
     */
    @Override
    public StatusCodeRespDto getStatusCode(Long code) {
        StatusCode statusCode = em.find(StatusCode.class,code);
        if(statusCode != null) {
            return entityConverter.statusCodeEntitytoRespDto(statusCode);
        }
        return null;
    }

    /**
     * Get all {@link StatusCode}
     * @return List of {@link StatusCodeRespDto}
     */
    @Override
    public List<StatusCodeRespDto> getStatusCode(String inverterTyp) {
        return entityConverter.statusCodeEntityListtoRespDto(em.createQuery("SELECT sc FROM statusCode sc WHERE sc.inverter_typ = :typ", StatusCode.class)
                .setParameter("typ", inverterTyp)
                .getResultList());
    }
}
