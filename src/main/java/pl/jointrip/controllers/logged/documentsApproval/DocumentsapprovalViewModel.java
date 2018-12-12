package pl.jointrip.controllers.logged.documentsApproval;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DocumentsApprovalViewModel {
    private MultipartFile file;
    private Integer documentKind;
}
