package com.sysu.verto.post.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("bulletScreen")
public class BulletScreen {
    @TableId(value = "bulletId")
    private Long bulletId;
    private Long uid;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Integer visibility;
    private Integer duration;
    private String contactInfo;
}