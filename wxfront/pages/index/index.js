// index.js
const config = require('../../config.js')
Page({
  data: {
    m: wx.getMenuButtonBoundingClientRect(),
    s: wx.getSystemInfoSync(),
    longitude: config.center_longitude,
    latitude: config.center_latitude,
    mapSubKey: config.mapSubKey, // 地图key
    longitude: 116.404, // 默认经度
    latitude: 39.915, // 默认纬度
    markers: [],
  },
  onShow() {
    this.getTabBar().init()
  },
  onLoad: function () {
    // 初始化时获取用户当前位置
    this.getUserLocation()
    // 模拟从后端获取的经纬度信息
    const location = {
      longitude: 116.404, // 替换为从后端获取的经度
      latitude: 39.915, // 替换为从后端获取的纬度
      title: '面包店', // 标记点的标题
      iconPath: '../../images/icons/icon_tab_photo.png', // 自定义图标路径
      width: 50, // 图标宽度
      height: 50, // 图标高度
    }
    this.setData({
      longitude: location.longitude,
      latitude: location.latitude,
      markers: [
        {
          id: 1,
          latitude: location.latitude,
          longitude: location.longitude,
          title: location.title,
          iconPath: location.iconPath,
          width: location.width,
          height: location.height,
        },
      ],
    })
  },
  getUserLocation: function () {
    const vm = this
    wx.getLocation({
      type: 'wgs84',
      success(res) {
        vm.setData({
          longitude: res.longitude,
          latitude: res.latitude,
        })
      },
    })
  },
  moveToLocation: function () {
    // 调用地图组件的移动到当前定位点
    const mapCtx = wx.createMapContext('map')
    mapCtx.moveToLocation()
  },
  goToSearch: function () {
    wx.navigateTo({
      url: './search-content/search-content',
    })
  },
})
