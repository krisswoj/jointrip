package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.Comments;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer> {
}
