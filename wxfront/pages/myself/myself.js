// pages/myself/myself.js
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    uid:'',
    avatarUrl: '/images/icons/pie.png',
    username: '用户名默认',
    doubleArrowFriends: '120',
    youFollowed: '130',
    followedYou: '140',
    userInfo:null,
    items: [
      { imageSrc: '/images/icons/pie.png', text: '我的合集1' },
      { imageSrc: '/images/icons/pie.png', text: '我的合集2' },
      { imageSrc: '/images/icons/pie.png', text: '我的合集3' },
      { imageSrc: '/images/icons/pie.png', text: '我的合集4' },
      { imageSrc: '/images/icons/pie.png', text: '我的合集5' },
      { imageSrc: '/images/icons/pie.png', text: '我的合集6' },
      { imageSrc: '/images/icons/pie.png', text: '我的合集7' },
      { imageSrc: '/images/icons/pie.png', text: '我的合集8' }
    ],
    aboutMe: '开心每一天红红火火恍恍惚惚哈哈哈哈',
    userId: '888',
    gender: '男/女/其他',
    birthday: '2007-01-04',
    zodiac: '金牛座',
    tags: ['各种标签', '各种标签', '各种标签', '各种标签','各种标签', '各种标签', '各种标签', '各种标签'],
    monthIndex: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  // onLoad: function () {
  //   wx.getSetting({
  //     success: res => {
  //       if (res.authSetting['scope.userInfo']) {
  //         wx.getUserInfo({
  //           success: res => {
  //             this.setData({
  //               userInfo: res.userInfo
  //             });
  //           }
  //         });
  //       }
  //     }
  //   });
  // },
  onLoad: function(options) {
    // 获取全局变量中的 uid 和 userName
    const globalData = getApp().globalData;
    console.log(globalData);
    const { uid, userName } = globalData;

    // 更新当前页面的数据
    this.setData({
      username: userName,
      uid: uid
    });

    // 利用 uid 向数据库发送请求获取其他信息
    // this.fetchAdditionalInfo(uid);
  },
  openAvatarUploader: function () {
    // 打开上传头像界面
    console.log('打开上传头像界面')
  },
  
  handleCalendarChange: function (e) {
    this.setData({
      monthIndex: e.detail.value
    });
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