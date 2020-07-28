package com.chat.community;

import com.chat.community.dao.*;
import com.chat.community.entity.*;
import com.chat.community.dao.*;
import com.chat.community.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private DiscussPost1Mapper discussPost1Mapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TestMapper testMapper;


    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("s");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user =new User();
        user.setUsername("ss");

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser() {
        int rows = userMapper.updateName(150, "sss");
        System.out.println(rows);

        rows = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(150, "hello");
        System.out.println(rows);
    }

    @Test
    public void testSelectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0,0,10,0);
        for (DiscussPost post : list){
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc",1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }

    @Test
    public void testSelectLetters() {
        List<Message> list = messageMapper.selectConversations(111, 0, 20);
        for (Message message : list) {
            System.out.println(message);
        }

        int count = messageMapper.selectConversationCount(111);
        System.out.println(count);

        list = messageMapper.selectLetters("111_112", 0, 10);
        for (Message message : list) {
            System.out.println(message);
        }

        count = messageMapper.selectLetterCount("111_112");
        System.out.println(count);

        count = messageMapper.selectLetterUnreadCount(131, "111_131");
        System.out.println(count);

    }

    @Test
    public void replyTest(){


       String d = discussPostMapper.selectDiscussPostTitleByEntityId(184);
        System.out.println(d);



    }

    @Test
    public void modeTest(){
        List<Text> list = testMapper.selectDiscussPosts(0,0,10,2);
        for (Text text : list) {
            System.out.println(text);
        }

        int rows = testMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }

    @Test
    public void removeDuplicate2(){
        String[] array ={"b","a","c","a","1","3","E"};
        Arrays.sort(array,String.CASE_INSENSITIVE_ORDER);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
                list.add(array[i]);
        }
        System.out.println("list"+list);
    }


    }



