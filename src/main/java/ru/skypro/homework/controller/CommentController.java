package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CommentsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;

import static ru.skypro.homework.security.RoleAuthority.USER;
import static ru.skypro.homework.security.RoleAuthority.ADMIN;

/**
 * This controller provides endpoint for managing comments
 */
@RestController
@RequestMapping("ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Retrieves all comments.
     *
     * @param id The ID of the advertisement.
     * @return ResponseEntity containing a list of comments objects.
     *
     * <pre>{@code
     *
     * HTTP 200 (OK) with the list of comments
     * HTTP 401 (Unauthorized): if authentication fails.
     * HTTP 404 (Not Found): if comments are not found.
     *
     * }</pre>
     */
    @Operation(
            tags = "Комментарии",
            summary = "Получение комментариев объявления",
            parameters = {
                    @Parameter(name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(type = "integer", format = "int32"))
            },
            operationId = "getComments",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found")
            }


    )
    @GetMapping("{id}/comments")
    public ResponseEntity<CommentsDto> getComments(@PathVariable("id") int id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    /**
     * Posts the comment.
     *
     * @param id   The ID of the advertisement.
     * @param text The text of comment.
     * @return ResponseEntity indicating the success of posting.
     *
     * <pre>{@code
     *
     * HTTP 200 (OK): on successful posting.
     * HTTP 401 (Unauthorized): if authentication fails.
     * HTTP 404 (Not Found): if the ad is not found.
     *
     * }</pre>
     */
    @Operation(
            tags = "Комментарии",
            summary = "Добавление комментария к объявлению",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(implementation = CreateOrUpdateCommentDto.class)
                    )
            ),
            parameters = {
                    @Parameter(name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(type = "integer", format = "int32"))
            },
            operationId = "postComment",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found")
            }


    )
    @PostMapping("{id}/comments")
    public ResponseEntity<CreateOrUpdateCommentDto> postComment(@PathVariable("id") int id, @RequestBody CreateOrUpdateCommentDto text) {
        return ResponseEntity.ok(commentService.postComment(id, text.getText()));
    }

    /**
     * Deletes the comment.
     *
     * @param adId      The ID of the advertisement.
     * @param commentId The ID of the comment.
     * @return ResponseEntity indicating the success of deletion.
     *
     * <pre>{@code
     *
     * HTTP 200 (OK): on successful deletion.
     * HTTP 401 (Unauthorized): if authentication fails.
     * HTTP 403 (Forbidden): if the user does not have permission.
     * HTTP 404 (Not Found): if the ad or comment are not found.
     *
     * }</pre>
     */
    @Operation(
            tags = "Комментарии",
            summary = "Удаление комментария",
            parameters = {
                    @Parameter(name = "adId",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(type = "integer", format = "int32")),
                    @Parameter(name = "commentId",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(type = "integer", format = "int32"))
            },
            operationId = "deleteComment",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found")
            }


    )
    @DeleteMapping("{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable("adId") int adId, @PathVariable("commentId") int commentId) {
        commentService.deleteComment(adId, commentId);
    }

    /**
     * Updates the comment.
     *
     * @param adId      The ID of the advertisement.
     * @param commentId The ID of the comment.
     * @param text      The text of the comment.
     *
     * @return ResponseEntity indicating the success of update.
     *
     * <pre>{@code
     *
     * HTTP 200 (OK): on successful update.
     * HTTP 401 (Unauthorized): if authentication fails.
     * HTTP 403 (Forbidden): if the user does not have permission.
     * HTTP 404 (Not Found): if the ad or comment are not found.
     *
     * }</pre>
     */
    @Operation(
            tags = "Комментарии",
            summary = "Обновление комментария",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(implementation = CreateOrUpdateCommentDto.class)
                    )
            ),
            parameters = {
                    @Parameter(name = "adId",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(type = "integer", format = "int32")),
                    @Parameter(name = "commentId",
                            in = ParameterIn.PATH,
                            required = true,
                            schema = @Schema(type = "integer", format = "int32"))
            },
            operationId = "patchComment",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found")
            }


    )
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CreateOrUpdateCommentDto> patchComment(@PathVariable("adId") int adId, @PathVariable("commentId") int commentId, @RequestBody String text) {
        return ResponseEntity.ok(commentService.patchComment(adId, commentId, text));
    }

}
