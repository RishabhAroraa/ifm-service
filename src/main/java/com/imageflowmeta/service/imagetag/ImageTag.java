package com.imageflowmeta.service.imagetag;

import com.imageflowmeta.service.image.Image;
import com.imageflowmeta.service.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class ImageTag {
    @Id
    @SequenceGenerator(
            name = "tag_sequence",
            sequenceName = "tag_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tag_sequence"
    )
    private @Getter @Setter Long id;

    private @Getter @Setter String tag;

    @ManyToMany
    private @Getter @Setter Set<Image> images;

}
