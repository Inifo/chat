package com.chat.community.dao;

import com.chat.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit,int orderMode);

    List<DiscussPost> selectDiscussPostsByMode(int userId, int offset, int limit,int mode);

    List<DiscussPost> selectNews(int userId, int offset, int limit);

    List<DiscussPost> selectNewsByMode(int userId, int offset, int limit,int mode);

    int insertNew(DiscussPost discussPost);

    int selectNewsRows(@Param("userId") int userId);

    int selectNewsRowsByMode(int mode);

    List<DiscussPost> selectDiscussPostsByTime(int userId, int offset, int limit);
    List<DiscussPost> selectDiscussPostsByEntertainment(int userId, int offset, int limit);
    List<DiscussPost> selectDiscussPostsBySport(int userId, int offset, int limit);
    List<DiscussPost> selectDiscussPostsByEmotion(int userId, int offset, int limit);
    List<DiscussPost> selectDiscussPostsByDigit(int userId, int offset, int limit);
    List<DiscussPost> selectDiscussPostsByVideo(int userId, int offset, int limit);
    List<DiscussPost> selectDiscussPostsByElectronic(int userId, int offset, int limit);

    // @Param注解用于给参数取别名,
    // 如果只有一个参数,并且在<if>里使用,则必须加别名.
    int selectDiscussPostRows(@Param("userId") int userId);

    int selectDiscussPostRowsByMode(int mode);








    int selectDiscussReplyRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);

    DiscussPost selectDiscussPostByEntityId(int entityId);

    String selectDiscussPostTitleByEntityId(int entityId);

    int updateType(int id,int type);

    int updateStatus(int id, int status);

    int updateScore(int id, double score);

}
