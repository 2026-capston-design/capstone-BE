package backend.capstone.domain.bookmarkplace.repository;

import backend.capstone.domain.bookmarkplace.entity.BookmarkPlace;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkPlaceRepository extends JpaRepository<BookmarkPlace, Long> {

    @Query("""
        select bp
        from BookmarkPlace bp
        where bp.user.id = :userId
        order by bp.id asc
        """)
    List<BookmarkPlace> findByUserIdOrderByIdAsc(@Param("userId") Long userId);
}
