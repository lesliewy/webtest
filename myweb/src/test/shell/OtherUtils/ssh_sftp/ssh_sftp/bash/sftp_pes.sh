#! /bin/bash
# ���ڽɻ��ѻؽɿ�ȡ�����ļ�
EX_OK=0
EX_ARGS=3                   # sftp_pes.sh ��������.
EX_NOTDIR=4                 # ָ���Ĳ���һ��Ŀ¼.
EX_SFTP_ERROR=5             # SFTP����.
EX_NOCHK=6                  # ûȡ��.chk�ļ�.
EX_FORMATDIR=7
EX_USAGE=9

#usage(){
#   echo "usage: `basename $0` remoteuser remoteip basedir localchkdatadir remotechkdatadir remotedatadir"
#   echo "    remoteuser:  Զ��sftp�û���."
#   echo "    remoteip:    Զ�̻���ip."
#   echo "    basedir:     ���ش�Ÿջ�ȡ�����ļ�����־��Ŀ¼."
#   echo "    localchkdatadir:  ���ش�Ŵ���chk��dat�ļ�."
#   echo "    remotechkdatadir: Զ�̴���ļ���bakĿ¼."
#   echo "    remotedatadir:    Զ��Ŀ¼."
#   exit $EX_USEAGE
#}
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
   rm -rf "$logfile1"
   rm -rf "$logfile2"
}

[ "$#" -ne 6 ] && exit $EX_ARGS 
date=`date +%Y%m%d%H%M%S`
# remote user ip
remoteuser=$1
remoteip=$2

if [ -d "$3" ]; then
   basedir=`formatdir $3`  
else
   exit $EX_NOTDIR
fi
# �Ǽ���־Ŀ¼
logdir=${basedir}/log
# ��־����
logfile=${logdir}/${date}.log
logfile1=${logdir}/${date}.log1              # ���� &>.
logfile2=${logdir}/${date}.log2              # ���� &>.
# ���ȡ�ص����е��ļ�,����ʱ��,����ɾ��.
datadir_tmp=${basedir}/${date}
sftpbatchfile1=${basedir}/${date}.file1
sftpbatchfile2=${basedir}/${date}.file2

# local ��chk��dat�ļ����Ŀ¼
if [ -d "$4" ]; then
   localchkdatadir=`formatdir $4`  
else
   exit $EX_NOTDIR
fi

# remote ���ݴ�chk��dat�ļ�Ŀ¼.
remotechkdatadir=`formatdir $5`
remotedatadir=`formatdir $6`

[ ! -d ${logdir} ] && mkdir ${logdir}
[ ! -d ${datadir_tmp} ] && mkdir ${datadir_tmp}
[ ! -f ${sftpbatchfile1} ] && touch ${sftpbatchfile1}
[ ! -f ${sftpbatchfile2} ] && touch ${sftpbatchfile2}

sftp -oPasswordAuthentication=no  $remoteuser@$remoteip:${remotedatadir}/* ${datadir_tmp}   &> ${logfile}    #  15 �ϲ���ʹ�� &>>  ��Ϊ�������û��������������֤����ģʽ,������ֹ�������.
sftpreturn=$?
if [ "$sftpreturn" -ne 0 ]; then
   echo "sftp return: $sftpreturn" >>$logfile
   deletetempfiles
   exit $EX_SFTP_ERROR                                                   # sftp ����,�鿴log
fi
# scp mktdev@127.0.0.1:wy/temp/* .

# ����chk�ļ� + dat�ļ�,.chk .CHK��֧��.
chkflag1=`find ${datadir_tmp} -name *.chk`
chkflag2=`find ${datadir_tmp} -name *.CHK` 
if [ -z "$chkflag1" -a -z "$chkflag2" ];then
   echo "no check file." >>  ${logfile}
   deletetempfiles
   exit $EX_NOCHK
fi
if [ -n "$chkflag1" ];then
   datawithchk1=`ls ${datadir_tmp}/*.chk|sed -e 's/\.chk$/\.dat/g'`" "`ls ${datadir_tmp}/*.chk`
fi
if [ -n "$chkflag2" ];then
   datawithchk2=`ls ${datadir_tmp}/*.CHK|sed -e 's/\.CHK$/\.dat/g'`" "`ls ${datadir_tmp}/*.CHK`                              
fi
datawithchk=$datawithchk1" "$datawithchk2
for chkfile in `ls ${datadir_tmp}/*.chk 2>/dev/null` `ls ${datadir_tmp}/*.CHK 2>/dev/null`;do
   noimportfilename=`echo $chkfile|sed -e 's/\.chk$/\.noimport/g' -e 's/\.CHK$/\.noimport/g'` 
   touch $noimportfilename
   datawithchknoimport1=$datawithchknoimport1" ""$noimportfilename"
done
datawithchknoimport=$datawithchk" "$datawithchknoimport1 
echo "datawithchknoimport: ${datawithchknoimport}" >>${logfile}

# ��chk�ļ���dat�ļ�ת��,ͬʱ����ͬ��.noimport�ļ���ת��.
cp $datawithchknoimport ${localchkdatadir} >>${logfile}
echo ${datawithchk} |tr ' ' '\n'|sed -e 's/^/put /g' > ${sftpbatchfile1}

# ����chk�ļ���dat�ļ�sftp��remote
sftp -oPasswordAuthentication=no -b ${sftpbatchfile1} $remoteuser@$remoteip:${remotechkdatadir}  &> ${logfile1}
sftpreturn=$?
if [ "$sftpreturn" -ne 0 ]; then
   cat ${logfile1} >> $logfile
   echo "sftp return: $sftpreturn" >>$logfile
   deletetempfiles
   exit $EX_SFTP_ERROR                                                   # sftp ����,�鿴log
fi
cat ${logfile1} >> $logfile

# ɾ��remote�� .chk �� ��.chkǰ׺��ͬ��.dat�ļ�.
for line in `cat ${sftpbatchfile1}|cut -d ' ' -f 2`; do
   sftpbatchfile21=$sftpbatchfile21" "`basename $line`
done
echo ${sftpbatchfile21} |tr ' ' '\n'| sed -e 's/^/rm /g' > ${sftpbatchfile2}
sftp -oPasswordAuthentication=no -b ${sftpbatchfile2} $remoteuser@$remoteip:${remotedatadir}  &> ${logfile2}
sftpreturn=$?
if [ "$sftpreturn" -ne 0 ]; then
   cat ${logfile2} >> $logfile
   echo "sftp return: $sftpreturn" >>$logfile
   deletetempfiles
   exit $EX_SFTP_ERROR                                                   # sftp ����,�鿴log
fi
cat ${logfile2} >> $logfile

# ɾ��������ʱ�ļ�.
deletetempfiles
times >> ${logfile}

exit $EX_OK
