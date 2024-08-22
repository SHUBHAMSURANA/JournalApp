package net.engineeringdigest.journalApp.entity;

import lombok.*;
import net.engineeringdigest.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@NoArgsConstructor

@Data
// if we Use @data annoation then no need for below things
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Builder
//@EqualsAndHashCode


public class JournalEntry {

    @Id
    private ObjectId id;
    // Data Type for mongo db id
    private LocalDateTime date;

    @Indexed(unique = true)
    @NonNull
    private String title;
    //@NonNull
    private String content;
    private Sentiment sentiment;

    // use of lambok
    // it aim to reduce the boiler plate code such as getter ,setter and constructor and more
    // during the compilation it will genearte the code

}
