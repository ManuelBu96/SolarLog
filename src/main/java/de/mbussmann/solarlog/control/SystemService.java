package de.mbussmann.solarlog.control;

import de.mbussmann.solarlog.boundary.dto.SystemDto;

import java.util.List;

public interface SystemService {
    void createSystem(SystemDto newSystem);
    boolean pruefSystem(Long id);
    void updateSystem(Long id, SystemDto updateSystem);
    List<SystemDto> getSystems();
    SystemDto getSystem(Long id);
    void removeSystem(Long id);
}
