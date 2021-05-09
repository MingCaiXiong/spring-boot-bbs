package top.xiongmingcai.bbs.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.xiongmingcai.bbs.common.Constant;
import top.xiongmingcai.bbs.model.pojo.Post;
import top.xiongmingcai.bbs.model.pojo.User;
import top.xiongmingcai.bbs.model.request.AddPostReq;
import top.xiongmingcai.bbs.model.request.PutPostReq;
import top.xiongmingcai.bbs.service.PostService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller：帖子
 *
 * @author xmc000
 * @date 2021-05-09 00:05:20
 */
@RestController
@RequestMapping("/post")
public class PostController extends ApiController {
    @Resource
    private PostService postService;

    /**
     * 获取全部的 帖子 列
     */
    @GetMapping("all")
    public Object listAll(Post entity) {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> result = new HashMap<>();
        List<Post> postList = postService.findPostAll();
        return success(postList);
    }


    /**
     * 添加一个 帖子
     *
     * @param addPostReq
     */
    @PostMapping
    public Object add(@Valid AddPostReq addPostReq, HttpSession session) {
        User user = (User) session.getAttribute(Constant.loginUser);
        postService.add(addPostReq, user.getUsername());
        List<Post> postList = postService.findPostAll();

        return success(postList);
    }

    /**
     * 修改一个 帖子
     *
     * @param id         主键ID
     * @param putPostReq 修改后的信息
     */
    @PutMapping("{id}")
    public Object update(@PathVariable Long id, PutPostReq putPostReq) {
        //无内如变化
        if (putPostReq.getTitle() == null && putPostReq.getContent() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        postService.updatePost(id, putPostReq);

        List<Post> postList = postService.findPostAll();
        return success(postList);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Long id) {

        postService.deleteOnePost(id);

        return success(null);
    }
}
