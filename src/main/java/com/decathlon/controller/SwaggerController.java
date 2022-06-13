package com.decathlon.controller;

import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@Api(
    hidden = true
)
public class SwaggerController {
    private final AtomicReference<String> swaggerContent = new AtomicReference();

    public SwaggerController() {
    }

    @GetMapping
    public String getSwagger() {
        return Optional.ofNullable(this.swaggerContent.get()).orElseGet(() -> {
            try {
                this.swaggerContent.set(IOUtils.toString(this.getClass().getResourceAsStream("/swagger/swagger.json"), StandardCharsets.UTF_8));
                return this.swaggerContent.get();
            } catch (IOException var2) {
                throw new RuntimeException(var2);
            }
        });
    }
}