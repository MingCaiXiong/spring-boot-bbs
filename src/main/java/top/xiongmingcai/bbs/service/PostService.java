package top.xiongmingcai.bbs.service;

import top.xiongmingcai.bbs.model.pojo.Post;
import top.xiongmingcai.bbs.model.request.PostReq;

import java.util.List;

/**
 * Service：帖子
 *
 * @author xmc000
 * @date 2021-05-09 00:05:20
 */
public interface PostService {


    Post add(PostReq postReq, String username);

    List<Post> findPostAll();


    Post updatePost(Long id, PostReq putPostReq);

    void deleteOnePost(Long id);
}