package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.user.LoginDto;
import ru.skypro.homework.service.AuthService;

/**
 * This controller provides endpoint for user's authorization.
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Авторизация")
@RestController

public class AuthorizationController {
    private final AuthService authService;

    public AuthorizationController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * User's authorization
     * @param loginDto User's DTO for authorization
     * @return ResponseEntity containing the authorization status
     * HTTP 200 (OK): authorization successful.
     * HTTP 401 (Unauthorized): if authorization fails.
     *
     */
    @Operation(
            summary = "Авторизация пользователя",
            tags = "Авторизация",
            operationId = "login",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LoginDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        if(authService.login(loginDto)) return ResponseEntity.ok().build();
         else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
