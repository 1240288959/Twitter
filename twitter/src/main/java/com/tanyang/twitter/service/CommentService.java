package com.tanyang.twitter.service;


import com.tanyang.twitter.pojo.Comment;
import com.tanyang.twitter.pojo.Twitter;
import com.tanyang.twitter.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment);

    List<Comment> getCommentByTwitterId(Twitter twitter);
}
