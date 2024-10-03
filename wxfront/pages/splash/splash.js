// pages/splash/splash.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  /**
   * 用户点击确定按钮跳转到主页
   */
  goToIndex: function() {
    if (this.data.selectedFeeling) {
      // 设置全局变量 feeling 为当前选择的心情
      getApp().globalData.feeling = this.data.selectedFeeling;
      wx.switchTab({
        url: '/pages/index/index'
      });
    } else {
      // 如果没有选择心情，弹出提示对话框
      wx.showToast({
        title: '您还没有选择心情哦',
        icon: 'none',
        duration: 2000
      });
    }
  },
  selectMood: function(e) {
    let feeling = e.currentTarget.dataset.feeling;
    if (feeling !== this.data.selectedFeeling) {
      this.setData({
        selectedFeeling: feeling
      });
    }
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