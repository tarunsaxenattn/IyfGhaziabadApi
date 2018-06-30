package com.IyfGZB.services;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.NotificationServices.SeminarNotificationService;
import com.IyfGZB.controller.AccountController;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.securityservices.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class SeminarOperation {

    @Autowired
    SeminarNotificationService notificationService;

    @Autowired
    SeminarRepo seminarRepo;

    public static final Logger logger = LoggerFactory.getLogger(SeminarOperation.class);

    public CommonResponseDTO createSeminar(Seminar seminar){
        try{
            UserInfo userInfo=CurrentUser.getCurrentUser();

            seminar.setModifiedDate(new Date());
            seminar.setModifiedBy(userInfo.getEmail());
            seminar.setCreatedBy(userInfo.getEmail());
            seminar.setCreatedDate(new Date());
            seminarRepo.save(seminar);

            notificationService.sendEmail(seminar);

            return new CommonResponseDTO("success","Seminar Created Successfully");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger","OOPS..! Seminar Could Not Be Created");


        }
    }
    public List<Seminar> getUpcomingSeminars(Integer vedicLevel,Integer pageNumber,Integer itemPerPage){
      try{
          PageRequest pageRequest=new PageRequest(pageNumber,itemPerPage,Sort.Direction.DESC,"date");
          List<Seminar> seminars=seminarRepo.findAllByDateAfter(new Date(),pageRequest);
//          seminars.forEach(seminar -> {
//              String seminarStartTime=seminar.getStartTime();
//              if(seminarStartTime != null ){
//                  LocalTime now = LocalTime.now();
//                  LocalTime startTime = LocalTime.parse( seminarStartTime );
//                  if(!startTime.isAfter( now )){
//                      seminars.remove(seminar);
//                  }
//              }
//          });

//          seminars.forEach();
          return seminars;
      }catch (Exception e){
         logger.error(e.getMessage());
         return null;
      }


    }



}
