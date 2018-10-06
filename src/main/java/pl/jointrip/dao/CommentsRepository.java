package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import pl.jointrip.models.Comments;

public interface CommentsRepository extends CrudRepository<Comments, Integer> {
}
