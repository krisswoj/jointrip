package pl.jointrip.models;

import java.util.List;

public class CommentsWrapper {

    List<Comments> commentsList;

    public CommentsWrapper(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }
}
