package com.eval.kubernetessandbox.api;

import com.eval.kubernetessandbox.doman.bookmark.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDto getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "query", defaultValue = "") String query) {
        if (Strings.isBlank(query)) return bookmarkService.getAll(page);
        return bookmarkService.searchBookmarks(query, page);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkDto createBookMark(@RequestBody @Valid CreateBookMarkRequest bookmark) {
        return bookmarkService.createBookmark(bookmark);
    }

}
