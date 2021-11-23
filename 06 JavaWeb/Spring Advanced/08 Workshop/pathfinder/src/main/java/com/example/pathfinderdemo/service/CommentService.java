package com.example.pathfinderdemo.service;


import com.example.pathfinderdemo.model.service.CommentServiceModel;
import com.example.pathfinderdemo.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    CommentViewModel createComment(CommentServiceModel commentServiceModel);
    List<CommentViewModel> getComments(Long routeId);
}
