// pages/term-of-service/term-of-service.js
Page({
  data: {
    sections: [
      {
        title: '1. 协议介绍',
        content: [
          '本协议由您与我们的公司签订。',
          '通过注册或使用我们的服务，即表示您同意遵守本协议的所有条款和条件。'
        ]
      },
      {
        title: '2. 使用规则',
        content: [
          '您必须年满18岁才能使用我们的服务。',
          '不得用于非法目的。'
        ]
      },
      // 更多协议内容...
    ]
  },

  goBack: function() {
    wx.navigateBack({
      delta: 1
    });
  },

  agree: function() {
    wx.showModal({
      title: '确认',
      content: '您确定接受用户服务协议吗？',
      success: function(res) {
        if (res.confirm) {
          wx.showToast({
            title: '已接受协议',
            icon: 'success',
            duration: 2000
          });
        } else if (res.cancel) {
          console.log('用户点击取消');
        }
      }
    });
  },

  disagree: function() {
    wx.showModal({
      title: '确认',
      content: '您确定不接受用户服务协议吗？',
      success: function(res) {
        if (res.confirm) {
          wx.showToast({
            title: '未接受协议',
            icon: 'none',
            duration: 2000
          });
        } else if (res.cancel) {
          console.log('用户点击取消');
        }
      }
    });
  }
});