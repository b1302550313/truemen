# 转换sql为utf-8
import codecs

with codecs.open("friend_backup.sql", "r", encoding="utf-16-le") as f_in:
    with codecs.open("output.sql", "w", encoding="utf-8") as f_out:
        for line in f_in:
            f_out.write(line)