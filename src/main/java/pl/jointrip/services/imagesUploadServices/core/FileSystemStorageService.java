package pl.jointrip.services.imagesUploadServices.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.jointrip.dao.ImagesStoreRepository;
import pl.jointrip.models.entities.documents.ImagesStore;
import pl.jointrip.models.entities.trip.Trip;
import pl.jointrip.models.entities.user.User;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    ImagesStoreRepository imagesStoreRepository;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String storeFromTrip(MultipartFile file, Trip trip, User user) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(!file.getOriginalFilename().matches("[a-zA-Z-_0-9]+[.][a-z]+$")) {
                throw new StorageException("File format is wrong " + filename);
            }
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                String newFileName = this.generateFileName(trip, user, filename);
                Files.copy(inputStream, this.rootLocation.resolve(newFileName),
                        StandardCopyOption.REPLACE_EXISTING);
                return newFileName;
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public String generateFileName(Trip trip, User user, String oryginalFileName){
        String[] splited = oryginalFileName.split("\\.");
        String generatedString = RandomStringUtils.randomAlphanumeric(15).toLowerCase();
        return String.format("%s_%s_%s.%s", trip.getId(), user.getUserId(), generatedString, splited[1]);
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);

            byte[] image = Files.readAllBytes(file);

            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (IOException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {

        if (Files.notExists(rootLocation)) {
            try {
                Files.createDirectories(rootLocation);
            } catch (IOException e) {
                throw new StorageException("Could not initialize storage", e);
            }
        }
    }
}
