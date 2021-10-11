package jsoprocessing.ex.service;

import jsoprocessing.ex.model.entity.Part;

import java.io.IOException;
import java.util.Set;

public interface PartService {
    void seedParts() throws IOException;

    Set<Part> getRandomPartSet();

}
