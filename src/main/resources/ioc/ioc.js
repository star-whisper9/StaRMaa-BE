var ioc = {
    dao: {
        type: "org.nutz.dao.impl.NutDao",
        refer: "dataSource"
    },
    dataSource: {
        type: "org.nutz.dao.impl.SimpleDataSource",
        fields: {
            jdbcUrl: "jdbc:postgresql://localhost:5432/starmaa",
            username: "postgres",
            password: "123456"
        }
    }
}