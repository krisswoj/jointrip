package pl.jointrip.services.documentsService;

import org.springframework.web.multipart.MultipartFile;
import pl.jointrip.models.User;

public interface DocumentsService {
    boolean saveDocument(byte[] content, User loggedUser, String fileName, String contentType, Integer documentKind);
    byte[] handleUploadFile(MultipartFile file);
}
