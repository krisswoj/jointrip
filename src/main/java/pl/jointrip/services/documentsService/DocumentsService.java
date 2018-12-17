package pl.jointrip.services.documentsService;

import pl.jointrip.models.entities.documents.Documentstore;
import pl.jointrip.models.entities.user.User;
import pl.jointrip.models.viewModels.documents.DocumentsApprovalViewModel;

public interface DocumentsService {
    boolean saveDocument(DocumentsApprovalViewModel viewModel);

    DocumentsApprovalViewModel findUserDocuments(User user);

    Documentstore findById(int id);
}
