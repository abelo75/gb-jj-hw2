import java.lang.reflect.Field;
import java.util.UUID;

public class QueryBuilder {

    public String buildInsertQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("INSERT INTO ");
        StringBuilder values = new StringBuilder();

        String delimiter = "";
        if (!clazz.isAnnotationPresent(Table.class)) {
            return null;
        }
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        query.append(tableAnnotation.name()).append(" (");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            Column columnAnnotation = field.getAnnotation(Column.class);
            query.append(delimiter).append(columnAnnotation.name());
            field.setAccessible(true);
            values.append(delimiter).append("'").append(field.get(obj)).append("'");
            delimiter = ", ";
        }

        query.append(") VALUES (").append(values).append(")");

        return query.toString();
    }

    public String buildSelectQuery(Class<?> clazz, UUID primaryKey) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        if (!clazz.isAnnotationPresent(Table.class)) {
            return null;
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        query.append(tableAnnotation.name()).append(" WHERE ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey()) {
                    query.append(columnAnnotation.name()).append(" = ").append(primaryKey);
                    break;
                }
            }
        }
        return query.toString();
    }

    public String buildUpdateQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("UPDATE ");

        if (!clazz.isAnnotationPresent(Table.class)) {
            return null;
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        query.append(tableAnnotation.name()).append(" SET ");

        Field[] fields = clazz.getDeclaredFields();
        String idFieldName = "";
        String idFieldValue = "";
        String delimiter = "";
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }
            field.setAccessible(true);
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation.primaryKey()) {
                idFieldName = field.getName();
                idFieldValue = field.get(obj).toString();
            } else {
                query.append(delimiter).append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("'");
                delimiter = ", ";
            }
        }

        if (idFieldValue.isEmpty() || idFieldName.isEmpty()) {
            return null;
        }

        query.append(" WHERE ").append(idFieldName).append("='").append(idFieldValue).append("'");

        return query.toString();

    }

    /**
     * TODO: Доработать в рамках домашней работы
     *
     * @param clazz
     * @param primaryKey
     * @return
     */
    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            return null;
        }
        StringBuilder query = new StringBuilder("DELETE FROM ").append(clazz.getAnnotation(Table.class).name()).append(" WHERE ");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }
            Column annotation = field.getAnnotation(Column.class);
            if (annotation.primaryKey()) {
                query.append(annotation.name()).append(" = '").append(primaryKey).append("'");
                return query.toString();
            }
        }
        return null;
    }

}
