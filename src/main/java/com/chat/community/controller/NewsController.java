package com.chat.community.controller;


import com.chat.community.entity.*;
import com.chat.community.event.EventProducer;
import com.chat.community.service.DiscussPostService;
import com.chat.community.service.LikeService;
import com.chat.community.util.CommunityConstant;
import com.chat.community.util.CommunityUtil;
import com.chat.community.util.HostHolder;
import com.chat.community.util.RedisKeyUtil;
import com.chat.community.entity.*;
import com.chat.community.service.NewService;
import com.chat.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class NewsController implements CommunityConstant {


    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private LikeService likeService;

    @Autowired
    private NewService newService;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(path = "/news", method = RequestMethod.GET)
    public String getNews(Model model, Page page) {
        page.setRows(discussPostService.findNewsRows(0));
        List<DiscussPost> list = newService.findNews(
                0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST,post.getId());
                map.put("likeCount",likeCount);
                discussPosts.add(map);
            }
        }


        model.addAttribute("News", discussPosts);

        return "/news";
    }


    @RequestMapping(path = "/newMode", method = RequestMethod.GET)
    public String getIndexModePage(Model model, Page page,
                                   @RequestParam(name = "Mode") int Mode) {
        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(discussPostService.findNewsRowsByMode(Mode));
        page.setPath("/newMode?Mode=" + Mode);
        List<DiscussPost> list = discussPostService.findNewsByMode(
                0, page.getOffset(), page.getLimit(),Mode);
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST,post.getId());
                map.put("likeCount",likeCount);
                discussPosts.add(map);
            }
        }
        model.addAttribute("News", discussPosts);
        model.addAttribute("Mode", Mode);

        return "/news";
    }
//
//    @RequestMapping(path = "/newsTime", method = RequestMethod.GET)
//    public String getNewsByTime(Model model, Page page) {
//        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
//        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
//        page.setRows(newService.findNewRows(0));
//
//        List<New> list = newService.findNewsByTime(
//                0, page.getOffset(), page.getLimit());
//        List<Map<String, Object>> News = new ArrayList<>();
//        if (list != null) {
//            for (New news : list) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("post", news);
//                User user = userService.findUserById(news.getUserId());
//                map.put("user", user);
//
//                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST,news.getId());
//                map.put("likeCount",likeCount);
//
//                News.add(map);
//            }
//        }
//        model.addAttribute("News", News);
//        return "/news";
//    }
//
//
//    @RequestMapping(path = "/newsSports", method = RequestMethod.GET)
//    public String getNewsBySports(Model model, Page page) {
//        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
//        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
//        page.setRows(newService.findNewRows(0));
//
//        List<New> list = newService.findNewsBySports(
//                0, page.getOffset(), page.getLimit());
//        List<Map<String, Object>> News = new ArrayList<>();
//        if (list != null) {
//            for (New news : list) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("post", news);
//                User user = userService.findUserById(news.getUserId());
//                map.put("user", user);
//
//                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST,news.getId());
//                map.put("likeCount",likeCount);
//
//                News.add(map);
//            }
//        }
//        model.addAttribute("News", News);
//        return "/news";
//    }
//
//
//    @RequestMapping(path = "/newsEntertainment", method = RequestMethod.GET)
//    public String getNewsByMode(Model model, Page page) {
//        // 方法调用前,SpringMVC会自动实例化Model和Page,并将Page注入Model.
//        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
//        page.setRows(newService.findNewRows(0));
//
//        List<New> list = newService.findNewsByEntertainment(
//                0, page.getOffset(), page.getLimit());
//        List<Map<String, Object>> News = new ArrayList<>();
//        if (list != null) {
//            for (New news : list) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("post", news);
//                User user = userService.findUserById(news.getUserId());
//                map.put("user", user);
//
//                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST,news.getId());
//                map.put("likeCount",likeCount);
//
//                News.add(map);
//            }
//        }
//        model.addAttribute("News", News);
//        return "/news";
//    }


    @RequestMapping(path = "/new/{NewId}", method = RequestMethod.GET)
    public String getNew(@PathVariable("NewId") int NewId, Model model) {
        // 帖子
        New aNew = newService.findNewById(NewId);
        model.addAttribute("post", aNew);
        // 作者
        User user = userService.findUserById(aNew.getUserId());
        model.addAttribute("user", user);



        return "/site/new-detail";
    }

    @RequestMapping(path = "/new/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content,int select) {
        User user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJSONString(403, "你还没有登录哦!");
        }

        //int status = 3;
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setMode(select);
        post.setStatus(3);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

        // 触发发贴事件
        Event event = new Event()
                .setTopic(TOPIC_PUBLISH)
                .setUserId(user.getId())
                .setEntityType(ENTITY_TYPE_POST)
                .setEntityId(post.getId());
        eventProducer.fireEvent(event);

        //计算帖子分数
        String redisKey = RedisKeyUtil.getPostScoreKey();
        redisTemplate.opsForSet().add(redisKey, post.getId());

        // 报错的情况,将来统一处理.
        return CommunityUtil.getJSONString(0, "发布成功!");
    }


}
