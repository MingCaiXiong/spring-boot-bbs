package top.xiongmingcai.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.xiongmingcai.bbs.exception.BussinessException;
import top.xiongmingcai.bbs.model.dao.PostMapper;
import top.xiongmingcai.bbs.model.pojo.Post;
import top.xiongmingcai.bbs.model.request.AddPostReq;
import top.xiongmingcai.bbs.model.request.PutPostReq;
import top.xiongmingcai.bbs.service.PostService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service：帖子表
 *
 * @author xmc000
 * @date 2021-05-09 00:05:20
 */
@Service
public class PostServiceImpl implements PostService {


    @Resource
    private PostMapper postMapper;


    @Override
    public Post add(AddPostReq postReq, String username) {
        Post entity = new Post();
        entity.setUsername(username);
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setTitle(postReq.getTitle());
        entity.setContent(postReq.getContent());
        int count = postMapper.insert(entity);
        if (count != 1) {
            throw new BussinessException("p01", "帖子入库失败!");
        }
        return entity;
    }

    @Override
    public List<Post> findPostAll() {
        List<Post> postList = postMapper.selectList(new QueryWrapper<>());
        return postList;
    }

    @Override
    public Post updatePost(Long id, PutPostReq putPostReq) {
        Post entity = postMapper.selectById(id);
        if (entity == null) {
            throw new BussinessException("p02", "没有Post ID " + id + "数据");
        }
        nullAwareBeancopyproperties(putPostReq, entity);

        int count = postMapper.updateById(entity);
        if (count != 1) {
            throw new BussinessException("p04", "帖子入库失败!");
        }
        return entity;
    }

    @Override
    public void deleteOnePost(Long id) {
        Post entity = postMapper.selectById(id);
        if (entity == null) {
            throw new BussinessException("p02", "没有Post ID " + id + "数据");
        }
        int count = postMapper.deleteById(id);
        if (count != 1) {
            throw new BussinessException("p04", "帖子删除失败!");
        }
    }

    private void nullAwareBeancopyproperties(PutPostReq putPostReq, Post entity) {

        if (putPostReq.getTitle() != null) {
            entity.setTitle(putPostReq.getTitle());
        }
        if (putPostReq.getContent() != null) {
            entity.setContent(putPostReq.getContent());
        }

        entity.setUpdateTime(LocalDateTime.now());
    }

}