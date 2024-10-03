package com.truemen.api.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.truemen.api.user.model.WechatUserInfo;

@Service
public class WechatClient {
    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

    private final RestTemplate restTemplate;

    public WechatClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("appid", appid);
        uriVariables.put("secret", secret);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Map.class,
                uriVariables);
        Map<String, String> response = responseEntity.getBody();

        if (response == null || !response.containsKey("access_token")) {
            throw new RuntimeException("Failed to get access token from WeChat API");
        }

        return response.get("access_token");
    }

    public WechatUserInfo getUserInfo(String openId, String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={access_token}&openid={openid}&lang=zh_CN";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("access_token", accessToken);
        uriVariables.put("openid", openId);

        ResponseEntity<WechatUserInfo> responseEntity = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
                WechatUserInfo.class, uriVariables);
        WechatUserInfo userInfo = responseEntity.getBody();

        if (userInfo == null) {
            throw new RuntimeException("Failed to get user info from WeChat API");
        }

        return userInfo;
    }
}
