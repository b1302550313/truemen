Page({
  data: {
    m: wx.getMenuButtonBoundingClientRect(), // 获取胶囊按钮位置
    s: wx.getSystemInfoSync(), // 获取设备信息
    autoFocus: false, // 确保输入框自动获得焦点
    query: '', // 搜索框输入内容
    tabs: [
      { name: '热门' },
      { name: '户外' },
      { name: '休闲' },
      { name: '文化' },
      { name: '美食' },
      { name: '拍照' },
      { name: '探店' },
      { name: '一人游' },
      { name: '多人游' },
      { name: '一日游' },
      // 添加更多标签
    ],
    showEditPanel: false,
    arrowIcon: 'arrow-down',
    panelHeight: 0
  },

  onTabClick(e) {
    const index = e.currentTarget.dataset.index
    console.log('Tab clicked:', index)
    // 切换标签选中状态的逻辑
  },

  showAllTabs() {
    this.setData({ showModal: true })
  },

  hideModal() {
    this.setData({ showModal: false })
  },

  onModalTabClick(e) {
    const index = e.currentTarget.dataset.index
    console.log('Modal tab clicked:', index)
    this.hideModal()
    // 可能还需要切换选中的标签
  },

  // 切换编辑面板的显示状态
  toggleEditPanel() {
    const newState = !this.data.showEditPanel
    const query = wx.createSelectorQuery()
    query
      .select('.header')
      .boundingClientRect((rect) => {
        this.setData({
          showEditPanel: newState,
          arrowIcon: newState ? 'arrow-up' : 'arrow-down',
          panelHeight: newState ? 'show' : ''// 控制高度变化
        })
      })
      .exec()
  },

  // 标签点击事件
  onTagClick(e) {
    const index = e.currentTarget.dataset.index
    console.log('Tag clicked:', this.data.editableTabs[index])
    // 处理标签点击事件，进入对应页面
  },

  // 标签长按事件，进入编辑模式
  onTagLongPress(e) {
    const index = e.currentTarget.dataset.index
    console.log('Tag long pressed:', this.data.editableTabs[index])
    // 进入拖动排序模式
  },
  // 跳转搜索详情界面
  goToSearch: function () {
    wx.navigateTo({
      url: './search-content/search-content',
    })
  },
})
