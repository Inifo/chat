package com.chat.news.controller;

import com.chat.community.service.UserService;
import com.chat.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Newcontroller implements CommunityConstant {

    @Autowired
    private UserService userService;







}
