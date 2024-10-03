package com.truemen.api.user.service;

import com.truemen.api.user.model.WechatUserInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WechatService {
    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

    private final HttpSession httpSession;

    public WechatService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public WechatUserInfo getUserInfoByCode(String code) {
        // 获取 access_token 和 openid
        Map<String, String> accessTokenResponse = getAccessTokenAndOpenid(code);
        String accessToken = accessTokenResponse.get("access_token");
        String openid = accessTokenResponse.get("openid");

        // 获取微信用户信息
        WechatUserInfo userInfo = getUserInfoByAccessToken(accessToken, openid);

        // 将用户信息存储在 HttpSession 中
        httpSession.setAttribute("wechatUserInfo", userInfo);

        return userInfo;
    }

    private Map<String, String> getAccessTokenAndOpenid(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("appid", appid);
        uriVariables.put("secret", secret);
        uriVariables.put("code", code);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Map.class,
                uriVariables);

        Map<String, String> response = (Map<String, String>) responseEntity.getBody();
        return response;
    }

    private WechatUserInfo getUserInfoByAccessToken(String accessToken, String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token={access_token}&openid={openid}";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("access_token", accessToken);
        uriVariables.put("openid", openid);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WechatUserInfo> responseEntity = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
                WechatUserInfo.class, uriVariables);

        return responseEntity.getBody();
    }
}