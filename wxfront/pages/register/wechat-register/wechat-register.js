// pages/register/wechat-register/wechat-register.js
// pages/login/login.js
Page({
  data: {
    userInfo: null,
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
  },

  onLaunch: function () {
    // 登录
    wx.login({
      success: res => {
        if (res.code) {
          // 发起网络请求，传递 code 到后端
          this.requestLogin(res.code);
        } else {
          console.error('获取用户登录态失败！' + res.errMsg);
        }
      }
    });

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
            success: res => {
              this.globalData.userInfo = res.userInfo;
            }
          });
        }
      }
    });
  },

  requestLogin: function (code) {
    wx.request({
      url: 'https://your-backend-url/login',
      data: {
        code: code
      },
      method: 'POST',
      header: {
        'content-type': 'application/json'
      },
      success: res => {
        if (res.data.status === 'success') {
          const { accessToken, refreshToken, userInfo } = res.data;
          wx.setStorageSync('accessToken', accessToken);
          wx.setStorageSync('refreshToken', refreshToken);
          wx.setStorageSync('userInfo', userInfo);
        } else {
          console.error('登录失败：', res.data.message);
        }
      },
      fail: err => {
        console.error('请求登录失败：', err);
      }
    });
  },
  
  onGetUserInfo: function (e) {
    if (e.detail.userInfo) {
      // 用户已经同意授权
      wx.login({
        success: (res) => {
          if (res.code) {
            // 发送 code 到后端
            wx.request({
              url: 'https://your-backend-url/login',
              data: {
                code: res.code
              },
              method: 'POST',
              header: {
                'content-type': 'application/json'
              },
              success: (res) => {
                if (res.data.status === 'success') {
                  // 保存登录状态
                  wx.setStorageSync('userInfo', res.data.userInfo);
                  wx.setStorageSync('accessToken', res.data.accessToken);
                  wx.setStorageSync('refreshToken', res.data.refreshToken);
                } else {
                  console.error('登录失败：', res.data.message);
                }
              },
              fail: (err) => {
                console.error('请求登录失败：', err);
              }
            });
          } else {
            console.error('获取用户登录态失败！');
          }
        }
      });
    } else {
      // 用户拒绝授权
      console.log('用户拒绝授权');
    }
  },
  globalData: {
    userInfo: null
  },
  // 不同意按钮
  disagree: function () {
    console.log('不同意');
    // 不做任何事情
    wx.navigateBack({
      delta: 1
    });
  }
});