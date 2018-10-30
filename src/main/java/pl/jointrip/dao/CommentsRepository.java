package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.Comments;
import pl.jointrip.models.Trip;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer> {

    Comments findById(int commentId);
    List<Comments> findByTripAndStatusIs(Trip trip, int status);
}
