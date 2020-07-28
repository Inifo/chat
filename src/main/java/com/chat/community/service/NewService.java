package com.chat.community.service;

import com.chat.community.dao.NewMapper;
import com.chat.community.entity.New;
import com.chat.community.dao.DiscussPostMapper;
import com.chat.community.entity.DiscussPost;
import com.chat.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class NewService {

    @Autowired
    private NewMapper newMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    public List<DiscussPost> findNews(int userId, int offset, int limit) {
        return discussPostMapper.selectNews(userId, offset, limit);
    }

    public List<New> findNewsByMode(int userId, int offset, int limit, int mode) {
        return newMapper.selectNews(userId, offset, limit);
    }




    public List<New> findNewsByTime(int userId, int offset, int limit) {
        return newMapper.selectNewsByTime(userId, offset, limit);
    }

    public List<New> findNewsBySports(int userId, int offset, int limit) {
        return newMapper.selectNewsBySports(userId, offset, limit);
    }
    public List<New> findNewsByEntertainment(int userId, int offset, int limit) {
        return newMapper.selectNewsByEntertainment(userId, offset, limit);
    }


    public int findNewRows(int userId) {
        return newMapper.selectNewRows(userId);
    }

    public int findDiscussReplyRows(int userId){ return newMapper.selectNewReplyRows(userId);}

    public int addDiscussPost(DiscussPost post) {
        if (post == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 转义HTML标记
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));
        // 过滤敏感词
        post.setTitle(sensitiveFilter.filter(post.getTitle()));
        post.setContent(sensitiveFilter.filter(post.getContent()));
        // 空格过滤

        return newMapper.insertDiscussPost(post);
    }

    public New findNewById(int id) {
        return newMapper.selectNewById(id);
    }

    public DiscussPost findDiscussPostByEntityId(int entityId) {
        return newMapper.selectDiscussPostByEntityId(entityId);
    }

    public int updateCommentCount(int id, int commentCount){
        return newMapper.updateCommentCount(id, commentCount);
    }

    public String findDiscussPostTitleByEntityId(int entityId){
        return newMapper.selectDiscussPostTitleByEntityId(entityId);
    }

    public int updateType(int id, int type) {
        return newMapper.updateType(id, type);
    }

    public int updateStatus(int id, int status) {
        return newMapper.updateStatus(id, status);
    }

}
