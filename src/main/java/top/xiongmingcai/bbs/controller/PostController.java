package top.xiongmingcai.bbs.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import org.springframework.validation.annotation.Validated;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Controller：帖子
 *
 * @author xmc000
 * @date 2021-05-09 00:05:20
 */
@Validated
@RestController
@RequestMapping("/post")
public class PostController extends ApiController {
    @Resource
    private PostService postService;

    /**
     * 获取全部的 帖子 列
     */
    @GetMapping("all")
    public Object listAll() {

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
    public Object update(@PathVariable @Min(value = 0, message = "不能小于0") Long id, @Valid @RequestBody(required = false) PutPostReq putPostReq) {

        postService.updatePost(id, putPostReq);

        List<Post> postList = postService.findPostAll();
        return success(postList);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable @Max(value = 1000000, message = "不能大于于1000000") Long id) {

        postService.deleteOnePost(id);

        return success(null);
    }
}
