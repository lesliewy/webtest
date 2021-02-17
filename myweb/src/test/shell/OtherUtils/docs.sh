#! /bin/bash
# docs.sh 5 3:    wx 5篇, geektime 3篇;
#set -x

# 将随机选出的文章复制到这个目录.
temp_dir=/Users/leslie/Temp1/docs731

# 公众号文章目录.
wx_dir=/Volumes/docs/wx/data/
# 公众号目录名，用于随机选择.
wx_article_titles=("ACM算法日常" "Docker中文社区" "Hollis" "importNew" "java之道" "java编程" "java葵花宝典" "java进阶架构师" "labuladong" "Linux中国" "Python编程" "程序新视界" "方志朋" "肥朝" "黑客技术与网络安全" "架构师之路" "架构头条" "良许Linux" "前端大全" "数据仓库与Python大数据" "算法与数据结构" "小浩算法" "占小狼的博客" "张江打工人" "朱小厮的博客")

# geektime 文章目录.
gt_dir=/Volumes/docs/GeekTime/data/
# geektime 目录名，用于随机选择.  暂只支持html格式.
gt_article_titles=("Java核心技术36讲-杨晓峰" "Linux性能优化实战-倪朋飞" "MySQL实战45讲-林晓斌" "安全攻防技能30讲-何为舟" "从零开始学架构-李运华" "高并发系统设计40问-唐扬" "即时消息技术剖析与实战-袁武林" "技术管理实战36讲-刘建国" "浏览器工作原理与实践-李兵" "如何设计一个秒杀系统-许令波" "深入拆解java虚拟机-郑雨迪" "数据结构与算法之美-王争" "消息队列高手课-李玥" "许式伟的架构课-许式伟" "朱赟的技术管理课")

# 公众号文章数 参数1, 默认值为5
num_of_wx_articles=${1:=5}
# geektime 文章数 参数2, 默认值为5
num_of_gt_articles=${2:=5}

[ -d ${temp_dir} ] && rm -rf ${temp_dir}
[ ! -d ${temp_dir} ] && mkdir ${temp_dir}

# 随机抽取文章.
# $1: 目录
# $2: title 数组
# $3: 随机抽取的文章数.
function random_select_article(){
   dir="$1"
   article_titles_temp=$2
   num_of_articles="$3"
   no_new_window="$4"
   idx=0
   # 重新组织array, 不知道怎么传递.
   declare -a article_titles
   for i in ${article_titles_temp[*]}; do
      article_titles[idx]="$i"
      (( idx++ ))
   done
   [ ! -d $dir ] && echo "$dir not exists." && exit 2

   idx=0
   length_of_dir=${#dir}
   total_article_titles=${#article_titles[*]}
   pre_ifs=$IFS
   IFS=$'\n'

   # 这种随机方法不好：前端大全的文章非常多，不太感兴趣看，但是被选中概率却是很大.
   # for file in `find "$dir" -iname *.html|grep -v _files|sort -R |head -n $num_of_articles`;do

   # 换成这种: 先随机找一个标题，找标题是等概率的.
   for a in `seq $num_of_articles`; do
      random=$((RANDOM % total_article_titles))
      article_title=${article_titles[$random]}
      article_dir=$dir"/"$article_title
      for file in `find "$article_dir" -iname "*.html"|grep -v _files|grep -v "\._"|sort -R |head -n 1`;do
         t=${file%%".html"}
         dir_of_t=`dirname ${t}`
         # 完整的新路径: /Users/leslie/Temp1/docs731/Hollis/2019/01
         new_temp_dir=${temp_dir}${dir_of_t:$length_of_dir}
         [ ! -d ${new_temp_dir} ] && mkdir -p ${new_temp_dir}
         cp -r "$t"* `dirname "$t"`/images ${new_temp_dir} &>/dev/null

         # 打开文章.
         file_name=`basename ${file}`
         new_file=${new_temp_dir}/${file_name}
         echo ${new_file}
         if [[ -z $no_new_window && $idx -eq 0 ]]; then
             "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome" --new-window "$new_file" > /dev/null &
         else
             "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome" "$new_file" > /dev/null &
         fi
      (( idx++ ))
      done
   done
   IFS=$pre_ifs
}

random_select_article $wx_dir "${wx_article_titles[*]}" $num_of_wx_articles
random_select_article $gt_dir "${gt_article_titles[*]}" $num_of_gt_articles "no_new_window"

exit 0;
