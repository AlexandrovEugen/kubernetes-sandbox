package com.eval.kubernetessandbox.doman.bookmark;

import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {
    public BookmarkDto toDto(Bookmark bookmark) {
        return BookmarkDto.builder()
                .id(bookmark.getId())
                .title(bookmark.getTitle())
                .url(bookmark.getUrl())
                .createdAt(bookmark.getCreatedAt())
                .build();
    }
}
