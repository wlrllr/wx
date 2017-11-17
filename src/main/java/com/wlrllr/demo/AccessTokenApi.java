package com.wlrllr.demo;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

@Component
public class AccessTokenApi{}
/*extends QuartzJobBean {

    public AccessToken getAccessToken() {
        if (accessToken == null || accessToken.getExpiresIn() < System.currentTimeMillis()) {
            //理想状态下当前时间永远比过期时间小，因为调试在过期前5分钟会刷新令牌
            accessToken = doGetAccessToken();
        }
        return accessToken;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        doGetAccessToken();
    }

    private AccessToken doGetAccessToken() {
        final AccessToken accessToken = restOperations.getForObject(url, AccessToken.class, apiConfig.getAppId(), apiConfig.getAppSecret());
        if (accessToken.getExpiresIn() != null) {//返回错误时该值为空
            //腾讯的过期时间单位为秒，业务为了方便使用了毫秒
            accessToken.setExpiredOn(System.currentTimeMillis() + accessToken.getExpiresIn() * 1000);

            Executors.newSingleThreadExecutor().execute(new Runnable() {//异步去处理
                @Override
                public void run() {
                    try {
                        for (JobKey key : scheduler.getJobKeys(GroupMatcher.jobGroupStartsWith("getAccessToken"))) {
                            scheduler.deleteJob(key);
                        }
                        JobDetail detail = newJob().withIdentity(join("getAccessToken", randomAlphanumeric(32))).ofType(AccessTokenApi.class).build();
                        Trigger trigger = newTrigger()
                                .withIdentity(randomAlphanumeric(10))
                                .withSchedule(CronScheduleBuilder
                                        .cronSchedule(new DateTime(accessToken.getExpiredOn())
                                                .minusMinutes(5)
                                                .toString("s m H d M ? yyyy")))
                                .build();

                        scheduler.scheduleJob(detail, trigger);
                    } catch (SchedulerException e) {
                        e.printStackTrace();//发邮件警告*/
