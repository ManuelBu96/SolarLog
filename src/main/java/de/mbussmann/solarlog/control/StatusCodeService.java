package de.mbussmann.solarlog.control;

import de.mbussmann.solarlog.boundary.dto.StatusCodeDto;
import de.mbussmann.solarlog.entity.StatusCode;

import java.util.List;

/**
 * @author Manuel Bußmann
 */
public interface StatusCodeService {
    void createStatusCode(StatusCodeDto newStatusCode);
    StatusCodeDto getStatusCode(Long code);
    List<StatusCodeDto> getStatusCode(String inverterTyp);
}
