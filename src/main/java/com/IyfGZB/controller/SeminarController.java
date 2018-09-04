package com.IyfGZB.controller;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.dto.SeminarDto;
import com.IyfGZB.repositories.SeminarRecordRepo;
import com.IyfGZB.services.SchedularService;
import com.IyfGZB.services.SeminarOperation;
import com.IyfGZB.services.SeminarRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class SeminarController {

    public static final Logger logger = LoggerFactory.getLogger(SeminarController.class);

    @Autowired
    private SeminarOperation seminarOperation;

    @Autowired
    private SeminarRecordService seminarRecordService;

    @Autowired
    private SchedularService schedularService;

    @GetMapping("/sheet")
    public void sendSheet(){
        schedularService.sendSeminarAttendanceSheet();
    }

    @GetMapping("/upcomingSeminars/{vedicLevel}/{pageNumber}/{itemPerPage}")
    public HashMap<String,Object> getUpComingSeminars(@PathVariable("vedicLevel") Integer vediclevel,
                                                        @PathVariable("pageNumber") Integer pageNumber,
                                                        @PathVariable("itemPerPage") Integer itemPerPage){

        return seminarOperation.getUpcomingSeminars(vediclevel,pageNumber,itemPerPage);

    }

    @PostMapping("/bookSeatForSeminar/{seminarId}/{status}/{email}")
    public CommonResponseDTO bookSeatForSeminar(@PathVariable("seminarId") Long seminarId,
                                                @PathVariable("status") String status,
                                                @PathVariable("email") String email){

       return seminarRecordService.bookSeatForSeminar(seminarId, status,email);

    }

    @PutMapping("/cancelSeatForSeminar/{seminarRecordId}/{reason}/{email}")
    public CommonResponseDTO cancelSeatForSeminar(@PathVariable("reason") String reason,
                                                  @PathVariable("seminarRecordId") Long seminarRecordId){
        return seminarRecordService.cancelseatForSeminar(seminarRecordId,reason);
    }
//    @GetMapping("/gettotalPage/{itemsPerPage}")
//    public Integer getTotalNumberOfpages(@PathVariable("itemsPerPage") Integer itemPerPage){
//        seminarRecordSer
//    }



}
