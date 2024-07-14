package dev.dodo.library.dto.request.publisher;

import dev.dodo.library.entities.Book;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {

    private int id;

    @NotEmpty(message = "Yayınevi adı boş veya null olamaz")
    private String publisherName;

    private String publisherYear;

    private String publisherAdress;

    private int publisherBooksId;
}
