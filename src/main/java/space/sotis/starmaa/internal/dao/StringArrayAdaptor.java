package space.sotis.starmaa.internal.dao;

import org.nutz.castor.Castors;
import org.nutz.dao.jdbc.ValueAdaptor;

import java.sql.*;

/**
 * @author x1ngyu
 * @since 2024/10/15
 */

public class StringArrayAdaptor<T> implements ValueAdaptor {
    @Override
    public Object get(ResultSet rs, String colName) throws SQLException {
        Object object = rs.getArray(colName).getArray();
        return Castors.me().castTo(object, String[].class);
    }

    @Override
    public void set(PreparedStatement stat, Object obj, int index) throws SQLException {
        if (null == obj) {
            stat.setNull(index, Types.NULL);
        } else {
            Array array = stat.getConnection().createArrayOf("text[]", (String[]) obj);
            stat.setArray(index, array);
        }
    }
}
