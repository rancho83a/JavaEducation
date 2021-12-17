package com.example.pathfinderdemo.service.impl;

import com.example.pathfinderdemo.Repository.RouteRepository;
import com.example.pathfinderdemo.model.entity.CommentEntity;
import com.example.pathfinderdemo.model.service.CommentServiceModel;
import com.example.pathfinderdemo.model.view.CommentViewModel;
import com.example.pathfinderdemo.service.CommentService;
import com.example.pathfinderdemo.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {
//TODO
        throw new UnsupportedOperationException("Not YET");
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long routeId) {


        var routeOpt = this.routeRepository.findById(routeId)
                .orElseThrow(() -> new ObjectNotFoundException("Route with id " + routeId + " was not found"));


        return routeOpt.getComments()
                .stream()
                .map(this::mapCommentEntityToCommentViewModel)
                .collect(Collectors.toList());
    }

    private CommentViewModel mapCommentEntityToCommentViewModel(CommentEntity comment) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.setCommentId(comment.getId())
                .setCanApprove(true)
                .setCreated(comment.getCreated())
                .setCanDelete(true)
                .setMessage(comment.getTextContent())
                .setUser(comment.getAuthor().getFullName());

        return commentViewModel;
    }
}
