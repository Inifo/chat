package com.chat.community.service;

import com.chat.community.dao.DiscussPostMapper;
import com.chat.community.entity.DiscussPost;
import com.chat.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class DiscussPost1Service {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit,int orderMode) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit,orderMode);
    }

    public List<DiscussPost> findDiscussPostsByMode(int userId, int offset, int limit,int mode) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit, mode);
    }

    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }

    public int findDiscussReplyRows(int userId){ return discussPostMapper.selectDiscussReplyRows(userId);}

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

        return discussPostMapper.insertDiscussPost(post);
    }

    public DiscussPost findDiscussPostById(int id) {
        return discussPostMapper.selectDiscussPostById(id);
    }

    public DiscussPost findDiscussPostByEntityId(int entityId) {
        return discussPostMapper.selectDiscussPostByEntityId(entityId);
    }

    public int updateCommentCount(int id, int commentCount){
        return discussPostMapper.updateCommentCount(id, commentCount);
    }

    public String findDiscussPostTitleByEntityId(int entityId){
        return discussPostMapper.selectDiscussPostTitleByEntityId(entityId);
    }

    public int updateType(int id, int type) {
        return discussPostMapper.updateType(id, type);
    }

    public int updateStatus(int id, int status) {
        return discussPostMapper.updateStatus(id, status);
    }

    public int updateScore(int id, double score) {
        return discussPostMapper.updateScore(id, score);
    }
}
