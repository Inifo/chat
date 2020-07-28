package com.chat.community.dao;

import com.chat.community.entity.DiscussPost;
import com.chat.community.entity.New;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewMapper {

    List<New> selectNews(int userId, int offset, int limit);

    List<New> selectNewsByTime(int userId, int offset, int limit);
    List<New> selectNewsBySports(int userId, int offset, int limit);
    List<New> selectNewsByEntertainment(int userId, int offset, int limit);
    // @Param注解用于给参数取别名,
    // 如果只有一个参数,并且在<if>里使用,则必须加别名.
    int selectNewRows(@Param("userId") int userId);

    int selectNewReplyRows(@Param("userId") int userId);



    int insertDiscussPost(DiscussPost discussPost);

    New selectNewById(int id);

    int updateCommentCount(int id, int commentCount);

    DiscussPost selectDiscussPostByEntityId(int entityId);

    String selectDiscussPostTitleByEntityId(int entityId);

    int updateType(int id,int type);

    int updateStatus(int id, int status);

    int updateScore(int id, double score);

}
