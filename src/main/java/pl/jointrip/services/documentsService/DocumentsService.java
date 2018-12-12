package pl.jointrip.services.documentsService;

import pl.jointrip.models.viewModels.documents.DocumentsApprovalViewModel;

public interface DocumentsService {
    boolean saveDocument(DocumentsApprovalViewModel viewModel);
}
