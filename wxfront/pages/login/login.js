// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: '',
    password: '',
    phone:13512341234,
  },
  handleUsernameChange: function(e) {
    this.setData({
      username: e.detail.value
    });
  },

  handlePasswordChange: function(e) {
    this.setData({
      password: e.detail.value
    });
  },

  handleLogin: function() {
    const { username, password } = this.data;

    if (!username || !password) {
      wx.showToast({
        title: '请输入用户名和密码',
        icon: 'none'
      });
      return;
    }

    // 假设这里调用 API 进行登录验证
    // 例如:
    // wx.request({ ... })

    wx.showToast({
      title: '登录成功',
      icon: 'success'
    });
    setTimeout(() => {
      wx.switchTab({
        url: '/pages/home/home'
      });
    }, 1000);
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})