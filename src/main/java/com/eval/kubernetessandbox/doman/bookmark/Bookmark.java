package com.eval.kubernetessandbox.doman.bookmark;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "bookmarks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter(value = AccessLevel.NONE)
public final class Bookmark {

    @Id
    @SequenceGenerator(name = "bm_id_seq_gen", sequenceName = "bm_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bm_id_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private Instant createdAt;

}
