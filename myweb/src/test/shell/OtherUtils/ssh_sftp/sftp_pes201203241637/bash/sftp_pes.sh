#! /bin/bash
# ���ڽɻ��ѻؽɿ�ȡ�ļ�
EX_OK=0
EX_USAGE=9
EX_FORMATDIR=3
EX_NOTDIR=4
EX_NOCHK=5                  # ûȡ��.chk�ļ�.
usage(){
   echo "usage: `basename $0` remoteuser remoteip basedir localchkdatadir remotechkdatadir remotedatadir"
   echo "    remoteuser:  Զ��sftp�û���."
   echo "    remoteip:    Զ�̻���ip."
   echo "    basedir:     ���ش�Ÿջ�ȡ�����ļ�����־��Ŀ¼."
   echo "    localchkdatadir:  ���ش�Ŵ���chk��dat�ļ�."
   echo "    remotechkdatadir: Զ�̴���ļ���bakĿ¼."
   echo "    remotedatadir:    Զ��Ŀ¼."
   exit $EX_USEAGE
}
# ͳһȥ��Ŀ¼������ "/"
formatdir(){
   [ "$#" -ne 1 ] && echo "formatdir: args error" && exit $EX_FORMATDIR   
   a=`echo "$1"|sed -e 's/\/*$//g'` 
   echo $a
}
# ɾ����ʱ�ļ�.
deletetempfiles(){
   rm -rf "$datadir_tmp"
   rm -rf "$sftpbatchfile1"
   rm -rf "$sftpbatchfile2"
}

[ "$#" -ne 6 ] && usage
date=`date +%Y%m%d%H%M%S`
# remote user ip
remoteuser=$1
remoteip=$2

if [ -d "$3" ]; then
   basedir=`formatdir $3`  
else
   echo "$3 is not a directory"
   exit $EX_NOTDIR
fi
# �Ǽ���־Ŀ¼
logdir=${basedir}/log
# ��־����
logfile=${logdir}/${date}.log
# ���ȡ�ص����е��ļ�,����ʱ��,����ɾ��.
datadir_tmp=${basedir}/${date}
sftpbatchfile1=${basedir}/${date}.file1
sftpbatchfile2=${basedir}/${date}.file2

# local ��chk��dat�ļ����Ŀ¼
if [ -d "$4" ]; then
   localchkdatadir=`formatdir $4`  
else
   echo "$4 is not a directory"
   exit $EX_NOTDIR
fi

# remote ���ݴ�chk��dat�ļ�Ŀ¼.
remotechkdatadir=`formatdir $5`
remotedatadir=`formatdir $6`

[ ! -d ${logdir} ] && mkdir ${logdir}
[ ! -d ${datadir_tmp} ] && mkdir ${datadir_tmp}
[ ! -f ${sftpbatchfile1} ] && touch ${sftpbatchfile1}
[ ! -f ${sftpbatchfile2} ] && touch ${sftpbatchfile2}

sftp $remoteuser@$remoteip:${remotedatadir}/* ${datadir_tmp}   &> ${logfile}    #  15 �ϲ���ʹ�� &>>
# scp mktdev@127.0.0.1:wy/temp/* .

# ����chk�ļ� + dat�ļ�.
chkflag=`find ${datadir_tmp} -name *.chk`
if [ -z "$chkflag" ];then
   echo "no check file." >>  ${logfile}
   deletetempfiles
   exit $EX_NOCHK
fi
datawithchk=`ls ${datadir_tmp}/*.chk|sed -e 's/chk/dat/g'`" "`ls ${datadir_tmp}/*.chk` >> ${logfile} 
echo "datawithchk: ${datawithchk}" >>${logfile}

# ��chk�ļ���dat�ļ�ת��.
cp $datawithchk ${localchkdatadir} >>${logfile}
echo ${datawithchk} |tr ' ' '\n'|sed -e 's/^/put /g' > ${sftpbatchfile1}

# ����chk�ļ���dat�ļ�sftp��remote
sftp -b ${sftpbatchfile1} $remoteuser@$remoteip:${remotechkdatadir} >> ${logfile}

# ɾ��remote�� .chk �� ��.chkǰ׺��ͬ��.dat�ļ�.
for line in `cat ${sftpbatchfile1}|cut -d ' ' -f 2`; do
   sftpbatchfile21=$sftpbatchfile21" "`basename $line`
done
echo ${sftpbatchfile21} |tr ' ' '\n'| sed -e 's/^/rm /g' > ${sftpbatchfile2}
sftp -b ${sftpbatchfile2} $remoteuser@$remoteip:${remotedatadir}  >> ${logfile}

# ɾ��������ʱ�ļ�.
deletetempfiles

exit $EX_OK
