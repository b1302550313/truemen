// pages/myself/edit-myself.js
Page({
  data: {
    avatarUrl: '/images/icons/pie.png',
    uid:0,
    userName: '默认用户名',
    bio: '默认自我介绍',
    userId: 'user_000',
    gender: '匿',
    birthDate: '2000-01-01',
    zodiac: '水瓶座',
    genderList: ['男', '女', '匿'], 
    genderMap:{
      "男":0,
      "女":1,
      "匿":2,
    },
    zodiacList: [
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
    zodiacMap : {
      '水瓶座': 0,
      '双鱼座': 1,
      '白羊座': 2,
      '金牛座': 3,
      '双子座': 4,
      '巨蟹座': 5,
      '狮子座': 6,
      '处女座': 7,
      '天秤座': 8,
      '天蝎座': 9,
      '射手座': 10,
      '摩羯座': 11
    },
    currentGenderIndex: 2,  // genderlist索引
    currentZodiacIndex:0  // 星座list索引
  },
  
  onNameChange: function (e) {
    this.setData({
      userName: e.detail.value
    });
  },
  onUserIdChange: function (e) {
    this.setData({
      userId: e.detail.value
    });
  },
  onBioChange: function (e) {
    this.setData({
      bio: e.detail.value
    });
  },
  onGenderChange: function (e) {
    const index = e.detail.value;
    this.setData({
      currentGenderIndex: index,
      gender: this.data.genderList[index]
    });
  },
  bindDateChange: function(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      birthDate: e.detail.value
    })
  },
  handleZodiacChange(e) {
    const index = e.detail.value;
    console.log(index,this.data.zodiacList[index]);
    this.setData({
      currentZodiacIndex:index,
      zodiac: this.data.zodiacList[index]
    });
  },
  cancelButton: function() {
    wx.navigateBack({
      delta: 1
    });
  },
  confirmButton: function() {
    console.log("confirmButton",this.data);
    wx.request({
      url: `${getApp().globalData.host}/api/users/uid/${this.data.uid}/profile/edit`,
      method: 'PUT',
      data: JSON.stringify({
        uid:this.data.uid,
        userId:this.userId,
        phone: this.data.phone,
        userName:this.data.userName,
        bio:this.data.bio,
        gender:this.data.gender,
        birthDate:this.data.birthDate,
        zodiac: this.data.zodiac,
      }),
      header: {
        'Content-Type': 'application/json'
      },
      success: res=>{
        if (res.statusCode === 200) {
          wx.showToast({
            title: '保存成功',
            icon: 'success',
            duration: 2000
          });
          getApp().setGlobalData({
            userName:this.data.userName,
            bio:this.data.bio,
            userId:this.data.userId,
            gender:this.data.gender,
            birthDate:this.data.birthDate,
            zodiac: this.data.zodiac,
          });
          console.log("edit globaldata success");
          wx.navigateBack({
            delta: 1
          });
        } else {
          wx.showToast({
            title: '保存失败',
            icon: 'none',
            duration: 2000
          });
        }
      },
      fail: function(err) {
        console.error('请求失败:', err);
        wx.showToast({
          title: '网络错误',
          icon: 'none',
          duration: 2000
        });
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    console.log("edit myself onLoad")
    const globalData = getApp().globalData;
    const { uid} = globalData;
    // 更新当前页面的数据
    if(uid){
      this.setData({
        uid:globalData.uid,
        userName:globalData.userName,
        bio:globalData.bio,
        userId:globalData.userId,
        gender:globalData.gender,
        birthDate:globalData.birthDate,
        zodiac: globalData.zodiac,
      });
    }
    this.data.currentGenderIndex=this.data.genderMap[this.data.gender];
    this.data.currentZodiacIndex=this.data.zodiacMap[this.data.zodiac];
    console.log("onLoad edit  myself data",this.data);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow(options) {
    console.log("edit myself onShow")
    const globalData = getApp().globalData;
    const { uid} = globalData;
    // 更新当前页面的数据
    if(uid){
      this.setData({
        uid:globalData.uid,
        userName:globalData.userName,
        bio:globalData.bio,
        userId:globalData.userId,
        gender:globalData.gender,
        birthDate:globalData.birthDate,
        zodiac: globalData.zodiac,
      });
    }
    this.data.currentGenderIndex=this.data.genderMap[this.data.gender];
    this.data.currentZodiacIndex=this.data.zodiacMap[this.data.zodiac];
    console.log("onShow edit  myself data",this.data);
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