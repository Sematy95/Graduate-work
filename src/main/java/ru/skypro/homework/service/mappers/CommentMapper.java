package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Comment;

@Mapper(componentModel = "spring", uses = CommentMapper.class)
public interface CommentMapper {

 @Mapping(target = "author",expression ="java(comment.getAuthor().getId())" )
 @Mapping(target = "authorImage", expression ="java(comment.getAuthor().getImage())" )
 @Mapping(target = "authorFirstName", expression ="java(comment.getAuthor().getFirstName())" )
 CommentDto commentToCommentDto(Comment comment);

 CreateOrUpdateCommentDto commentToCreateOrUpdateCommentDto(Comment comment);









}
