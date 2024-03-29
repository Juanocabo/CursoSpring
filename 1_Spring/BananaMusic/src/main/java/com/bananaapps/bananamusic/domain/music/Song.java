package com.bananaapps.bananamusic.domain.music;

import lombok.*;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Tune")
@NamedQuery(name = "Song.findByArtist",
        query = "Select s " +
                "FROM Song s " +
                " WHERE s.artist = :artist")
public class Song {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title, artist;
    private LocalDate releaseDate;
    @Column(name = "cost")
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private SongCategory songCategory;
    @Version
    private int version;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Backlog> backlogRecords = new ArrayList<Backlog>();

    public void addBacklogRecord(String location, int quantity) {
        Backlog iv = new Backlog(location, quantity);

        getBacklogRecords().add(iv);
        iv.setItem(this);
    }

    @Transient
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Song(Long id) {
        setId(id);
    }

    public Song(String title, String artist, String releaseDate, BigDecimal price, SongCategory musicCategory) {
        this.setTitle(title);
        this.setArtist(artist);
        this.setReleaseDateAsString(releaseDate);
        this.setPrice(price);
        this.setSongCategory(musicCategory);
    }

    public Song(Long id, String title, String artist, String releaseDate, BigDecimal price, SongCategory musicCategory) {
        this.setId(id);
        this.setTitle(title);
        this.setArtist(artist);
        this.setReleaseDateAsString(releaseDate);
        this.setPrice(price);
        this.setSongCategory(musicCategory);
    }

    public void setReleaseDateAsString(String releaseDateString) {
        releaseDate = LocalDate.parse(releaseDateString, formatter);
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o == this) {
            result = true;
        } else if (o instanceof Song) {
            Song other = (Song) o;
            result = Objects.equals(this.getTitle(), other.getTitle()) &&
                    Objects.equals(this.getArtist(), other.getArtist()) &&
                    Objects.equals(this.getReleaseDate(), other.getReleaseDate());
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.artist, this.releaseDate);
    }

}
