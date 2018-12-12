package pl.jointrip.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jointrip.models.Documentstore;

@Repository
public interface DocumentsRepository extends CrudRepository<Documentstore, Integer> {
    Documentstore findById(int id);
}
