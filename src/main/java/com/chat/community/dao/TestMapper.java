package com.chat.community.dao;

import com.chat.community.entity.DiscussPost;
import com.chat.community.entity.Text;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestMapper {

    List<Text> selectDiscussPosts(int userId, int offset, int limit,int orderMode);

    List<Text> selectDiscussPostsByMode(int userId, int offset, int limit, int mode);


    // @Param注解用于给参数取别名,
    // 如果只有一个参数,并且在<if>里使用,则必须加别名.
    int selectDiscussPostRows(@Param("userId") int userId);

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
