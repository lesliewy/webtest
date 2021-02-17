#! /bin/bash
###   处理目录、文件的中的特殊符号.
###   special_filename.sh . wx

#set -x

# 去掉目录中两个以上的".",
process_dot(){
   for special_dir in `find "$1" -iname "*\.\." -type d`;do
      echo "$special_dir"
      last_dir=`basename $special_dir`
      after=`dirname $special_dir`"/""${last_dir//./}"
      echo "after: $after"
      mv $special_dir $after
   done;

   for special_file in `find "$1" -iname "*\.\.*" -type f`;do
      echo "$special_file"
      last_file=`basename $special_file`
      after1=${last_file/.html/}
      after2=${after1//./}
      after=`dirname $special_file`"/"$after2".html"
      echo $after
      mv $special_file $after
   done;
}

# 处理 "|", 英文":", 英文"?", 英文"*", 将其替换为"-"
# 处理英文"", 将其转为中文“”  ---- 暂未处理, 文件名可以替换,但是文件内容中出现了src='./xxxx  其他都是 src="./xxxx
process_vertical(){
   search_dir="$1"
   # 文件名中可能包含whitespace, 导致获取文件名出错. 需要修改IFS， 仅换行符
   # "" 可以阻止处理whitespace, 但是这里不能用, 因为whitespace中包含\n, 也阻止了换行.
   pre_ifs=$IFS
   IFS=$'\n'
   old_base_name=""
   for special_dat in `find "$search_dir" -iname "*[|:?*]*"`;do
      old_base_name=`basename $special_dat`
      # 处理目录
      if [ -d "$special_dat" ];then
         echo "$special_dat"
         after=$(replace_last ${special_dat} "|" "-")
         after=$(replace_last ${after} ":" "-")
         after=$(replace_last ${after} "?" "-")
         after=$(replace_last ${after} "*" "-")
         mv $special_dat $after
      fi
      # 处理html
      old_html=$special_dat".html"
      new_html=$after".html"
      if [ -e $old_html ];then
         echo "new_html: $new_html"
         substring=$old_base_name
         replace=`basename $after`
         # 替换文件中的字符串.
         replace_str_in_file "$old_html" "$substring" "$replace" "$new_html"
         rm "$old_html"
      fi

   done
   IFS=$pre_ifs
}

replace_last(){
   # 待替换的完整字符串
   dat="$1"
   # 待替换的字符串
   substring="$2"
   # 替换后的字符串.
   replace="$3"
   last_dir=`basename $dat`
   # substring用"", 防止?等字符转义.
   after=`dirname $dat`"/"${last_dir//"$substring"/$replace}
   # 返回值处理, 返回字符串.
   echo $after
}

replace_str_in_file(){
   # 源文件
   old_file="$1"
   # 待替换字符串, 对*先做特殊处理, 否在即使在""中，仍然会被转义成正则.
   substring="$2"
   substring=${substring/\*/\\*}
   # 替换后字符串
   replace="$3"
   # 新文件
   new_file="$4"
   if [ -z $new_file ]; then
      new_file="`dirname $old_file`/${replace}.html"
   fi
#   echo "file: $old_file, substring: $substring, replace: $replace, new_file: $new_file"
   # sed 中使用variable, 放在"", 不能放在''中.
   # 放在""中的*仍会被转义.
   sed 's/src=".\/'"$substring"'/src=".\/'"$replace"'/' "$old_file" > "$new_file"
}

dir="$1"
reok="$2"
 [[ $reok != "wx" ]] && echo "确定选择的目录, 会替换文件名, 切记!" && exit 2;
 process_vertical $dir

