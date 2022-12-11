package com.eval.kubernetessandbox.doman.bookmark;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@AllArgsConstructor
public class BookmarkService {

    private static final String CREATED_AT = "createdAt";
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper mapper;

    @Transactional(readOnly = true)
    public BookmarksDto getAll(Integer page) {
        val pageable = getPageRequest(page);
        return BookmarksDto.builder().build().page(bookmarkRepository.findBookmarks(pageable));
    }

    private Pageable getPageRequest(Integer page) {
        val pageNo = page < 1 ? 0 : page - 1;
        return PageRequest.of(pageNo, 10, Sort.Direction.DESC, CREATED_AT);
    }


    @Transactional(readOnly = true)
    public BookmarksDto searchBookmarks(String query, Integer page) {
        val pageRequest = getPageRequest(page);
        return BookmarksDto.builder().build().page(bookmarkRepository.findByTitleContainsIgnoreCase(query, pageRequest));
    }

    public BookmarkDto createBookmark(CreateBookMarkRequest bookmarkCreateRequest) {
        val bookmark = Bookmark.builder()
                .url(bookmarkCreateRequest.url())
                .title(bookmarkCreateRequest.title())
                .createdAt(Instant.now())
                .build();
        return mapper.toDto(bookmarkRepository.save(bookmark));
    }
}
