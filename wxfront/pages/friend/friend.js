// pages/friend/friend.js
Page({
  data: {
    currentTab: 0,
    friends: [
      { id: 1, name: '张三', avatar: 'https://example.com/avatar/zhangsan.jpg', latestMessage: '你好，最近怎么样？', time: '2023-09-01 10:30', unreadCount: 2 },
      { id: 2, name: '李四', avatar: 'https://example.com/avatar/lisi.jpg', latestMessage: '今天天气不错！', time: '2023-09-02 12:45', unreadCount: 0 }
    ],
    groups: [
      { id: 1, name: '朋友群', avatar: 'https://example.com/group/group1.jpg', latestMessage: '周末一起出去玩吧！', time: '2023-09-01 10:30', unreadCount: 5 },
      { id: 2, name: '工作群', avatar: 'https://example.com/group/group2.jpg', latestMessage: '明天会议记得准备资料。', time: '2023-09-02 12:45', unreadCount: 0 }
    ]
  },
  switchTab: function (event) {
    const tab = event.currentTarget.dataset.tab;
    console.warn(tab);
    this.setData({
      currentTab: parseInt(tab)
    });
  },
  addFriend: function () {
    console.log('添加好友');
  }
});