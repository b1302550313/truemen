package com.truemen.api.user.model;
// Twilio 是一个第三方短信服务提供商，使用其服务发送短信需要购买 Twilio 的服务
// package com.truemen.api.user.model;
// import com.twilio.Twilio;
// import com.twilio.rest.api.v2010.account.Message;
// import com.twilio.type.PhoneNumber;

// public class SMSClient {
// // Twilio 账户 SID 和 Auth Token
// public static final String ACCOUNT_SID = "your_twilio_account_sid";
// public static final String AUTH_TOKEN = "your_twilio_auth_token";

// // Twilio 发送短信的电话号码
// public static final String FROM_PHONE_NUMBER = "your_twilio_phone_number";

// public static void sendSMS(String toPhoneNumber, String messageBody) {
// Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
// Message message = Message.creator(
// new PhoneNumber(toPhoneNumber),
// new PhoneNumber(FROM_PHONE_NUMBER),
// messageBody)
// .create();
// System.out.println("SMS sent with SID: " + message.getSid());
// }
// }