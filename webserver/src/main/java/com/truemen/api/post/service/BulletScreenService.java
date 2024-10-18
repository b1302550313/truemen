package com.truemen.api.post.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.truemen.api.post.dao.BulletScreenDao;
import com.truemen.api.post.dao.BulletScreenLikeDao;
import com.truemen.api.post.mapper.BulletScreenMapper;
import com.truemen.api.post.model.BulletScreen;
import com.truemen.api.post.model.BulletScreenLike;
import com.truemen.api.post.query.BulletScreenUploadQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BulletScreenService extends ServiceImpl<BulletScreenDao, BulletScreen> {
    @Autowired
    BulletScreenLikeDao bulletScreenLikeMapper;

    public Long upLoadBulletScreen(BulletScreenUploadQuery bulletScreenUploadQuery) {
        BulletScreen bulletScreen = BulletScreenMapper.INSTANCE.uploadBulletScreenToBulletScreen(bulletScreenUploadQuery);
        if (save(bulletScreen)) return bulletScreen.getBulletId();
        else return null;
    }

    public Boolean likeBulletScrren(Long uid,Long bulletId,Boolean yes){
        LambdaQueryWrapper<BulletScreenLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BulletScreenLike::getUserId,uid);
        queryWrapper.eq(BulletScreenLike::getBulletId,bulletId);
        Long count = bulletScreenLikeMapper.selectCount(queryWrapper);
//        log.info("count {}",count);
        if (count!=0 && !yes){
            return bulletScreenLikeMapper.delete(queryWrapper)==1?true:false;
        }
        if(count==0 && yes){
            BulletScreenLike like=new BulletScreenLike(null,bulletId,uid);
            return bulletScreenLikeMapper.insert(like)==1?true:false;
        }
        return true;
    }
}
