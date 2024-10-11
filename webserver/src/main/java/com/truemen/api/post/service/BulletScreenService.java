package com.truemen.api.post.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.truemen.api.post.dao.BulletScreenDao;
import com.truemen.api.post.mapper.BulletScreenMapper;
import com.truemen.api.post.model.BulletScreen;
import com.truemen.api.post.query.BulletScreenUploadQuery;
import org.springframework.stereotype.Service;

@Service
public class BulletScreenService extends ServiceImpl<BulletScreenDao, BulletScreen> {


    public Long upLoadBulletScreen(BulletScreenUploadQuery bulletScreenUploadQuery) {
        BulletScreen bulletScreen = BulletScreenMapper.INSTANCE.uploadBulletScreenToBulletScreen(bulletScreenUploadQuery);
        if (save(bulletScreen)) return bulletScreen.getBulletId();
        else return null;
    }
}
