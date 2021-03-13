package de.mbussmann.solarlog.control;

import de.mbussmann.solarlog.boundary.dto.InverterDto;
import java.util.List;

public interface InverterService {
    void createInverter(InverterDto newInverter);
    boolean pruefInverter(Long id);
    void updateInverter(Long id, InverterDto updateInverter);
    List<InverterDto> getInverters(Long systemId);
    InverterDto getInverter(Long inverterId);
}
