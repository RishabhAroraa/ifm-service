package com.imageflowmeta.service.image;

import com.imageflowmeta.service.imagetag.ImageTag;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table
public class Image {

    @Id
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )
    private @Getter @Setter Long imageId;

    private @Getter @Setter Long authorId;
    private @Getter @Setter String imageTitle;
    private @Getter @Setter LocalDate dateOfCreation;

    // likes
    private @Getter @Setter Long claps;

    private @Getter @Setter byte[] image;
    private @Getter @Setter boolean isPrivate;

    @ManyToMany
    private @Getter @Setter Set<ImageTag> tags;
}
