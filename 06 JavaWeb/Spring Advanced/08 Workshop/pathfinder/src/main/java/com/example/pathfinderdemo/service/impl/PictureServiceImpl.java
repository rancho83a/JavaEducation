package com.example.pathfinderdemo.service.impl;

import com.example.pathfinderdemo.Repository.PictureRepository;
import com.example.pathfinderdemo.model.entity.PictureEntity;
import com.example.pathfinderdemo.service.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private  final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<String> findAllPicturesUrl() {

        return pictureRepository.findAllByUrl();
    }
}
