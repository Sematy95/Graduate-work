package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.mappers.CommentMapper;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, AdRepository adRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentsDto getComments(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            List<CommentDto> comments = commentRepository.findAllByAdPk(id).orElseThrow(() -> new CommentNotFoundException(id)).stream()
                    .map(commentMapper::commentToCommentDto)
                    .toList();

            return new CommentsDto(
                    comments.size(),
                    comments
            );
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }


    @Override
    public CreateOrUpdateCommentDto postComment(int id, String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Comment comment = new Comment(
                    userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserNotFoundException(authentication.getName())),
                    text,
                    new Date().getTime(),
                    adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id))
            );
            return commentMapper.commentToCreateOrUpdateCommentDto(commentRepository.save(comment));
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Transactional
    @Override
    public void deleteComment(int adId, int commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean permission = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserNotFoundException(authentication.getName())).getId()
                == commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId)).getAuthor().getId();

        if ((authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN")) || permission) &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            commentRepository.deleteByAdPkAndPk(adId, commentId);
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public CreateOrUpdateCommentDto patchComment(int adId, int commentId, String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Comment comment = commentRepository.findByAdPkAndPk(adId, commentId).orElseThrow(() -> new CommentNotFoundException(commentId));
            comment.setText(text);
            return commentMapper.commentToCreateOrUpdateCommentDto(commentRepository.save(comment));
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
