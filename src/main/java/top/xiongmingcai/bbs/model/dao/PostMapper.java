package top.xiongmingcai.bbs.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.xiongmingcai.bbs.model.pojo.Post;

/**
 * 存储库：帖子表
 *
 * @author xmc000
 * @date 2021-05-09 00:05:20
 */
@Repository
public interface PostMapper extends BaseMapper<Post> {
    int createPost(Post entity);
}