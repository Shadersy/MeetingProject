package ru.gvozdilin.meet.controllers;

import freemarker.log.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gvozdilin.meet.dao.MeetUpDao;
import ru.gvozdilin.meet.dao.UserDao;
import ru.gvozdilin.meet.entity.MeetupUser;
import ru.gvozdilin.meet.services.MyUserPrincipal;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserDao userDao;
    @Autowired
    MeetUpDao meetUpDao;

    private static freemarker.log.Logger log = Logger.getLogger(MainController.class.getName());
        @GetMapping("/user")
        public String getAllUsers(Model userModel){
        userModel.addAttribute("userList", userDao.findAll());
        userModel.addAttribute("meetupList", meetUpDao.findAllMeetUps());
        userModel.addAttribute("meetupUserList", meetUpDao.findMeetupUsers());
        userModel.addAttribute("resultList", meetUpDao.showResultTable());

        List<MeetupUser> list = (List)userModel.getAttribute("resultList");

        for (MeetupUser s:list) {
            log.info(s.getMeetupId() + " " + s.getUserId() + " "+ s.getUsername() + " "+ s.getTime() + " ");
        }

        Map<String, List<MeetupUser>> collect = list.stream()
                .collect(Collectors.groupingBy(MeetupUser::getTime));
            userModel.addAttribute("MapResult", collect);
            for (String key : collect.keySet()) {
            log.info("Key = " + key);
            }

        for (String key : collect.keySet()) {
            List<MeetupUser> strings = collect.get(key);
            for (MeetupUser string : strings) {
              log.info("username = " + string.getUsername());
            }
        }

        Long AuthorithedUserId = ((MyUserPrincipal)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
        userModel.addAttribute("authIdUser", userDao.getUsernameByUserId(AuthorithedUserId));
        return "user";
}
        @PostMapping("/deleteMeetup")
        public String deleteMeetUp(HttpServletRequest request){
        int deletedMeetup = Integer.parseInt(request.getParameter("deleteSelect"));
            System.out.println(deletedMeetup);
        meetUpDao.deleteMeetUp(deletedMeetup);
            return "redirect:/user";
}
        @PostMapping("/createMeetup")
         public String creatingMeeting(HttpServletRequest request){
         ArrayList<Integer> usersId = new ArrayList<>();
         String kek = request.getParameter("dateMeetUp");

        for(String s : request.getParameterValues("users")){
                usersId.add(Integer.parseInt(s));
        }
        Long meetupID = meetUpDao.createMeetUp(kek);
        meetUpDao.setMeetUpUser(meetupID, usersId);
        return"redirect:/user";
    }
}
