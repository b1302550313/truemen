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
    genderOptions: ['男', '女', '匿'], 
    constellationList: [
      '水瓶座',  // 1月20日-2月18日
      '双鱼座',  // 2月19日-3月20日
      '白羊座',  // 3月21日-4月19日
      '金牛座',  // 4月20日-5月20日
      '双子座',  // 5月21日-6月21日
      '巨蟹座',  // 6月22日-7月22日
      '狮子座',  // 7月23日-8月22日
      '处女座',  // 8月23日-9月22日
      '天秤座',  // 9月23日-10月23日
      '天蝎座',  // 10月24日-11月22日
      '射手座',  // 11月23日-12月21日
      '摩羯座'   // 12月22日-1月19日
    ],
    birthday: '2007-01-04',
    currentConstellation: '金牛座',
    currentGenderIndex: 0,
    currentConstellationIndex:0
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
  bindDateChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      birthday: e.detail.value
    })
  },
  handleConstellationChange(e) {
    const index = e.detail.value;
    this.setData({
      currentConstellationIndex:index,
      currentConstellation: this.data.constellationList[index]
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const userId = options.userId || '';
    if (userId) {
      this.fetchUserProfile(userId);
    }
  },
  fetchUserProfile: function(userId) {
    wx.request({
      url: `${this.globalData.host}/${userId}/profile`,
      method: 'GET',
      success: res => {
        if (res.statusCode === 200) {
          const user = res.data;
          this.setData({
            userId: user.id,
            userName: user.userName,
            avatarUrl: user.avatar,
            aboutMe: user.bio,
            gender: user.gender,
            birthday: user.birthDate,
            // currentConstellation: this.getConstellation(user.birthDate),
            // currentGenderIndex: this.getGenderIndex(user.gender)
          });
        } else {
          console.error('获取用户信息失败:', res);
        }
      },
      fail: err => {
        console.error('请求失败:', err);
      }
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