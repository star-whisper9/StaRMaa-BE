var ioc = {
    dataSource: {
        type: "org.nutz.dao.impl.SimpleDataSource",
        fields: {
            jdbcUrl: "jdbc:postgresql://localhost:5432/starmaa",
            username: "postgres",
            password: "123456"
        }
    },
    dao: {
        type: "org.nutz.dao.impl.NutDao",
        args: [{refer: "dataSource"}]
    },
    $aop: {
        type: "org.nutz.ioc.aop.config.impl.JsonAopConfigration",
        fields: {
            itemList: [
                ['space.sotis.starmaa.web.services.*', '.+', 'space.sotis.starmaa.internal.interceptor.ServiceLoggingInterceptor'],
                ['space.sotis.starmaa.web.controllers.*', '.+', 'space.sotis.starmaa.internal.interceptor.ControllerLoggingInterceptor']
            ]
        }
    }
}