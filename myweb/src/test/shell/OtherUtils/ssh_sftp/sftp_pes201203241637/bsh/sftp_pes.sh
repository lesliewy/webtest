#! /bin/bash

EX_OK=0
date=`date +%Y%m%d%H%M%S`
#basedir=/media/S/Work/LESLIE/linux/shell/OtherUtils/ssh_sftp
basedir=$1
# �Ǽ���־Ŀ¼
logdir=${basedir}/log
# ��־����
logfile=${logdir}/${date}.log
# ���ȡ�ص����е��ļ�,����ʱ��,����ɾ��.
datadir_tmp=${basedir}/${date}
sftpbatchfile=${basedir}/${date}.file
# ��chk��dat�ļ����Ŀ¼
#localchkdatadir=${basedir}/chkdata
localchkdatadir=$2

# remote ���ݴ�chk��dat�ļ�Ŀ¼.
remotechkdatadir=$3
remotedatadir=$4

 [ ! -d ${logdir} ] && mkdir ${logdir}
 [ ! -d ${datadir_tmp} ] && mkdir ${datadir_tmp}
 [ ! -f ${sftpbatchfile} ] && touch ${sftpbatchfile}

sftp mktdev@127.0.0.1:${remotedatadir}/* ${datadir_tmp}  &>> ${logfile}
# scp mktdev@127.0.0.1:wy/temp/* .
# ������chk�ļ���dat�ļ�.
datawithchk=`ls ${datadir_tmp}/*.chk|sed -e 's/chk/dat/g'` &>> ${logfile}
echo ${datawithchk} &>>${logfile}

# ����chk�ļ���dat�ļ�ת��.
cp $datawithchk ${localchkdatadir} &>>${logfile}

echo ${datawithchk} |sed -e 's/^/put /g' &>> ${sftpbatchfile}
# ����chk�ļ���dat�ļ�sftp��remote
#sftp -b ${sftpbatchfile} mktdev@127.0.0.1:${remotedatadir}

exit $EX_OK
