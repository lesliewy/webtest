
# 如果存在多个空行，只保留一个.  
# sed 版本:利用多行模式空间(N 和D)

/^$/{
   N
   /\n$/D
}
