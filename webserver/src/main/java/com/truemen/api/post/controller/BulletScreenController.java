package com.truemen.api.post.controller;


import com.truemen.api.common.exception.ErrorCode;
import com.truemen.api.common.exception.ServerException;
import com.truemen.api.common.result.Result;
import com.truemen.api.post.query.BulletScreenUploadQuery;
import com.truemen.api.post.service.BulletScreenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/bullet-screen")
@Validated
public class BulletScreenController {

    @Autowired
    private BulletScreenService bulletScreenService;

    @PostMapping("/upload")
    Result<Map<String,Long>> uploadBulletScreen(@RequestBody @Valid BulletScreenUploadQuery bulletScreenUploadQuery){
        // upload BulletScreen
        Long bulletId = bulletScreenService.upLoadBulletScreen(bulletScreenUploadQuery);
        Map<String, Long> data = new HashMap<>();
        data.put("bulletId",bulletId);
        if (bulletId==null){
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return Result.ok(data);
    }


    @PutMapping("/{uid}/like/{bulletId}/{yes}")
    Result likeBulletScreen(
            @PathVariable("uid") Long uid,
            @PathVariable("bulletId") Long bulletId,
            @PathVariable("yes") Boolean yes
    ){
        Boolean success = bulletScreenService.likeBulletScrren(uid, bulletId, yes);
        if (success==null){
            throw new ServerException("点赞失败");
        }
        return Result.ok();
    }
}
