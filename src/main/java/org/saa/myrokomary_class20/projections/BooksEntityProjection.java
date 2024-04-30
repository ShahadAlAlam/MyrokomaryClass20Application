package org.saa.myrokomary_class20.projections;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public interface BooksEntityProjection {

    public Long getBooksId();

    public String getTitle();

    public String getAuthor();
}
