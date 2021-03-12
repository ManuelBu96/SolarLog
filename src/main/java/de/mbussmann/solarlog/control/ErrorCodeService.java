package de.mbussmann.solarlog.control;

import de.mbussmann.solarlog.boundary.dto.ErrorCodeDto;
import de.mbussmann.solarlog.entity.ErrorCode;
import java.util.List;

/**
 * @author Manuel Bußmann
 */
public interface ErrorCodeService {
    void createErrorCode(ErrorCodeDto newErrorCode);
    ErrorCodeDto getErrorCode(Long errorCode);
    List<ErrorCodeDto> getErrorCode(String inverterTyp);
    ErrorCode getEntityById(Long id);
}