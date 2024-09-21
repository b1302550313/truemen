// pages/login/other-phone-login/other-phone-login.js
Page({
  data: {
    phoneNumber: '',
    verificationCode: ''
  },
  onPhoneInput: function (event) {
    this.setData({
      phoneNumber: event.detail.value
    });
  },
  onCodeInput: function (event) {
    this.setData({
      verificationCode: event.detail.value
    });
  },
  sendCode: function () {
    // 发送验证码的逻辑
    console.log('发送验证码');
  },
  switchToPasswordLogin: function () {
    wx.navigateTo({
      url: '/pages/password-login/password-login'
    });
  },
  doLogin: function () {
    if (this.data.phoneNumber && this.data.verificationCode) {
      // 登录逻辑
      console.log('登录');
    } else {
      console.log('请填写手机号和验证码');
    }
  }
});