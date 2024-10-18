package com.truemen.api.post.vo;

import com.truemen.api.post.model.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PostDetailVo{
    public ExtPost base;
    public List<Long> mediaId;

    @Data
    @NoArgsConstructor
    public static class ExtPost{
        public Contact contact;
        protected Long postId;
        protected Long landmarkId;
        protected Long uid;
        protected String title;
        protected String content;
        protected LocalDateTime createTime;
        protected LocalDateTime updateTime;
        protected Long tag;
        protected Long visibility;
        protected Long allowComment;
        protected Long duration;

        @Data
        @NoArgsConstructor
        public static class Contact {
            public String phone;
            public String qq;
            public String wx;
        }
    }
}
