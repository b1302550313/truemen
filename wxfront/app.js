// app.js
App({
  globalData: {
    // host: 'http://localhost:8000',
    host: 'http://192.168.43.216:8000',// 域名
    userName:'',
    navBarHeight: 0, // 导航栏高度
    menuRight: 0, // 胶囊距右方间距（方保持左、右间距一致）
    menuTop: 0, // 胶囊距顶部间距
    menuHeight: 0, // 胶囊高度（自定义内容可与胶囊高度保证一致）
    menuWidth: 0, // 胶囊宽度（自定义内容可与胶囊宽度保证一致）
    searchMarginTop: 0, // 搜索框上边距
    searchWidth: 0, // 搜索框宽度
    searchHeight: 0, // 搜索框高度
  },
  onLaunch: function (options) {
    const that = this
    // 获取系统信息
    const systemInfo = wx.getSystemInfoSync()
    // 胶囊按钮位置信息
    const menuButtonInfo = wx.getMenuButtonBoundingClientRect()
    console.log(systemInfo)
    console.log(menuButtonInfo)

    const { screenWidth, statusBarHeight } = systemInfo
    const { top, width, height, right } = menuButtonInfo
    const margin = top - statusBarHeight
    // 导航栏高度 = 状态栏高度 + 顶部窗口高度（胶囊按钮位置信息上边距 - 状态栏高度） * 2 + 胶囊按钮高度 + 胶囊按钮位置信息下边距 - 状态栏高度
    that.globalData.navBarHeight = height + statusBarHeight + margin * 2
    that.globalData.menuRight = screenWidth - right
    that.globalData.menuTop = top
    that.globalData.menuHeight = height
    that.globalData.menuWidth = width
    this.globalData.searchMarginTop = statusBarHeight + margin // 状态栏 + 胶囊按钮边距
    this.globalData.searchHeight = height // 与胶囊按钮同高
    this.globalData.searchWidth = right - width - 20 // 胶囊按钮右边坐标 - 胶囊按钮宽度 = 按钮左边可使用宽度
  },
})
