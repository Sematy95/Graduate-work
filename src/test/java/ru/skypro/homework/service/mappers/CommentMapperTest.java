package ru.skypro.homework.service.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.comment.CommentDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentMapperTest {
    private CommentMapper mapper = Mappers.getMapper(CommentMapper.class);
    Comment comment = new Comment(new User(1,"123456","Oleg","Smagin","+79215600890","email@mail.ru", Role.USER,"112"),"t",1, new Ad());

    @Test
    void commentToCommentDto() {
        CommentDto commentDto = mapper.commentToCommentDto(comment);
        System.out.println("commentDto = " + commentDto);
        assertEquals(comment.getAuthor().getId(), commentDto.getAuthor());
        assertEquals(comment.getAuthor().getImage(), commentDto.getAuthorImage());
        assertEquals(comment.getAuthor().getFirstName(), commentDto.getAuthorFirstName());
        assertEquals(comment.getCreatedAt(), commentDto.getCreatedAt());
        assertEquals(comment.getPk(), commentDto.getPk());
        assertEquals(comment.getText(), commentDto.getText());

    }

    @Test
    void commentToCreateOrUpdateCommentDto() {

        CreateOrUpdateCommentDto commentDto = mapper.commentToCreateOrUpdateCommentDto(comment);
        System.out.println("commentDto = " + commentDto);
        assertEquals(comment.getText(), commentDto.getText());

    }
}