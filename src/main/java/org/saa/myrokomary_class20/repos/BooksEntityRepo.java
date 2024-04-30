package org.saa.myrokomary_class20.repos;

import jakarta.transaction.Transactional;
import org.saa.myrokomary_class20.dto.Books;
import org.saa.myrokomary_class20.entity.BooksEntity;
import org.saa.myrokomary_class20.projections.BooksEntityProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksEntityRepo extends JpaRepository<BooksEntity,Long> {

    @Query(value=" Select coalesce(Max(books_id),0)+1  from books ",nativeQuery = true)
    public Long findMaxId();

    public List<BooksEntity> findAllByOrderByBooksIdAsc();
    public List<BooksEntity> findAllByOrderByBooksIdAsc(Pageable pageable);
    @Query(value="select books_id, title, author from books ",nativeQuery = true)
    public List<BooksEntityProjection> findAllSpecific(Pageable pageable);


}
