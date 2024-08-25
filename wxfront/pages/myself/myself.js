// pages/myself/myself.js
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: '/images/register/register.webp',
    username: '用户名1',
    doubleArrowFriends: '120',
    youFollowed: '130',
    followedYou: '140',
    items: [
      { imageSrc: '/images/register/register.webp', text: '我的合集1' },
      { imageSrc: '/images/register/register.webp', text: '我的合集2' },
      { imageSrc: '/images/register/register.webp', text: '我的合集3' },
      { imageSrc: '/images/register/register.webp', text: '我的合集4' },
      { imageSrc: '/images/register/register.webp', text: '我的合集5' },
      { imageSrc: '/images/register/register.webp', text: '我的合集6' },
      { imageSrc: '/images/register/register.webp', text: '我的合集7' },
      { imageSrc: '/images/register/register.webp', text: '我的合集8' }
    ],
    aboutMe: '开心每一天',
    userId: '我的ID:',
    gender: '男/女/其他',
    birthday: '2007-01-04',
    zodiac: '金牛座',
    tags: ['各种标签', '各种标签', '各种标签', '各种标签','各种标签', '各种标签', '各种标签', '各种标签'],
    monthIndex: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    // this.setData({
    //   avatarUrl: app.globalData.avatarUrl || ''
    // })
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