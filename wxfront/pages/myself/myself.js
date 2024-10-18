// pages/myself/myself.js
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    uid:null,
    phone:null,
    wechatId: null,
    avatarUrl: '/images/icons/pie.png',
    userName: '默认用户名',
    bio: '默认自我介绍',
    userId: 'user_000',
    gender: '男/女/其他',
    birthDate: '2000-01-01',
    zodiac: '水瓶座',
    doubleArrowFriends: '0',
    youFollowed: '0',
    followedYou: '0',
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
    tags: ['各种标签', '各种标签', '各种标签', '各种标签','各种标签', '各种标签', '各种标签', '各种标签'],
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
  navigateToLogin: function() {
    // 跳转到登录页面
    wx.navigateTo({
      url: '/pages/login/login'
    });
  },
  fetchAdditionalInfo: function(uid) {
    // 发送 GET 请求到后端
    console.log("fetchAdditonalInfo");
    wx.request({
      url: getApp().globalData.host + '/api/users/uid/' + uid + '/profile', // 后端 API 地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.statusCode === 200) {
          // 请求成功
          const userInfo = res.data; // 假设返回的数据格式为 { userName, phone, wechatId, birthDate, bio }
          // 更新页面数据
          console.log("put edit",userInfo);
          this.setData({
            userName: userInfo.userName ,
            phone: userInfo.phone ,
            wechatId: userInfo.wechatId ,
            userId:userInfo.userId,
            birthDate: userInfo.birthDate,
            bio: userInfo.bio,
            gender:userInfo.gender
          });
        } else {
          // 请求失败
          wx.showToast({
            title: '获取用户信息失败',
            icon: 'none',
            duration: 2000
          });
        }
      },
      fail: (error) => {
        // 请求失败
        wx.showToast({
          title: '网络错误',
          icon: 'none',
          duration: 2000
        });
      }
    });
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

  onLoad: function(options) {
    // 获取全局变量中的 uid 和 userName
    console.log("myself onLoad");
    const globalData = getApp().globalData;
    const { uid} = globalData;   
    // 利用 uid 向数据库发送请求获取其他信息
    if(uid){
      this.fetchAdditionalInfo(uid);
    }
    console.log("onLoad myself data",this.data);
  },
    /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    console.log("myself onReady")
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    console.log("myself onShow")
    const globalData = getApp().globalData;
    const { uid} = globalData;
    // 更新当前页面的数据
    if(uid){
      this.setData({
        uid:globalData.uid,
        phone:globalData.phone,
        wechatId: globalData.wechatId,
        userName:globalData.userName,
        bio:globalData.bio,
        userId:globalData.userId,
        gender:globalData.gender,
        birthDate:globalData.birthDate,
        zodiac: globalData.zodiac,
      });
    }
    console.log("onShow myself data",this.data);
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
    console.log("myself onUnload")
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