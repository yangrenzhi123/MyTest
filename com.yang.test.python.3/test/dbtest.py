import sqlite3
conn = sqlite3.connect('./test.cloud')
c = conn.cursor()
cursor = c.execute('select userid,name,password,mobile,lastdb,lastattach from y_user where mobile=?',('13588068929',))
item = cursor.fetchone()
print(item)