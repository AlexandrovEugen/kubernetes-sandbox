package com.eval.kubernetessandbox.doman.bookmark;


import jakarta.validation.constraints.NotEmpty;

public record CreateBookMarkRequest(
        @NotEmpty(message = "Title should not be empty") String title,
        @NotEmpty(message = "Url should not be empty") String url) {
}
