#!/usr/bin/env bash
#################################################################################
#  输出thread stack、heapdump、gc
#  v1.0
#  wy
#################################################################################

usage(){
   cat << ENDOFUSAGE
   用法:   $(basename "$0") [-t] [-h] [-g]
             -t [interval]: 每隔指定时间(默认2s)输出一次cpu 占用top 5 thread, 总共输出5次.
                            输出的最后两列分别是十进制tid, 十六进制tid.
             -u [interval] [count]: 每隔指定时间(默认5s)输出一次thread stack, 总共指定的次数(默认3次).
             -h: 输出 heapdump(必要时使用).
             -g [interval] [count]: 输出 gc, 默认2000ms, 共5次.
ENDOFUSAGE
}

gen_thread_stack(){
   pid="$1"
   file="$2"
   ### 生成thread stack, 可以加 -l
   jstack "$pid" > "$file"
   echo -e "线程堆栈文件已生成: " "$file"
}

gen_thread_stack_interval(){
   pid="$1"
   interval_="$2"
   thread_stack_output_file_num_="$3"

   # 默认输出thread stack 的间隔时间.
   interval=5
   # 默认输出thread stack 次数
   thread_stack_output_file_num=3

   if [ -n "$interval_" ] && [ "$interval_" -gt 0 ];then
      interval="$interval_"
   fi
   if [ -n "$thread_stack_output_file_num_" ] && [ "$thread_stack_output_file_num_" -gt 0 ];then
      thread_stack_output_file_num="$thread_stack_output_file_num_"
   fi

   index=0
   while [[ "$index" -lt "$thread_stack_output_file_num" ]];do
      ((index++))
      file="$(date +%Y%m%d%H%M%S.%N).dat"
      gen_thread_stack "$pid" "$file"
      sleep "$interval"
   done
}

gen_heapdum(){
   pid="$1"
   file="$2"
   echo -e "pid: " "$pid"
   jmap -dump:format=b,file="$file" "$pid"
}

print_top_thread_once(){
   pid="$1"
   ps -mp "$pid" -oTHREAD,tid |sed '1d'|sort -rn -k 2|head -n 5|awk '{print($1, "   ", $2, "   ", $8, "   ", sprintf("%x", $8))}'
}

print_thread(){
   pid="$1"
   interval_="$2"

   # 默认输出thread stack 的间隔时间.
   interval=2
   # 默认输出thread stack 次数
   thread_stack_output_num=5

   if [ -n "$interval_" ] && [ "$interval_" -gt 0 ];then
      interval="$interval_"
   fi
   
   ### 输出thread stack
   index=0
   while [[ "$index" -lt "$thread_stack_output_num" ]];do
      ((index++))
      echo "pid: " "$pid" " time:" "$(date '+%F %T')" " interval:" "$interval""s" 
      print_top_thread_once "$pid"
      echo -e
      sleep "$interval"
   done
}

print_gc(){
   pid="$1"
   interval_="$2"
   count_="$3"

   interval=2000
   count=5
   if [ -n "$interval_" ] && [ "$interval_" -gt 0 ];then
      interval="$interval_"
   fi
   if [ -n "$count_" ] && [ "$count_" -gt 0 ];then
      count="$count_"
   fi

   echo -e "pid: " "$pid" " interval: " "$interval" "ms"
   ### 使用gccause, 包含了gcutil, 还可以输出上次和本次gc的原因. System.gc() or Allocation failure
   jstat -gccause "$pid" "$interval" "$count"
}


EX_USAGE=64;
EX_OK=0;

#pid=$(ps aux|grep tomcat|grep -v grep|awk '{print $2}')
pid=$(pgrep java)
post=$(date +%Y%m%d%H%M%S)

[ "$#" -eq 0 ] && usage && exit $EX_USAGE
while getopts "tuhg" option;do
   case "$option" in
      "t") print_thread "$pid" "$2"
           thread_stack_name="$HOME/thread_stack_""${post}"".dat"
           gen_thread_stack "$pid" "$thread_stack_name"
           exit $EX_OK
           ;;
      "u") gen_thread_stack_interval "$pid" "$2" "$3"
           exit $EX_OK
           ;;
      "h") heapdum_file="$HOME/heapdump_""${post}"".bin"
           gen_heapdum "$pid" "$heapdum_file"
           exit $EX_OK
           ;;
      "g") print_gc "$pid" "$2" "$3"
           exit $EX_OK
           ;;
       *) usage
          exit $EX_USAGE
          ;;
   esac
done
