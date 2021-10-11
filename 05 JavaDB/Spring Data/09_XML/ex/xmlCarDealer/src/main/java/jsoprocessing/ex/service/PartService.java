package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.PartSeedDto;
import jsoprocessing.ex.model.entity.Part;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PartService {

    long getProductCount();

    void seedParts(List<PartSeedDto> parts);

    Set<Part> getRandomPartSet();

}
