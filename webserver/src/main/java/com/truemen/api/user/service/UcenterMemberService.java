package com.truemen.api.user.service;

import com.truemen.api.user.model.UcenterMember;

public interface UcenterMemberService {
    UcenterMember getOpenIdMember(String openid);
    void save(UcenterMember member);
}