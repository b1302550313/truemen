// pages/myself/edit-myself.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: '/images/icons/pie.png',
    userName: '张三',
    aboutMe: '开心每一天红红火火恍恍惚惚哈哈哈哈',
    userId: '888',
    gender: '男',
    genderOptions: ['男', '女', '其他'], 
    currentGenderIndex: 0
  },


  onNameChange: function (e) {
    this.setData({
      userName: e.detail.value
    });
  },
  onUerIdChange: function (e) {
    this.setData({
      userId: e.detail.value
    });
  },
  onAboutMeChange: function (e) {
    this.setData({
      aboutMe: e.detail.value
    });
  },
  onGenderChange: function (e) {
    const index = e.detail.value;
    this.setData({
      currentGenderIndex: index,
      gender: this.data.genderOptions[index]
    });
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