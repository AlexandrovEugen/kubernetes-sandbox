package com.eval.kubernetessandbox.doman.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(toBuilder = true)
public record BookmarksDto(List<BookmarkDto> data, long totalElements, int totalPages, int currentPage,
                           @JsonProperty("isFirst")
                          boolean isFirst,
                           @JsonProperty("isLast")
                          boolean isLast,
                           boolean hasNext,
                           boolean hasPrevious) {

    public BookmarksDto page(Page<BookmarkDto> page) {
        return this.toBuilder()
                .data(page.getContent())
                .totalElements(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();

    }

}
