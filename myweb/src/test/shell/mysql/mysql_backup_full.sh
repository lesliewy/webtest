#!/bin/bash
##############################
#
# mysql 全量备份.
#
##############################

# 定时任务
# 每个星期日凌晨3:00执行全量备份脚本
# 0 3 * * 0 root /data/spartan/mysql/shell/mysql_backup_full.sh
# 周一到周六凌晨3:00执行增量备份脚本
# 0 3 * * 1-6 root /data/spartan/mysql/shell/mysql_backup_increment.sh

### 不同机器可能修改.
backup_dir=/usr/local/mysqlbackup
login_path_name=root
mysqldump_bin=/usr/local/mysql/bin/mysqldump
mysqlsocket=/usr/local/mysql/mysql.sock

#backup_dir=/data/spartan/mysql/backup
#login_path_name=test
#mysqldump_bin=/data/spartan/mysql/mysql-5.7.29-el7-x86_64/bin/mysqldump
#mysqlsocket=/data/spartan/mysql/mysql-5.7.29-el7-x86_64/mysql.sock

[ ! -d $backup_dir ] && mkdir $backup_dir
backup_log=$backup_dir/backup.log
cur_time=`date +%Y%m%d%H%M%S`
begin=`date +"%Y年%m月%d日 %H:%M:%S"`
cd $backup_dir
dump_file=ALL_$cur_time.sql
dump_file_gzip=${dump_file}.gz
$mysqldump_bin --login-path=${login_path_name} --socket $mysqlsocket --quick --events --all-databases --flush-logs --single-transaction > $dump_file 2>>$backup_log

dump_result=success.
if [ ! -f $dump_file -o `ls -l $dump_file | awk '{ print $5 }'` == 0 ]; then
   dump_result=failed.
   echo [FullBackup] 开始:$begin 结束:$end $dump_file_gzip $dump_result >> $backup_log
   exit 1;
fi
tar -zvcf $dump_file_gzip $dump_file > /dev/null
rm $dump_file
end=`date +"%Y年%m月%d日 %H:%M:%S"`
echo [FullBackup] 开始:$begin 结束:$end $dump_file_gzip $dump_result >> $backup_log
# scp重复全量备份文件到其他服务器
# scp $dump_file_gzip root@xxxx:/usr/mysql/backup/
# 删除30天前的全量备份文件
find $backup_dir -mtime +30 -type f -name "*.sql.gz" | xargs rm -f
# 删除增量备份文件
#cd $BakDir/daily
#rm -f *
