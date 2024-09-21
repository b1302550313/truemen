Page({
  data: {
    m: wx.getMenuButtonBoundingClientRect(), // 获取胶囊按钮位置
    s: wx.getSystemInfoSync(), // 获取设备信息
    autoFocus: true, // 确保输入框自动获得焦点
    query: '', // 搜索框输入内容
    historyList: ['搜索内容', '搜索内容', '搜索内容', '搜索内容', '搜索内容'], // 模拟的历史搜索数据
  },
  onReady: function () {
    this.setData({
      autoFocus: true,
    })
  },

  // 输入框输入事件
  onInput: function (e) {
    this.setData({
      query: e.detail.value,
    })
  },

  // 返回按钮点击事件
  goBack: function () {
    wx.navigateBack() // 返回上一页
  },
  onLoad() {
    // 监听键盘高度变化
    wx.onKeyboardHeightChange((res) => {
      console.log('键盘高度变化:', res.height)
      // 可以根据键盘高度调整页面布局
    })
  },
  clearHistory: function () {
    // 清空历史搜索记录
    this.setData({
      historyList: [],
    })
  },
})
