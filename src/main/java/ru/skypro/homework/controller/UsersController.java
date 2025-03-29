package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.GetUserDto;
import ru.skypro.homework.dto.user.SetPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.service.UsersService;


import java.io.IOException;

import static ru.skypro.homework.security.RoleAuthority.USER;

/**
 * This controller provides endpoints for user's operations.
 */

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи")


public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * The endpoint for password updating
     *
     * @param setPassword User's DTO for the password updating
     *                    HTTP 200 (OK): password updating successful
     *                    HTTP 401 (Unauthorized): if authentication fails.
     *                    HTTP 403 (Forbidden): password updating declined
     */
    @Operation(
            tags = "Пользователи",
            summary = "Обновление пароля",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SetPasswordDto.class)
                    )
            ),
            operationId = "setPassword",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ok"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden")

            }
    )
    @PostMapping("/users/set_password")
    public void setPassword(@RequestBody SetPasswordDto setPassword) {
        usersService.setPassword(setPassword);
    }

    /**
     * Getting information about the authorized user
     *
     * @return ResponseEntity containing the created GetUserDto.
     * HTTP 200 (OK): with user's info
     * HTTP 400 (Unauthorized): if user is not authorized
     */
    @Operation(
            tags = "Пользователи",
            summary = "Получение информации об авторизованном пользователе",
            operationId = "getUser",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }

    )
    @GetMapping("/users/me")
    public ResponseEntity<GetUserDto> getUser() {
        return ResponseEntity.ok(usersService.getAuthorizedUserInfo());
    }

    /**
     * Updating information about the authorized user
     *
     * @param updateUserDto user's DTO for updating authorized user's info
     * @return ResponseEntity containing the updating status
     * HTTP 200 (OK): updating successful
     * HTTP 400 (Unauthorized): if user is not authorized
     */
    @PatchMapping("/users/me")
    @Operation(
            tags = "Пользователи",
            summary = "Обновление информации об авторизованном пользователе",
            operationId = "updateUser",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdateUserDto.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateUserDto.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized")
            }
    )
    public UserDto updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return usersService.updateUserInfo(updateUserDto);
    }

    /**
     * Updating user's avatar
     *
     * @param file new avatar
     *             HTTP 200 (OK): updating successful
     *             HTTP 400 (Unauthorized): if user is not authorized
     */
    @Operation(
            tags = "Пользователи",
            summary = "Обновление аватара авторизованного пользователя",
            operationId = "updateUserImage",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized")
            }
    )
    @PatchMapping(value = "/users/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam("image") MultipartFile file) {
        usersService.updateUserImage(file);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/users/images/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte[] getImage(@PathVariable("id") int id) throws IOException {
        return usersService.getUserImage(id);
    }
}