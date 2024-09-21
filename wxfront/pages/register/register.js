// pages/register/register.js
Page({
  data: {
    // 页面数据
  },

  // 微信注册按钮点击事件
  wechatRegister: function() {
    console.log('微信注册');
    // 添加微信登录的逻辑
    wx.navigateTo({
      url: './wechat-register/wechat-register',
      success: function(res) {
        // 成功跳转后的回调函数
      },
      fail: function(err) {
        console.error('跳转失败:', err);
      }
    });
  },

  // 手机号注册按钮点击事件
  phoneRegister: function() {
    console.log('手机号注册');
    // 添加手机号登录的逻辑
    wx.navigateTo({
      url: './phone-register/phone-register',
      success: function(res) {
        // 成功跳转后的回调函数
      },
      fail: function(err) {
        console.error('跳转失败:', err);
      }
    });
  },

  // 跳过按钮点击事件
  skipRegister: function() {
    console.log('跳过');
    wx.switchTab({
      url: '../index/index',
      success: function(res) {
        // 成功跳转后的回调函数
      },
      fail: function(err) {
        console.error('跳转失败:', err);
      }
    });
  }
});