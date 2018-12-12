package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.entities.documents.Documentstore;
import pl.jointrip.models.entities.user.User;

import java.util.List;

@Repository
public interface DocumentsRepository extends CrudRepository<Documentstore, Integer> {
    Documentstore findById(int id);
    List<Documentstore> findAllByUserId(User user);
}
