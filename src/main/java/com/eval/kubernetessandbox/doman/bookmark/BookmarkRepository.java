package com.eval.kubernetessandbox.doman.bookmark;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {


    @Query("select new com.eval.kubernetessandbox.doman.bookmark.BookmarkDto(b.id, b.url, b.title, b.createdAt) from Bookmark b")
    Page<BookmarkDto> findBookmarks(Pageable pageable);


}
