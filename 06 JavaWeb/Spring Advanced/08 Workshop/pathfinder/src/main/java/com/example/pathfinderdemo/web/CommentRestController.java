package com.example.pathfinderdemo.web;

import com.example.pathfinderdemo.model.binding.NewCommentBindingModel;
import com.example.pathfinderdemo.model.service.CommentServiceModel;
import com.example.pathfinderdemo.model.validation.ApiError;
import com.example.pathfinderdemo.model.view.CommentViewModel;
import com.example.pathfinderdemo.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/{routeId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(
            @PathVariable Long routeId,
            Principal principal
    ) {

        return ResponseEntity.ok(this.commentService.getComments(routeId));
    }

    @PostMapping("/api/{routeId}/comments")
    public ResponseEntity<CommentViewModel> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long routeId,
            @RequestBody @Valid NewCommentBindingModel newCommentBindingModel
    ) {

        CommentServiceModel commentServiceModel = modelMapper.map(newCommentBindingModel, CommentServiceModel.class);

        commentServiceModel.setRouteId(routeId);

        CommentViewModel commentViewModel = this.commentService.createComment(commentServiceModel);

        URI locationOfNewComment = URI.create(String.format("/api/%s/comments/%s",routeId, commentViewModel.getCommentId()));

        return ResponseEntity.
                created(locationOfNewComment)
                .body(commentViewModel);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        ex.getFieldErrors().forEach(fe ->
                apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }

}
