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
  // 登录按钮点击事件处理函数
  handleLogin: function() {
    // 检查全局数据中的登录状态
    const globalData = getApp().globalData;
    console.log(globalData);
    if (!globalData.isLogin || !globalData.uid) {
      // 如果未登录，则跳转到登录页面
      wx.navigateTo({
        url: '/pages/login/login',
      });
    } else {
      // 如果已登录，则可能执行其他操作，例如显示主页等
      // 这里可以添加相应的逻辑
      console.log('已经登录');
    }
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