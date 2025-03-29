package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.user.RegisterDto;
import ru.skypro.homework.service.AuthService;

/**
 * This controller provides endpoint for user's registration
 */
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Регистрация")

public class RegistrationController {

    private final AuthService authService;
    public RegistrationController(AuthService authService) {
        this.authService = authService;
    }
    /**
     * User's registration
     * @param registerDto User's DTO for registration
     * @return ResponseEntity containing the registration status
     * HTTP 200 (OK): User was created.
     * HTTP 400 (Unauthorized): if registration fails.
     *
     */
    @Operation(
            tags = "Регистрация",
            summary = "Регистрация пользователя",
            operationId = "register",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RegisterDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    )

            })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (authService.register(registerDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
