package com.eval.kubernetessandbox.doman.bookmark;

import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record BookmarkDto(Long id, String url, String title, Instant createdAt) {
}
