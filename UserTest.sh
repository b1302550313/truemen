curl -X POST \
     -H "Content-Type: application/json" \
     -d '{"userName":"zhangsan","weChatID":"zhangsan2234111","password":"securepassword","avatar":"https://example.com/avatar.jpg","permissionLevel":1}' \
     http://localhost:8000/api/users/register