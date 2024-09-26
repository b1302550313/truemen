@echo off
setlocal enabledelayedexpansion

echo "你的MySQL信息："
for /f "tokens=1,2 delims==" %%a in (config.txt) do (
    set %%a=%%b
    echo %%a=%%b
)

:: 设置字符集
set CHARSET=utf8mb4
:: 设置数据库
set DATABASE=verto

echo 执行 "init.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! < init.sql
echo 执行 "userCoreInfo.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/userCoreInfo.sql
echo 执行 "landmark.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/landmark.sql
echo 执行 "post.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/post.sql
echo 执行 "groups.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/groups.sql
echo 执行 "chatRecord.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/chatRecord.sql
echo 执行 "postLike.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/postLike.sql
echo 执行 "postMedia.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/postMedia.sql
echo 执行 "talkBox.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/talkBox.sql
echo 执行 "userBaseInfo.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/userBaseInfo.sql
echo 执行 "userFriend.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/userFriend.sql
echo 执行 "userGroup.sql"
mysql -h !HOST! -u !USER! -p!PASSWORD! --default-character-set=!CHARSET! !DATABASE! < ./test_data/userGroup.sql

echo SQL 执行完毕

pause
endlocal
