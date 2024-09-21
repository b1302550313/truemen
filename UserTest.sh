curl -X POST \
     -H "Content-Type: application/json" \
     -d '{"userName":"zhangsan","phone":"13512341234","weChatID":"zhangsan2234111","password":"securepassword","avatar":"https://example.com/avatar.jpg"}' \
     http://localhost:8080/api/users/registerByPhone


