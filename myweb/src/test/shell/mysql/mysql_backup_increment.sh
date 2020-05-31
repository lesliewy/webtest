#!/bin/bash
##############################
#
# mysql 增量备份.
#
##############################

### 不同机器可能修改.
backup_dir=/usr/local/mysqlbackup
binlog_dir=/usr/local/mysql/logs
login_path_name=root
mysqladmin_bin=/usr/local/mysql/bin/mysqladmin
mysqlsocket=/usr/local/mysql/mysql.sock
bin_index=/usr/local/mysql/logs/mysql-bin.index

#backup_dir=/data/spartan/mysql/backup
#binlog_dir=/data/spartan/mysql/mysql-5.7.29-el7-x86_64/log
#login_path_name=test
#mysqladmin_bin=/data/spartan/mysql/mysql-5.7.29-el7-x86_64/bin/mysqladmin
#mysqlsocket=/data/spartan/mysql/mysql-5.7.29-el7-x86_64/mysql.sock
#bin_index=/data/spartan/mysql/mysql-5.7.29-el7-x86_64/log/mysql-bin.index

backup_incre_dir=$backup_dir/increment
backup_log=$backup_dir/backup.log
[ ! -d $backup_dir ] && mkdir $backup_dir
[ ! -d $backup_incre_dir ] && mkdir $backup_incre_dir

$mysqladmin_bin --login-path=${login_path_name} --socket $mysqlsocket flush-logs
counter=`wc -l $bin_index |awk '{print $1}'`
next_num=0
cur_time=`date +%Y%m%d%H%M%S`
# 遍历index文件中的所有文件. 最后一个 skip, 因为是正在使用的.
for file in `cat $bin_index`
do
    base=`basename $file`
    next_num=`expr $next_num + 1`
    if [ $next_num -eq $counter ]; then
        echo $base skip! >> $backup_log
    else
        dest=$backup_incre_dir/$base
        exists_flag=`ls ${dest}* 2>/dev/null`
        if test ${#exists_flag} -gt 0
        then
            echo $base exist! >> $backup_log
        else
            cp $binlog_dir/$base $backup_incre_dir/$base.$cur_time
            echo $base copying >> $backup_log
         fi
     fi
done
echo [IncreBackup] `date +"%Y年%m月%d日 %H:%M:%S"` $next_num Backup successful! >> $backup_log
