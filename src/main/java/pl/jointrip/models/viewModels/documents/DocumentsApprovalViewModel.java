package pl.jointrip.models.viewModels.documents;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import pl.jointrip.models.entities.documents.Documentstore;
import pl.jointrip.models.entities.user.User;

import java.util.List;

@Data
public class DocumentsApprovalViewModel {
    private MultipartFile file;
    private Integer documentKind;
    private User loggedUser;
    private String fileName;
    private String contentType;
    private List<Documentstore> documentstoreList;
}
