package com.truemen.api.post.query;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BulletScreenUploadQuery {

    @NotNull(message = "用户ID不能为空")
    public Long uid;

    @NotNull(message = "用户ID不能为空")
    public Long landmarkId;

    @NotEmpty(message = "帖子内容不能为空")
    public String content;

    /**
     * 标签，只选一个,对应0123
     */
    @NotNull(message = "标签不能为空")
    public Long tag;

    /**
     * 对谁可见，0所有人，1朋友可见，2仅自己可见
     */
    @NotNull(message = "visibility不能为空")
    public Long visibility;

    /**
     * 是否可评论，0可，1不可
     */
    @NotNull(message = "是否可评论不能为空")
    public Long allowComment;

    /**
     * 联系方式，逗号隔开，依次为手机；qq;微信
     */
    public String contactInfo;

    /**
     * 0永久，1一年，2一月，3一天，4一小时
     */
    @NotNull(message = "保留市场不能为空")
    public Long duration;

}