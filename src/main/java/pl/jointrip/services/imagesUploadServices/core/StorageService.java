package pl.jointrip.services.imagesUploadServices.core;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.user.User;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    String storeFromTrip(MultipartFile file, Trip trip, User user);

    String generateFileName(Trip trip, User user, String oryginalFileName);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
