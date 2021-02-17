local key=KEYS[1]
local args=ARGV
-- redis-cli -h 127.0.0.1 -p 6379 --eval /Users/leslie/MyProjects/GitHub/webtest/myweb/src/main/resources/redis/test1.lua wy1 , 20 aa
-- 设置一个key = wy1,value=aa, 20s过期时间
return redis.call("setex",key,unpack(args))
