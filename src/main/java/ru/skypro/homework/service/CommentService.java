package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;

import java.util.List;

public interface CommentService {

    CommentsDto getComments(int id);

    CreateOrUpdateCommentDto postComment(int id, String text);

    void deleteComment(int adId, int commentId);

    CreateOrUpdateCommentDto patchComment(int adId, int commentId, String text);

}
