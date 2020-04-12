package cn.wy.biz.elasticjob;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.google.common.base.Joiner;

/**
 * Created by leslie on 2019/7/26.
 */
public class MySimpleJob extends AbstractSimpleElasticJob {

    // private static final Logger logger = LogManager.getLogger(MySimpleJob.class);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MySimpleJob.class);

    @Override
    public void process(JobExecutionMultipleShardingContext jobExecutionMultipleShardingContext) {
        logger.info("this is mySimpleJob...");

        Map<Integer, String> itemParameters = jobExecutionMultipleShardingContext.getShardingItemParameters();
        List<Integer> items = jobExecutionMultipleShardingContext.getShardingItems();
        String jobParameters = jobExecutionMultipleShardingContext.getJobParameter();
        System.out.println(jobParameters);
        logger.info("itemParameters: {}, items: {}, jobParameters: {}", Joiner.on('，').join(itemParameters.entrySet()),
                    Joiner.on(',').join(items), jobParameters);

        /*
         * // System.out.println(item); // switch // (item) { case 0: // do something by sharding item 0
         * System.out.println(String.format("-----ThreadId:%s,当前分片项：%s",Thread.currentThread().getId(),item)); break;
         * case 1: // do something by sharding item 1
         * System.out.println(String.format("-----ThreadId:%s,当前分片项：%s",Thread.currentThread().getId(),item)); break;
         * case 2: // do something by sharding item 2
         * System.out.println(String.format("-----ThreadId:%s,当前分片项：%s",Thread.currentThread().getId(),item)); break; }
         */
    }
}
