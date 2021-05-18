package com.gupaoeud.vip.mebatis.v1;

/**
 * 提供对外访问的简易API接口
 */
public class GpSqlSession {

    private GpConfiguration configuration;
    private GpExecutor executor;

    public GpSqlSession(){
        if(executor == null){
            executor = new GpExecutor();
        }
        if(configuration == null){
            configuration = new GpConfiguration();
        }
    }

    /**
     * 对外提供的查询的方法
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId,Object parameter){
        System.out.println(statementId);
        // 根据statementId 获取对应的SQL语句
        String sql = GpConfiguration.sqlMappings.getString(statementId);
        System.out.println(sql);

        return executor.query(sql,parameter);
    }

    public <T> T getMapper(Class clazz){
        return configuration.getMapper(clazz,this);
    }
}
