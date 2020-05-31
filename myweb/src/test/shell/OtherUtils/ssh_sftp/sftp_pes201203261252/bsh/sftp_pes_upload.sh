#! /bin/sh
# ���ڽɻ��ѻؽɿ��ϴ��ļ�.

# args 1: ���ϴ��ļ���,����·����ʾ.
# args 2: Զ��Ŀ¼.

EX_OK=0
EX_NOFILE=2           # ָ���ļ�������.
EX_ARGS=3             # ��������.
EX_SFTP_ERROR=5       # sftp ����, ������Ϣ��log.

formatdir(){
   [ "$#" -ne 1 ] && exit $EX_FORMATDIR
   a=`echo "$1"|sed -e 's/\/*$//g'`
   echo $a
}
deletetempfiles(){
   rm -rf $sftpbatchfile
}

date=`date +%Y%m%d%H%M%S`
[ $# -ne 4 ] && exit $EX_ARGS
# Զ���û�,ip
remoteuser=$1
remoteip=$2
# �ļ�����
[ ! -f $3 ] && exit $EX_NOFILE
absfilename=$3
filename=`basename $absfilename`
# Զ��Ŀ¼.
remotedir=`formatdir $4`

# �Ǽ���־Ŀ¼
absfilenamelength=`expr length $absfilename`
filenamelength=`expr length $filename`
sublength=`expr $absfilenamelength - $filenamelength`
logdir=`expr substr $absfilename 1 $sublength`"log"
# ��־����
logfile=${logdir}/${date}.log
# sftp batch file
sftpbatchfile=${logdir}/${date}.file

[ ! -d $logdir ] && mkdir $logdir
[ ! -f $logfile ] && touch $logfile
[ ! -f $sftpbatchfile ] && touch $sftpbatchfile

# �½�.chk�ļ�
chkfile=`echo "$absfilename"|sed -e 's/\.dat/\.chk/g'`
echo $filename >> $chkfile

# �ϴ��ļ�
echo "put $chkfile" >> $sftpbatchfile
echo "put $absfilename" >> $sftpbatchfile
sftp -oPasswordAuthentication=no -b $sftpbatchfile $remoteuser@$remoteip:$remotedir > $logfile 2>&1
sftpreturn=$?
if [ "$sftpreturn" -ne 0 ]; then
   echo "sftp return: $sftpreturn" >>$logfile
   deletetempfiles
   exit $EX_SFTP_ERROR                                                   # sftp ����,�鿴log
fi
# ɾ����ʱ�ļ�
deletetempfiles

exit $EX_OK
