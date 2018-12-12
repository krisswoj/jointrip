package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.entities.comments.Comments;
import pl.jointrip.models.entities.trip.Trip;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer> {

    Comments findById(int commentId);
    List<Comments> findByTripAndStatusIs(Trip trip, int status);
}
